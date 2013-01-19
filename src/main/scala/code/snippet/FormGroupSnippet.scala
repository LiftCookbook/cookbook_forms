package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.S


class FormGroupSnippet {

  def render = {

    var name = ""

    def processForm() = {
      println("The value of name is: "+name)
    }

    /*
      Avoid having to use formGroup by replacing
      hidden with onSubmitUnit
    */

    "#name" #> text(name, (s) => { println("Setting name to "+s); name = s} ) &
      "type=submit" #> S.formGroup(1000) { hidden(processForm) }

  }


}
