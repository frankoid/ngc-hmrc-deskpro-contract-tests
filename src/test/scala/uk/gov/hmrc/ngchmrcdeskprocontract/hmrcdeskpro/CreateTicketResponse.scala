package uk.gov.hmrc.ngchmrcdeskprocontract.hmrcdeskpro

import play.api.libs.json.{Json, OFormat}

case class CreateTicketResponse ( ticket_id: Long )

object CreateTicketResponse {
  implicit val format: OFormat[CreateTicketResponse] = Json.format[CreateTicketResponse]
}