package spinoco.protocol.mail.header

import scodec.{Attempt, Err}

import scala.util.Try

case class `Auto-Submitted`(
  tpe: `Auto-Submitted`.AutoType.Value
) extends DefaultEmailHeaderField


object `Auto-Submitted` extends DefaultHeaderDescription[`Auto-Submitted`] {

  object AutoType extends Enumeration {

    val No = Value("no")
    val AutoGenerated = Value("auto-generated")
    val AutoReplied = Value("auto-replied")
    val AutoNotified = Value("auto-notified")

  }


  val codec = scodec.codecs.ascii.exmap[`Auto-Submitted`]( a => {
    Attempt.fromEither(
      Try(AutoType.withName(a)).toOption.toRight(Err("Could not parse Auto-Submitted header due to invalid tpe: " + a))
      .map(`Auto-Submitted`(_))
    )
  }
  , b => {
    Attempt.successful(b.tpe.toString)
  })



}