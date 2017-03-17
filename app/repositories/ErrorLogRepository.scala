package repositories

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.Tables
import models.Tables.AccountRow
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

@ImplementedBy(classOf[ErrorLogRepository])
trait ErrorLogRepositoryLike extends HasDatabaseConfigProvider[JdbcProfile] {

  def errorLog(e: Exception, account: AccountRow): Left[Exception, Nothing]

  def errorLog(e: Exception, account: Option[AccountRow]): Left[Exception, Nothing]

}

class ErrorLogRepository @Inject() ( protected val dbConfigProvider: DatabaseConfigProvider)
    extends ErrorLogRepositoryLike {
  import driver.api._

  def errorLog(e: Exception, account: AccountRow): Left[Exception, Nothing] = {
//    insertErrorLog(e.fillInStackTrace().toString(), Some(account.accountId))
    Left(e)
  }

  def errorLog(e: Exception, account: Option[AccountRow]): Left[Exception, Nothing] = {
//    insertErrorLog(e.fillInStackTrace().toString(), account.map(_.accountId))
    Left(e)
  }

//  def insertErrorLog(fullmsg: String, userId: Option[Int]): Future[Int] = {
//    val msg = if (fullmsg.length() > 2000) fullmsg.substring(0, 2000) else fullmsg
//    db.run(Tables.ErrorLog.map(x => (x.errorMessage, x.accountIdCreate)) += (msg, userId))
//  }

}
