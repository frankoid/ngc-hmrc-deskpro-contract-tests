package uk.gov.hmrc.ngchmrcdeskprocontract.http

import java.net.URL

import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.ngchmrcdeskprocontract.support.{ServicesConfig, TestWSHttp}

import scala.concurrent.{ExecutionContext, Future}

case class HttpRequests (wsClient: WSClient, config: ServicesConfig = new ServicesConfig){

  private val http: TestWSHttp = new TestWSHttp(wsClient)
  private val hmrcDeskproBaseUrl: URL = new URL(config.baseUrl("hmrc-deskpro"))
  private val createTicketUrl: URL = new URL(hmrcDeskproBaseUrl, "/deskpro/ticket")
  private val createFeedbackUrl: URL = new URL(hmrcDeskproBaseUrl, "/deskpro/feedback")

  def createTicket()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[Unit] = {
    val createTicketRequest = Json.parse(
      s"""
         |{
         |  "name": ???,
         |  "email": ???,
         |  "subject": ???,
         |  "message": ???,
         |  "referrer": ???,
         |  "javascriptEnabled": ???,
         |  "userAgent": ???,
         |  "authId": ???,
         |  "areaOfTax": ???,
         |  "sessionId": ???,
         |  "userTaxIdentifiers": {
         |    "nino": ???,
         |    "ctUtr": ???,
         |    "utr": ???,
         |    "vrn": ???,
         |    "empRef": ???
         |  }
         |}
       """.stripMargin
    )
    http.POST[JsValue, HttpRequests](createTicketUrl.toString, createTicketRequest).map { _ => () }
  }

  def createFeedback()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[Unit] = {
    val createFeedbackRequest = Json.parse(
      s"""{
        |  "name": ???,
        |  "email": ???,
        |  "subject": ???,
        |  "message": ???,
        |  "referrer": ???,
        |  "javascriptEnabled": ???,
        |  "userAgent": ???,
        |  "authId": ???,
        |  "areaOfTax": ???,
        |  "sessionId": ???,
        |  "userTaxIdentifiers": {
        |    "nino": ???,
        |    "ctUtr": ???,
        |    "utr": ???,
        |    "vrn": ???,
        |    "empRef": ???
        |  }
        |  "rating": ???
        |}
      """.stripMargin
    )
    http.POST[JsValue, HttpRequests](createFeedbackUrl.toString, createFeedbackRequest).map { _ => () }
  }
}
