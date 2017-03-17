package repositories

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.Tables
import models.Tables.AccountRow
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

@ImplementedBy(classOf[AccountRepository])
trait AccountRepositoryLike extends HasDatabaseConfigProvider[JdbcProfile] {

  def findById(userId: Int): Future[Option[AccountRow]]

  def findByIdentificationName(idname: String): Future[Option[AccountRow]]

}

class AccountRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends AccountRepositoryLike {

  import driver.api._

  def findById(userId: Int): Future[Option[AccountRow]] = {
    db.run(Tables.Account.filter(_.accountId === userId.bind).result.headOption)
  }

  def findByIdentificationName(idname: String): Future[Option[AccountRow]] = {
    db.run(Tables.Account.filter(_.accountIdentificationName === idname.bind).result.headOption)
  }
}
