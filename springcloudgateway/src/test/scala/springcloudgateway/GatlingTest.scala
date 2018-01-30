package com.bytesville.microservices.demo.springcloudgateway


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class GatlingTest extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080")
    //.headers(Map("auth"->"someauth"))// Here is the root for all relative URLs

  val scn = scenario("Sample")
    .exec(http("GetIP")
      .get("/getip"))
    .pause(2 milliseconds)
  setUp(scn.inject(rampUsers(1) over(5 seconds)) .protocols(httpConf))
}
