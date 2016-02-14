package controllers

import javax.inject.Inject

import models.Event
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.EventService

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by Fumiyasu on 2016/02/14.
  */
class EventController @Inject()(eventService: EventService) extends Controller {

  implicit val eventWrites = Json.format[Event]

  def getList = Action.async { implicit request =>
    val list = eventService.list()
    list.map(events => {
      val json = Json.toJson(events)
      Logger.debug(s"EventController#list => $json")
      Ok(json)
    })
  }

  def list = Action {
    Ok(views.html.events())
  }

}
