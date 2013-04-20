package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.{StatefulSnippet, SHtml}
import net.liftweb.common.Loggable

class MultiSelectStateful extends StatefulSnippet with Loggable {

  def dispatch = {
    case _ => render
  }

  case class Item(id: String, name: String)

  val inventory =
    Item("a", "Coffee") ::
    Item("b", "Milk") ::
    Item("c", "Sugar") :: Nil

  val options : List[(String,String)] =
    inventory.map(i => (i.id -> i.name))

  var current = inventory.head.id :: Nil

  def render = {

    def logSelected() =
      logger.info("Values selected: "+current)

    "#opts *" #> (
      SHtml.hidden( () => current = Nil) ++
      SHtml.multiSelect(options, current, current = _)
    ) &
    "type=submit" #> SHtml.onSubmitUnit(logSelected)

  }

}



