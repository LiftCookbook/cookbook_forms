package code.snippet

import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.SHtml.ChoiceHolder

object BirthGender extends Enumeration {
  type BirthGender = Value
  val Male = Value("Male")
  val Female = Value("Female")
  val NotSpecified = Value("Rather Not Say")
}

object Radio extends Loggable {

  import BirthGender._

  val options : Seq[BirthGender] = BirthGender.values.toSeq

  val default : Box[BirthGender] = Empty

  val radio : ChoiceHolder[BirthGender] =
    SHtml.radioElem(options, default) { selected =>
      logger.info("Choice: "+selected)
    }

  def render = ".options" #> radio.toForm
}



