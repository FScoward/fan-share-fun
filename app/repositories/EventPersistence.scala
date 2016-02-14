package repositories

import javax.inject.Singleton
import com.google.inject.ImplementedBy
import repositories.Tables._
import repositories.Tables.profile.api._

/**
  * Created by Fumiyasu on 2016/02/14.
  */
@Singleton
class EventPersistenceObj extends EventPersistence

@ImplementedBy(classOf[EventPersistenceObj])
trait EventPersistence {
  def list(limit: Int = 100, offset: Int = 0) = {
    Event.drop(offset).take(limit).result
  }
}
