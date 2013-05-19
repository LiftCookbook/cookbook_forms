package code.snippet

import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.SHtml.ChoiceHolder

object StringRadio extends Loggable {

  val radio : ChoiceHolder[String] =
    SHtml.radio(
      "Male" :: "Female" :: "Rather Not Say" :: Nil,
      Empty,
      selected => logger.info("Choice: "+selected) )

  def render = ".options" #> radio.toForm
}



