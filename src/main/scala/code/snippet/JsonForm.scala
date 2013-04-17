package code.snippet

import scala.xml.{Text, NodeSeq}

import net.liftweb.util.Helpers._
import net.liftweb.util.JsonCmd
import net.liftweb.http.SHtml.jsonForm
import net.liftweb.http.JsonHandler
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{SetHtml, Script}

object JsonForm {

  def render =
    "#jsonForm" #> ((ns:NodeSeq) => jsonForm(MottoServer, ns)) &
    "#jsonScript" #> Script(MottoServer.jsCmd)

  object MottoServer extends JsonHandler {

    def apply(in: Any): JsCmd = in match {
      case JsonCmd("processForm", target, params: Map[String, String], all) =>
        val name = params.getOrElse("name", "No Name")
        val motto = params.getOrElse("motto", "No Motto")
        SetHtml("result",
          Text("The motto of %s is %s".format(name,motto)) )
    }
  }
}