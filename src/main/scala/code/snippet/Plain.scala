package code.snippet

import net.liftweb.common._
import net.liftweb.http.S
import net.liftweb.util.PassThru

object Plain {

  def render = S.param("name") match {
    case Full(name) =>
      S.notice("Hello "+name)
      S.redirectTo("/plain")
    case _ =>
      PassThru
  }


  def viaFor = {
    for {
      name <- S.param("name")
      pet <- S.param("petName")
    } {
      S.notice("Hello %s and %s".format(name,pet))
      S.redirectTo("/plain")
    }

   PassThru
  }


  def allNames = {

    val names = for {
      req <- S.request.toList
      paramName <- req.paramNames
      if paramName.toLowerCase contains "name"
      value <- S.param(paramName)
    } yield value

    S.notice("Hi "+names.mkString(","))
    PassThru
  }

}
