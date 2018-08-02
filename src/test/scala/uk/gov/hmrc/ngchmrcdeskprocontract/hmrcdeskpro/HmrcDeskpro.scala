package uk.gov.hmrc.ngchmrcdeskprocontract.hmrcdeskpro

import play.api.libs.json.{Json, OFormat}

case class HmrcDeskpro ( ticketId: Int )

object HmrcDeskpro {
  implicit val format: OFormat[HmrcDeskpro] = Json.format[HmrcDeskpro]
}