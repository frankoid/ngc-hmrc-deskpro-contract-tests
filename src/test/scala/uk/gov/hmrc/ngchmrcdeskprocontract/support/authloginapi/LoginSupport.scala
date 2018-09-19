package uk.gov.hmrc.ngchmrcdeskprocontract.support.authloginapi

import org.scalatest.compatible.Assertion
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.ngchmrcdeskprocontract.scalatest.WSClientSpec

import scala.concurrent.{ExecutionContext, Future}

trait LoginSupport {

  self:WSClientSpec =>

  def withLoggedInUser(nino: Nino)(assertionsF: HeaderCarrier => Future[Assertion])(implicit ec: ExecutionContext): Future[Assertion] = {
    httpRequests.governmentGatewayLoginFor(nino).flatMap(assertionsF)
  }
}