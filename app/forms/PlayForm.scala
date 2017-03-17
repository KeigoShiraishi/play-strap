package forms

import common.CustomConstraints
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

case class LoginForm(email: String, password: String)

object LoginFormObj {

  val loginForm = Form(
    mapping(
      "email" -> nonEmptyText.verifying(CustomConstraints.emailAddress),
      "password" -> nonEmptyText.verifying(CustomConstraints.passWordLight))(LoginForm.apply)(LoginForm.unapply))
}
