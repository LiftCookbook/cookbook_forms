package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.util.PassThru
import net.liftweb.http.SHtml

class Likes {

  var likesTurtles = false

  def disable =
    if (math.random > 0.5d) "* [disabled]" #> "disabled"
    else PassThru

  def render =
    "#like" #> disable( SHtml.checkbox(likesTurtles, likesTurtles = _) )

}
