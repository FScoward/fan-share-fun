package service

import javax.inject.Inject

import models.Event
import play.api.mvc.Session
import repositories.EventPersistence
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.driver.JdbcProfile

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by Fumiyasu on 2016/02/14.
  */
class EventService @Inject()(eventPersistence: EventPersistence,
                             protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  def list()(implicit session: Session): Future[Seq[Event]] = {
    val eventsF = db.run(eventPersistence.list())
    eventsF.map(_.map(event => Event(event.eventId, event.eventName)))
  }

}
