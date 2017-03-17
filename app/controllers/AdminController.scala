package controllers

import javax.inject._

import common.CodeAuthority.Administrator
import common.{AuthConfigLike, CodeAuthority}
import jp.t2v.lab.play2.auth.AuthElement
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.AccountServiceLike

import scala.concurrent.ExecutionContext

@Singleton
class AdminController @Inject()(val userAccountService: AccountServiceLike,
                                val messagesApi: MessagesApi)(implicit executor: ExecutionContext)
  extends Controller with AuthElement with AuthConfigLike with I18nSupport {

  def blank = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.blank("Your new application is ready."))
  }

  def buttons = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.buttons("Your new application is ready."))
  }

  def flot = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.flot("Your new application is ready."))
  }

  def forms = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.forms("Your new application is ready."))
  }

  def grid = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.grid("Your new application is ready."))
  }

  def icons = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.icons("Your new application is ready."))
  }

  def index = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.index("Your new application is ready."))
  }

  def login = Action { implicit request =>
    Ok(views.html.admin.login("Your new application is ready."))
  }

  def morris = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.morris("Your new application is ready."))
  }

  def notifications = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.notifications("Your new application is ready."))
  }

  def panels_wells = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.panels_wells("Your new application is ready."))
  }

  def tables = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.tables("Your new application is ready."))
  }

  def typography = StackAction(AuthorityKey -> Administrator) { implicit request =>
    Ok(views.html.admin.typography("Your new application is ready."))
  }

}
