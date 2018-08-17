package uk.gov.hmrc.ngchmrcdeskprocontract.http

import java.net.URL

import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.ngchmrcdeskprocontract.hmrcdeskpro.CreateTicketResponse
import uk.gov.hmrc.ngchmrcdeskprocontract.support.authloginapi.AuthLoginApiConnector
import uk.gov.hmrc.ngchmrcdeskprocontract.support.{ServicesConfig, TestWSHttp}

import scala.concurrent.{ExecutionContext, Future}

case class HttpRequests(wsClient: WSClient, config: ServicesConfig = new ServicesConfig) {

  private val http: TestWSHttp = new TestWSHttp(wsClient)
  private val auth: AuthLoginApiConnector = new AuthLoginApiConnector(config, wsClient)
  private val hmrcDeskproBaseUrl: URL = new URL(config.baseUrl("hmrc-deskpro"))
  private val createGetHelpTicketUrl: URL = new URL(hmrcDeskproBaseUrl, "/deskpro/get-help-ticket")
  private val createFeedbackUrl: URL = new URL(hmrcDeskproBaseUrl, "/deskpro/feedback")

  def createGetHelpTicket()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[CreateTicketResponse] = {
    val createGetHelpTicketRequest = Json.parse(
      s"""
         |{
         |  "name": "TestName",
         |  "email": "testName@test.com",
         |  "subject": "Test Subject",
         |  "message": "Test Message",
         |  "referrer": "test-journey-id",
         |  "javascriptEnabled": "",
         |  "userAgent": "TestAgent",
         |  "authId": "",
         |  "areaOfTax": "",
         |  "sessionId": "",
         |  "service": "HTS"
         |}
       """.stripMargin
    )
    http.POST[JsValue, CreateTicketResponse](createGetHelpTicketUrl.toString, createGetHelpTicketRequest)
  }

  def createFeedback(nino: Nino)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[CreateTicketResponse] = {
    val createFeedbackRequest = Json.parse(
      s"""{
         |  "name": "TestName",
         |  "email": "testName@test.com",
         |  "subject": "Test Subject",
         |  "message": "Test Message",
         |  "referrer": "test-journey-id",
         |  "javascriptEnabled": "",
         |  "userAgent": "TestAgent",
         |  "authId": "",
         |  "areaOfTax": "",
         |  "sessionId": "",
         |  "userTaxIdentifiers": {
         |    "nino": "${nino.value}"
         |  },
         |  "rating": "5"
         |}
      """.stripMargin
    )
    http.POST[JsValue, CreateTicketResponse](createFeedbackUrl.toString, createFeedbackRequest)
  }

  def createInvalidFeedback()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[CreateTicketResponse] = {
    val createFeedbackRequest = Json.parse("""{}""")

    http.POST[JsValue, CreateTicketResponse](createFeedbackUrl.toString, createFeedbackRequest)
  }

  def createInvalidGetHelpTicket()(implicit hc: HeaderCarrier, ex: ExecutionContext): Future[CreateTicketResponse] = {
    val createGetHelpTicketRequest = Json.parse("""{}""")

    http.POST[JsValue, CreateTicketResponse](createGetHelpTicketUrl.toString, createGetHelpTicketRequest)
  }

  def governmentGatewayLoginFor(nino: Nino)(implicit rec: ExecutionContext): Future[HeaderCarrier] = {
    auth.governmentGatewayLogin(Some(nino))
  }
}
