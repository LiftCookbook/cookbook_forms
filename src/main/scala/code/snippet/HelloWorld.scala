package code
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import Helpers._

class HelloWorld {

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> new java.util.Date().toString


}

