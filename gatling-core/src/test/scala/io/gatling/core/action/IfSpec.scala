/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.core.action

import akka.testkit._
import io.gatling.AkkaSpec

import io.gatling.core.result.writer.DataWriters
import io.gatling.core.session.Session
import io.gatling.core.session.el.El

class IfSpec extends AkkaSpec {

  "If" should "evaluate the condition using the session and send the session to one of the two actors only" in {
    val condition = "${condition}".el[Boolean]
    val baseSession = Session("scenario", "userId")

    val thenActorProbe = TestProbe()
    val elseActorProbe = TestProbe()
    val dataWriterProbe = TestProbe()
    val dataWriters = new DataWriters(system, List(dataWriterProbe.ref))

    val ifAction = TestActorRef(If.props(condition, thenActorProbe.ref, elseActorProbe.ref, dataWriters, self))

    val sessionWithTrueCondition = baseSession.set("condition", true)
    ifAction ! sessionWithTrueCondition
    thenActorProbe.expectMsg(sessionWithTrueCondition)
    elseActorProbe.expectNoMsg()

    val sessionWithFalseCondition = baseSession.set("condition", false)
    ifAction ! sessionWithFalseCondition
    thenActorProbe.expectNoMsg()
    elseActorProbe.expectMsg(sessionWithFalseCondition)
  }
}
