package repositories

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(Event.schema, Eventdescription.schema, EventHasGroup.schema, Group.schema, GroupHasUser.schema, User.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Event
   *  @param eventId Database column event_id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param eventName Database column event_name SqlType(VARCHAR), Length(45,true) */
  case class EventRow(eventId: Long, eventName: String)
  /** GetResult implicit for fetching EventRow objects using plain SQL queries */
  implicit def GetResultEventRow(implicit e0: GR[Long], e1: GR[String]): GR[EventRow] = GR{
    prs => import prs._
    EventRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table event. Objects of this class serve as prototypes for rows in queries. */
  class Event(_tableTag: Tag) extends Table[EventRow](_tableTag, "event") {
    def * = (eventId, eventName) <> (EventRow.tupled, EventRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(eventId), Rep.Some(eventName)).shaped.<>({r=>import r._; _1.map(_=> EventRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column event_id SqlType(BIGINT), AutoInc, PrimaryKey */
    val eventId: Rep[Long] = column[Long]("event_id", O.AutoInc, O.PrimaryKey)
    /** Database column event_name SqlType(VARCHAR), Length(45,true) */
    val eventName: Rep[String] = column[String]("event_name", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Event */
  lazy val Event = new TableQuery(tag => new Event(tag))

  /** Entity class storing rows of table Eventdescription
   *  @param eventEventId Database column Event_event_id SqlType(BIGINT), PrimaryKey
   *  @param artist Database column artist SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param location Database column location SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param date Database column date SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param description Database column description SqlType(TEXT), Default(None) */
  case class EventdescriptionRow(eventEventId: Long, artist: Option[String] = None, location: Option[String] = None, date: Option[String] = None, description: Option[String] = None)
  /** GetResult implicit for fetching EventdescriptionRow objects using plain SQL queries */
  implicit def GetResultEventdescriptionRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[EventdescriptionRow] = GR{
    prs => import prs._
    EventdescriptionRow.tupled((<<[Long], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table eventdescription. Objects of this class serve as prototypes for rows in queries. */
  class Eventdescription(_tableTag: Tag) extends Table[EventdescriptionRow](_tableTag, "eventdescription") {
    def * = (eventEventId, artist, location, date, description) <> (EventdescriptionRow.tupled, EventdescriptionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(eventEventId), artist, location, date, description).shaped.<>({r=>import r._; _1.map(_=> EventdescriptionRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column Event_event_id SqlType(BIGINT), PrimaryKey */
    val eventEventId: Rep[Long] = column[Long]("Event_event_id", O.PrimaryKey)
    /** Database column artist SqlType(VARCHAR), Length(45,true), Default(None) */
    val artist: Rep[Option[String]] = column[Option[String]]("artist", O.Length(45,varying=true), O.Default(None))
    /** Database column location SqlType(VARCHAR), Length(45,true), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(45,varying=true), O.Default(None))
    /** Database column date SqlType(VARCHAR), Length(45,true), Default(None) */
    val date: Rep[Option[String]] = column[Option[String]]("date", O.Length(45,varying=true), O.Default(None))
    /** Database column description SqlType(TEXT), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Default(None))

    /** Foreign key referencing Event (database name fk_EventDescription_Event) */
    lazy val eventFk = foreignKey("fk_EventDescription_Event", eventEventId, Event)(r => r.eventId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Eventdescription */
  lazy val Eventdescription = new TableQuery(tag => new Eventdescription(tag))

  /** Entity class storing rows of table EventHasGroup
   *  @param eventEventId Database column Event_event_id SqlType(BIGINT)
   *  @param groupGroupId Database column Group_group_id SqlType(BIGINT) */
  case class EventHasGroupRow(eventEventId: Long, groupGroupId: Long)
  /** GetResult implicit for fetching EventHasGroupRow objects using plain SQL queries */
  implicit def GetResultEventHasGroupRow(implicit e0: GR[Long]): GR[EventHasGroupRow] = GR{
    prs => import prs._
    EventHasGroupRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table event_has_group. Objects of this class serve as prototypes for rows in queries. */
  class EventHasGroup(_tableTag: Tag) extends Table[EventHasGroupRow](_tableTag, "event_has_group") {
    def * = (eventEventId, groupGroupId) <> (EventHasGroupRow.tupled, EventHasGroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(eventEventId), Rep.Some(groupGroupId)).shaped.<>({r=>import r._; _1.map(_=> EventHasGroupRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column Event_event_id SqlType(BIGINT) */
    val eventEventId: Rep[Long] = column[Long]("Event_event_id")
    /** Database column Group_group_id SqlType(BIGINT) */
    val groupGroupId: Rep[Long] = column[Long]("Group_group_id")

    /** Primary key of EventHasGroup (database name event_has_group_PK) */
    val pk = primaryKey("event_has_group_PK", (eventEventId, groupGroupId))

    /** Foreign key referencing Event (database name fk_Event_has_Group_Event1) */
    lazy val eventFk = foreignKey("fk_Event_has_Group_Event1", eventEventId, Event)(r => r.eventId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Group (database name fk_Event_has_Group_Group1) */
    lazy val groupFk = foreignKey("fk_Event_has_Group_Group1", groupGroupId, Group)(r => r.groupId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EventHasGroup */
  lazy val EventHasGroup = new TableQuery(tag => new EventHasGroup(tag))

  /** Entity class storing rows of table Group
   *  @param groupId Database column group_id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param groupName Database column group_name SqlType(VARCHAR), Length(45,true), Default(None) */
  case class GroupRow(groupId: Long, groupName: Option[String] = None)
  /** GetResult implicit for fetching GroupRow objects using plain SQL queries */
  implicit def GetResultGroupRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[GroupRow] = GR{
    prs => import prs._
    GroupRow.tupled((<<[Long], <<?[String]))
  }
  /** Table description of table group. Objects of this class serve as prototypes for rows in queries. */
  class Group(_tableTag: Tag) extends Table[GroupRow](_tableTag, "group") {
    def * = (groupId, groupName) <> (GroupRow.tupled, GroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(groupId), groupName).shaped.<>({r=>import r._; _1.map(_=> GroupRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column group_id SqlType(BIGINT), AutoInc, PrimaryKey */
    val groupId: Rep[Long] = column[Long]("group_id", O.AutoInc, O.PrimaryKey)
    /** Database column group_name SqlType(VARCHAR), Length(45,true), Default(None) */
    val groupName: Rep[Option[String]] = column[Option[String]]("group_name", O.Length(45,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Group */
  lazy val Group = new TableQuery(tag => new Group(tag))

  /** Entity class storing rows of table GroupHasUser
   *  @param groupGroupId Database column Group_group_id SqlType(BIGINT)
   *  @param userUserId Database column User_user_id SqlType(BIGINT)
   *  @param isOwner Database column is_owner SqlType(BIT) */
  case class GroupHasUserRow(groupGroupId: Long, userUserId: Long, isOwner: Boolean)
  /** GetResult implicit for fetching GroupHasUserRow objects using plain SQL queries */
  implicit def GetResultGroupHasUserRow(implicit e0: GR[Long], e1: GR[Boolean]): GR[GroupHasUserRow] = GR{
    prs => import prs._
    GroupHasUserRow.tupled((<<[Long], <<[Long], <<[Boolean]))
  }
  /** Table description of table group_has_user. Objects of this class serve as prototypes for rows in queries. */
  class GroupHasUser(_tableTag: Tag) extends Table[GroupHasUserRow](_tableTag, "group_has_user") {
    def * = (groupGroupId, userUserId, isOwner) <> (GroupHasUserRow.tupled, GroupHasUserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(groupGroupId), Rep.Some(userUserId), Rep.Some(isOwner)).shaped.<>({r=>import r._; _1.map(_=> GroupHasUserRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column Group_group_id SqlType(BIGINT) */
    val groupGroupId: Rep[Long] = column[Long]("Group_group_id")
    /** Database column User_user_id SqlType(BIGINT) */
    val userUserId: Rep[Long] = column[Long]("User_user_id")
    /** Database column is_owner SqlType(BIT) */
    val isOwner: Rep[Boolean] = column[Boolean]("is_owner")

    /** Primary key of GroupHasUser (database name group_has_user_PK) */
    val pk = primaryKey("group_has_user_PK", (groupGroupId, userUserId))

    /** Foreign key referencing Group (database name fk_Group_has_User_Group1) */
    lazy val groupFk = foreignKey("fk_Group_has_User_Group1", groupGroupId, Group)(r => r.groupId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name fk_Group_has_User_User1) */
    lazy val userFk = foreignKey("fk_Group_has_User_User1", userUserId, User)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table GroupHasUser */
  lazy val GroupHasUser = new TableQuery(tag => new GroupHasUser(tag))

  /** Entity class storing rows of table User
   *  @param userId Database column user_id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(45,true)
   *  @param imageUrl Database column image_url SqlType(TEXT) */
  case class UserRow(userId: Long, username: String, imageUrl: String)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Long], e1: GR[String]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Long], <<[String], <<[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (userId, username, imageUrl) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(username), Rep.Some(imageUrl)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(BIGINT), AutoInc, PrimaryKey */
    val userId: Rep[Long] = column[Long]("user_id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(45,true) */
    val username: Rep[String] = column[String]("username", O.Length(45,varying=true))
    /** Database column image_url SqlType(TEXT) */
    val imageUrl: Rep[String] = column[String]("image_url")
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
