package code.snippet

import scala.xml.NodeSeq
import net.liftweb.http._
import net.liftweb.http.FieldBinding.Self

object AccountInfoEditor extends CssBoundLiftScreen {
  val formName = "accountedit"

  override def allTemplate = savedDefaultXml
  protected def defaultAllTemplate = super.allTemplate

  // Pull the definition of the "normal" field from a template, if it exists
  override def defaultFieldNodeSeq: NodeSeq = Templates("accounts" :: "account_edit_field" :: Nil).openOr(
    <div>
      <label class="label field"></label>
      <span class="value fieldValue"></span>
      <span class="help"></span>
      <div class="errors">
        <div class="error"></div>
      </div>
    </div>)

  override def finish() {
    println("Account Edited for: "+firstName)
    S.notice("Done.")
  }

  // An example source of an account:
  case class Account(firstName: String, lastName: String, address: String)
  def accountToEdit = new Account("Ada", "Lovelace", "Ockham Park, Surrey")

  // Fields:
  val firstName = field("First Name", accountToEdit.firstName,
    trim, valMinLen(1, "First name is required"),
    FieldBinding("firstName"))

  val lastName = field("Last Name", accountToEdit.lastName,
    trim, valMinLen(1, "Last name is required"),
    FieldBinding("lastName"))

  val address = textarea("Address", accountToEdit.address,
    trim, valMinLen(1, "Address is required"), valMaxLen(255, "Address too long"),
    FieldBinding("address", Self))

}

