package service

import javax.inject.{Singleton, Inject}

import play.api.mvc.Session
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import repository.UserPersistence
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
 * Created by FScoward on 15/08/25.
 */
@Singleton
class AuthService @Inject()(userPersistence: UserPersistence, protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  def save(username: String, imgUrl: String)(implicit s: Session): Future[Long] = {
    db.run(userPersistence.save(0L, username, imgUrl))
  }

  def findByUsername(username: String)(implicit s: Session): Future[Option[Long]] = {
    db.run(userPersistence.findByUsername(username).headOption)
  }
}

