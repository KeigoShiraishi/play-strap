package controllers

import javax.inject.Inject

import common.AuthConfigLike
import forms.LoginFormObj.loginForm
import jp.t2v.lab.play2.auth.{LoginLogout, OptionalAuthElement}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import play.filters.csrf.{CSRFAddToken, CSRFCheck}
import services.AccountServiceLike

import scala.concurrent.{ExecutionContext, Future}

class LoginLogoutController @Inject()(val userAccountService: AccountServiceLike,
                                      val messagesApi: MessagesApi,
                                      val addToken: CSRFAddToken,
                                      val checkToken: CSRFCheck)(implicit executor: ExecutionContext)
  extends Controller with LoginLogout with OptionalAuthElement with AuthConfigLike with I18nSupport {

  def showLoginForm() = StackAction { implicit request =>
    loggedIn match {
      case Some(user) => Redirect(routes.AdminController.index())
      case None       => Redirect(routes.AdminController.login())
    }
  }

  def login() = checkToken {
    Action.async { implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => {
          System.out.println("error");
          Future.successful(Redirect(routes.LoginLogoutController.showLoginForm()))
        },
        form => {
          userAccountService.authenticate(form).flatMap { user =>
            user match {
              case Some(user) => gotoLoginSucceeded(user.accountId)
              case _          => Future.successful(Redirect(routes.LoginLogoutController.showLoginForm()))
            }
          }
        })
    }
  }

  def logout() = checkToken {
    Action.async { implicit request =>
      gotoLogoutSucceeded
    }
  }

}
