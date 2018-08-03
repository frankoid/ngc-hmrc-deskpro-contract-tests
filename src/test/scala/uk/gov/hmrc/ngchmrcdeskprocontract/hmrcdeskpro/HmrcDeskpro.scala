package uk.gov.hmrc.ngchmrcdeskprocontract.hmrcdeskpro

import play.api.libs.json.{Json, OFormat}

case class CreateTicketResponse ( ticketId: Int )

object HmrcDeskpro {
  implicit val format: OFormat[CreateTicketResponse] = Json.format[CreateTicketResponse]
}