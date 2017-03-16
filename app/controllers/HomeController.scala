package controllers

import javax.inject._

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi)
                              (implicit executor: ExecutionContext) extends Controller with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }

  def about = Action { implicit request =>
    Ok(views.html.about("Your new application is ready."))
  }

  def service = Action { implicit request =>
    Ok(views.html.service("Your new application is ready."))
  }

  def contact = Action { implicit request =>
    Ok(views.html.contact("Your new application is ready."))
  }

  def portfolio_1 = Action { implicit request =>
    Ok(views.html.portfolio_1("Your new application is ready."))
  }

  def portfolio_2 = Action { implicit request =>
    Ok(views.html.portfolio_2("Your new application is ready."))
  }

  def portfolio_3 = Action { implicit request =>
    Ok(views.html.portfolio_3("Your new application is ready."))
  }

  def portfolio_4 = Action { implicit request =>
    Ok(views.html.portfolio_4("Your new application is ready."))
  }

  def portfolio_item = Action { implicit request =>
    Ok(views.html.portfolio_item("Your new application is ready."))
  }

  def blog_1 = Action { implicit request =>
    Ok(views.html.blog_1("Your new application is ready."))
  }

  def blog_2 = Action { implicit request =>
    Ok(views.html.blog_2("Your new application is ready."))
  }

  def blog_post = Action { implicit request =>
    Ok(views.html.blog_post("Your new application is ready."))
  }

  def fullwidth = Action { implicit request =>
    Ok(views.html.fullwidth("Your new application is ready."))
  }

  def sidebar = Action { implicit request =>
    Ok(views.html.sidebar("Your new application is ready."))
  }

  def faq = Action { implicit request =>
    Ok(views.html.faq("Your new application is ready."))
  }

  def error404 = Action { implicit request =>
    Ok(views.html.error404("Your new application is ready."))
  }

  def pricing = Action { implicit request =>
    Ok(views.html.pricing("Your new application is ready."))
  }

}
