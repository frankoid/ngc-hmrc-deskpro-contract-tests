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

package uk.gov.hmrc.ngchmrcdeskprocontract.support

import com.typesafe.config.Config
import play.api.libs.ws.WSClient
import uk.gov.hmrc.http._
import uk.gov.hmrc.http.hooks.HttpHook
import uk.gov.hmrc.play.http.ws.WSHttp

/**
  * Uses the supplied WSClient instead of trying to get one from the running Play
  * application.
  *
  * For use e.g. in tests where there is no running Play application
  */
class TestWSHttp(override val wsClient: WSClient) extends HttpGet with HttpPut with HttpPost with HttpDelete with HttpPatch with WSHttp {
  override val hooks: Seq[HttpHook] = NoneRequired
  override lazy val configuration: Option[Config] = None
}