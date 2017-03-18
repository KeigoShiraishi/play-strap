package services

import java.util.UUID
import javax.inject.Inject

import com.google.inject.ImplementedBy
import forms.LoginForm
import models.Tables.AccountRow
import org.apache.commons.codec.digest.DigestUtils
import repositories.{AccountRepositoryLike, ErrorLogRepositoryLike}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@ImplementedBy(classOf[AccountService])
trait AccountServiceLike {

  /**  */
  def findById(userId: Int): Future[Option[AccountRow]]

  /**  */
  def authenticate(form: LoginForm): Future[Option[AccountRow]]

}

class AccountService @Inject()(val accountRepo: AccountRepositoryLike,
                               val errorLogRepo: ErrorLogRepositoryLike) extends AccountServiceLike {

  import AccountService._

  def findById(userId: Int): Future[Option[AccountRow]] = {
    accountRepo.findById(userId).map(userOpt =>
      userOpt.flatMap{ user =>
        Some(user)
      })
  }

  def authenticate(form: LoginForm): Future[Option[AccountRow]] = {
    accountRepo.findByIdentificationName(form.email).map(userOpt =>
      userOpt.flatMap{ user =>
        if (hashAndStretch(form.password, user.accountPasswordSalt, STRETCH_LOOP_COUNT) == user.accountPassword)
          Some(user)
        else
          None
      })
  }


}

object AccountService {

  val STRETCH_LOOP_COUNT = 1000

  def hashAndStretch(plain: String, salt: String, loopCnt: Int): String = {
    var hashed: String = ""
    (1 to STRETCH_LOOP_COUNT).foreach(i =>
      hashed = DigestUtils.sha256Hex(hashed + plain + salt))
    hashed
  }

  def createPasswordSalt(): String = {
    DigestUtils.sha256Hex(UUID.randomUUID().toString())
  }

}