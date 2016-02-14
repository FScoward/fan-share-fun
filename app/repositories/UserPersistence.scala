package repository

import javax.inject.Singleton
import com.google.inject.ImplementedBy
import repositories.Tables._
import repositories.Tables.profile.api._

/**
  * Created by Fumiyasu on 2015/11/21.
  */
@Singleton
class UserPersistenceObj extends UserPersistence

@ImplementedBy(classOf[UserPersistenceObj])
trait UserPersistence {
  def save(userId: Long = 0L, username: String, imgUrl: String) = {
    User returning User.map(_.userId) += UserRow(1L, username, imgUrl)
  }

  def findByUsername(username: String) = {
    User.filter(_.username === username.bind).map(_.userId).result
  }
}
