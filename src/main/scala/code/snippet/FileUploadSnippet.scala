package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.{OnDiskFileParamHolder, FileParamHolder}
import net.liftweb.common.{Loggable, Full, Empty, Box}
import java.io.InputStream


class FileUploadSnippet extends Loggable {

  def render = {

    var upload : Box[FileParamHolder] = Empty

    def processForm() = upload match {

      case Full(content : OnDiskFileParamHolder) =>
        logger.info("File: "+content.localFile.getAbsolutePath)
        val in: InputStream = content.fileStream
        // ...work with stream here...
        val wasDeleted_? = content.localFile.delete()

      case Full(FileParamHolder(_, mimeType, fileName, file)) =>
        logger.info("%s of type %s is %d bytes long" format (fileName, mimeType, file.length))

      case _ => logger.warn("No file?")

    }

    "#file" #> fileUpload(f => upload = Full(f)) &
      "type=submit" #> onSubmitUnit(processForm)
  }
}
