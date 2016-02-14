package controllers.authenticate
import java.util.UUID
import javax.inject.{Singleton, Inject}

import play.api.Logger
import play.api.cache.Cache
import play.api.mvc.{ Cookie, Action, Controller }
import service.AuthService
import twitter4j.auth.{ RequestToken, AccessToken }
import twitter4j.{ Twitter, TwitterException, TwitterFactory }
import play.api.Play.current

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
 * Created by FScoward on 15/08/27.
 */

@Singleton
class TwitterLoginController @Inject()(authService: AuthService) extends Controller {

  def login = Action {
    try {
      val twitter = new TwitterFactory().getInstance()
      twitter.setOAuthAccessToken(null)
      val requestToken = twitter.getOAuthRequestToken
      val url = requestToken.getAuthenticationURL
      Cache.set("twitter", twitter, 60 seconds)
      Cache.set("requestToken", requestToken, 60 seconds)
      Redirect(url)
    } catch {
      case e: TwitterException => Unauthorized(e.getMessage)
      case e: IllegalStateException => Unauthorized(e.getMessage)
    }
  }

  def logout = Action { implicit request =>
    Cache.remove("twitter")
    Cache.remove("requestToken")
    Redirect(controllers.routes.Application.index()).withNewSession
  }

  def callback = Action.async { implicit request =>
    val oauthVerifier = request.getQueryString("oauth_verifier")
    val requestOauthToken = request.getQueryString("oauth_token")
    (oauthVerifier, requestOauthToken) match {
      case (Some(oauthVerifier), Some(oauthToken)) => {
        val twitter = Cache.getAs[Twitter]("twitter").get
        val requestToken = Cache.getAs[RequestToken]("requestToken").get

        val accessToken: AccessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier)
        val token = UUID.randomUUID.toString
        twitter.setOAuthAccessToken(accessToken)
        val screenName = twitter.getScreenName
        val imgUrl = twitter.showUser(screenName).getBiggerProfileImageURL
        Logger.debug(s"Login Success. screen name is $screenName ,imgUrl is $imgUrl")

        val userId = authService.findByUsername(screenName).map(_.getOrElse{
          authService.save(screenName, imgUrl)
        })

        Cache.remove("twitter")
        Cache.remove("requestToken")
        userId.map(userId => Redirect("/front")
          .withSession("token" -> token, "username" -> screenName, "userId" -> userId.toString)
          .withCookies(
            Cookie("userId", userId.toString, httpOnly = false),
            Cookie("username", screenName, httpOnly = false),
            Cookie("image", imgUrl, httpOnly = false)
          ))
      }
      case _ => Future.successful(Redirect("/").withNewSession)
    }
  }
}
