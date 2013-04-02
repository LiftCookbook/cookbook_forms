package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml.{text,ajaxSubmit,hidden}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.common.Loggable
import xml.Text
import net.liftweb.http.S


object EchoForm extends Loggable {

  def render = {

    var name = ""

    def process() : JsCmd = SetHtml("result", Text(name))

    "@name" #> text(name, s => name = s) &
    "type=submit" #> ajaxSubmit("Click Me", process)

  }

  def renderViaHidden = {

    var name = ""

    def process() : JsCmd = SetHtml("result", Text(name))

    "@name" #> text(name, s => name = s) &
      "button *+" #> S.formGroup(1000) { hidden(process) }
  }

}
