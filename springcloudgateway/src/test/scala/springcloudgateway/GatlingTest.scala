package com.bytesville.microservices.demo.springcloudgateway

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GatlingTest extends Simulation {
  val httpConf = http
    .baseURL("http://localhost:8080")
  val scn = scenario("Sample")
    .exec(http("GetIP")
      .get("/getip"))
    .pause(100 milliseconds)
  setUp(scn.inject(rampUsers(50000) over(60 seconds)) .protocols(httpConf))
}

//class GatlingTest extends Simulation {
//  val httpConf = http
//    .baseURL("http://localhost:8765")
//  val scn = scenario("Sample")
//    .exec(http("GetIP")
//      .get("/api/myservice/ip"))
//    .pause(100 milliseconds)
//  setUp(scn.inject(rampUsers(5000) over(60 seconds)) .protocols(httpConf))
//}
