package common

import common.CodeAuthority._
import controllers.routes
import jp.t2v.lab.play2.auth.{AuthConfig, CookieTokenAccessor}
import play.api.mvc.{RequestHeader, Result}
import play.api.mvc.Results.{Forbidden, Redirect}
import services.AccountServiceLike

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}
import models.Tables.AccountRow

trait AuthConfigLike extends AuthConfig {

  type Id = Int

  type User = AccountRow

  type Authority = CodeAuthority

  val idTag: ClassTag[Id] = classTag[Id]

  val sessionTimeoutInSeconds: Int = 3600

  val userAccountService: AccountServiceLike

  def resolveUser(id: Id)(implicit ctx: ExecutionContext): Future[Option[AccountRow]] =
    userAccountService.findById(id)

  def loginSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.AdminController.index()))

  def logoutSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.CorporateController.index()))

  def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect(routes.CorporateController.index()))

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] =
    Future.successful(Forbidden("no permission"))


  def authorize(user: User, authority: Authority)(implicit ctx: ExecutionContext): Future[Boolean] = Future.successful {
    (user.accountAuthorityId, authority) match {
      case (SystemAdministrator.id, _)       => true
      case (Administrator.id, Administrator) => true
      case (Administrator.id, BigUser)       => true
      case (Administrator.id, User)          => true
      case (Administrator.id, Anonymous)     => true
      case (BigUser.id, BigUser)             => true
      case (BigUser.id, User)                => true
      case (BigUser.id, Anonymous)           => true
      case (User.id, User)                   => true
      case (User.id, Anonymous)              => true
      case (Anonymous.id, Anonymous)         => true
      case _                                 => false
    }
  }

  // TODO cookie configuration
  override lazy val tokenAccessor = new CookieTokenAccessor(
    cookieSecureOption = false,
    cookieMaxAge = Some(sessionTimeoutInSeconds))
}
