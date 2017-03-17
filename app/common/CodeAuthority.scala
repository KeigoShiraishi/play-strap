package common

sealed trait CodeAuthority {

  def id: Int
  def name: String

}

object CodeAuthority {

  def valueOf(value: String): CodeAuthority = value match {
    case "SystemAdministrator" => SystemAdministrator
    case "Administrator"       => Administrator
    case "BigUser"             => BigUser
    case "User"                => User
    case "Anonymous"           => Anonymous
    case _                     => throw new IllegalArgumentException()
  }

  case object SystemAdministrator extends CodeAuthority {
    val id = 3
    val name = "SystemAdministrator"
  }

  case object Administrator extends CodeAuthority {
    val id = 5
    val name = "Administrator"
  }

  case object BigUser extends CodeAuthority {
    val id = 8
    val name = "BigUser"
  }

  case object User extends CodeAuthority {
    val id = 13
    val name = "User"
  }

  case object Anonymous extends CodeAuthority {
    val id = 21
    val name = "Anonymous"
  }

}
