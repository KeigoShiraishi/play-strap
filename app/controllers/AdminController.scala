package controllers

import javax.inject._

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class AdminController @Inject()(val messagesApi: MessagesApi)
                               (implicit executor: ExecutionContext) extends Controller with I18nSupport {

  def blank = Action { implicit request =>
    Ok(views.html.admin.blank("Your new application is ready."))
  }

  def buttons = Action { implicit request =>
    Ok(views.html.admin.buttons("Your new application is ready."))
  }

  def flot = Action { implicit request =>
    Ok(views.html.admin.flot("Your new application is ready."))
  }

  def forms = Action { implicit request =>
    Ok(views.html.admin.forms("Your new application is ready."))
  }

  def grid = Action { implicit request =>
    Ok(views.html.admin.grid("Your new application is ready."))
  }

  def icons = Action { implicit request =>
    Ok(views.html.admin.icons("Your new application is ready."))
  }

  def index = Action { implicit request =>
    Ok(views.html.admin.index("Your new application is ready."))
  }

  def login = Action { implicit request =>
    Ok(views.html.admin.login("Your new application is ready."))
  }

  def morris = Action { implicit request =>
    Ok(views.html.admin.morris("Your new application is ready."))
  }

  def notifications = Action { implicit request =>
    Ok(views.html.admin.notifications("Your new application is ready."))
  }

  def panels_wells = Action { implicit request =>
    Ok(views.html.admin.panels_wells("Your new application is ready."))
  }

  def tables = Action { implicit request =>
    Ok(views.html.admin.tables("Your new application is ready."))
  }

  def typography = Action { implicit request =>
    Ok(views.html.admin.typography("Your new application is ready."))
  }

}
