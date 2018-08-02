/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ngchmrcdeskprocontract.hmrcdeskpro

import org.scalatest.{AsyncWordSpec, Matchers}
import play.api.test.{DefaultAwaitTimeout, FutureAwaits}
import uk.gov.hmrc.ngchmrcdeskprocontract.scalatest.WSClientSpec

class HmrcDeskproFeedbackSpec extends AsyncWordSpec
  with Matchers
  with FutureAwaits
  with DefaultAwaitTimeout
  with WSClientSpec {

  "/deskpro/feedback" should {

    "return 200 with ticket_id for successful ticket create" in {
      ???
    }
  }
}
