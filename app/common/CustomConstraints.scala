package common

import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}

object CustomConstraints extends CustomConstraints

trait CustomConstraints {

  private val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  def emailAddress: Constraint[String] = Constraint[String]("constraint.custom.email") { e =>
    if ((e == null) || (e.trim.isEmpty)) Valid // use nonEmpty or custom nonEmpty constraints
    else emailRegex.findFirstMatchIn(e)
      .map(_ => Valid)
      .getOrElse(Invalid(ValidationError("error.email")))
  }

  private val passwordLightRegex = """^(?=.*?[a-z])(?=.*?\d)[A-Za-z\d@_]{8,20}$""".r
  def passWordLight: Constraint[String] = Constraint[String]("constraint.password") { e =>
    if ((e == null) || (e.trim.isEmpty)) Valid // use nonEmpty or custom nonEmpty constraints
    else passwordLightRegex.findFirstMatchIn(e)
      .map(_ => Valid)
      .getOrElse(Invalid(ValidationError("validation error")))
  }


}
