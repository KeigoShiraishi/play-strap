package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import com.github.tototoshi.slick.MySQLJodaSupport._
  import org.joda.time.DateTime
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Account.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Account
   *  @param accountId Database column account_id SqlType(INT), AutoInc, PrimaryKey
   *  @param accountIdentificationName Database column account_identification_name SqlType(VARCHAR), Length(255,true)
   *  @param accountPassword Database column account_password SqlType(VARCHAR), Length(64,true)
   *  @param accountPasswordSalt Database column account_password_salt SqlType(VARCHAR), Length(64,true)
   *  @param accountTimezone Database column account_timezone SqlType(VARCHAR), Length(255,true), Default(Asia/Tokyo)
   *  @param accountLanguage Database column account_language SqlType(VARCHAR), Length(255,true), Default(ja)
   *  @param accountEmail Database column account_email SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param accountNickname Database column account_nickname SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param accountAuthorityId Database column account_authority_id SqlType(INT)
   *  @param accountCreateAt Database column account_create_at SqlType(TIMESTAMP)
   *  @param accountUpdateAt Database column account_update_at SqlType(TIMESTAMP) */
  case class AccountRow(accountId: Int, accountIdentificationName: String, accountPassword: String, accountPasswordSalt: String, accountTimezone: String = "Asia/Tokyo", accountLanguage: String = "ja", accountEmail: Option[String] = None, accountNickname: Option[String] = None, accountAuthorityId: Int, accountCreateAt: DateTime, accountUpdateAt: DateTime)
  /** GetResult implicit for fetching AccountRow objects using plain SQL queries */
  implicit def GetResultAccountRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[DateTime]): GR[AccountRow] = GR{
    prs => import prs._
    AccountRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[String], <<?[String], <<[Int], <<[DateTime], <<[DateTime]))
  }
  /** Table description of table account. Objects of this class serve as prototypes for rows in queries. */
  class Account(_tableTag: Tag) extends Table[AccountRow](_tableTag, "account") {
    def * = (accountId, accountIdentificationName, accountPassword, accountPasswordSalt, accountTimezone, accountLanguage, accountEmail, accountNickname, accountAuthorityId, accountCreateAt, accountUpdateAt) <> (AccountRow.tupled, AccountRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId), Rep.Some(accountIdentificationName), Rep.Some(accountPassword), Rep.Some(accountPasswordSalt), Rep.Some(accountTimezone), Rep.Some(accountLanguage), accountEmail, accountNickname, Rep.Some(accountAuthorityId), Rep.Some(accountCreateAt), Rep.Some(accountUpdateAt)).shaped.<>({r=>import r._; _1.map(_=> AccountRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9.get, _10.get, _11.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column account_id SqlType(INT), AutoInc, PrimaryKey */
    val accountId: Rep[Int] = column[Int]("account_id", O.AutoInc, O.PrimaryKey)
    /** Database column account_identification_name SqlType(VARCHAR), Length(255,true) */
    val accountIdentificationName: Rep[String] = column[String]("account_identification_name", O.Length(255,varying=true))
    /** Database column account_password SqlType(VARCHAR), Length(64,true) */
    val accountPassword: Rep[String] = column[String]("account_password", O.Length(64,varying=true))
    /** Database column account_password_salt SqlType(VARCHAR), Length(64,true) */
    val accountPasswordSalt: Rep[String] = column[String]("account_password_salt", O.Length(64,varying=true))
    /** Database column account_timezone SqlType(VARCHAR), Length(255,true), Default(Asia/Tokyo) */
    val accountTimezone: Rep[String] = column[String]("account_timezone", O.Length(255,varying=true), O.Default("Asia/Tokyo"))
    /** Database column account_language SqlType(VARCHAR), Length(255,true), Default(ja) */
    val accountLanguage: Rep[String] = column[String]("account_language", O.Length(255,varying=true), O.Default("ja"))
    /** Database column account_email SqlType(VARCHAR), Length(255,true), Default(None) */
    val accountEmail: Rep[Option[String]] = column[Option[String]]("account_email", O.Length(255,varying=true), O.Default(None))
    /** Database column account_nickname SqlType(VARCHAR), Length(255,true), Default(None) */
    val accountNickname: Rep[Option[String]] = column[Option[String]]("account_nickname", O.Length(255,varying=true), O.Default(None))
    /** Database column account_authority_id SqlType(INT) */
    val accountAuthorityId: Rep[Int] = column[Int]("account_authority_id")
    /** Database column account_create_at SqlType(TIMESTAMP) */
    val accountCreateAt: Rep[DateTime] = column[DateTime]("account_create_at")
    /** Database column account_update_at SqlType(TIMESTAMP) */
    val accountUpdateAt: Rep[DateTime] = column[DateTime]("account_update_at")

    /** Uniqueness Index over (accountEmail) (database name account_account_email_uindex) */
    val index1 = index("account_account_email_uindex", accountEmail, unique=true)
    /** Uniqueness Index over (accountIdentificationName) (database name account_account_identification_name_uindex) */
    val index2 = index("account_account_identification_name_uindex", accountIdentificationName, unique=true)
  }
  /** Collection-like TableQuery object for table Account */
  lazy val Account = new TableQuery(tag => new Account(tag))
}
