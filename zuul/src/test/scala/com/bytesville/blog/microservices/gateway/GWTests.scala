package com.bytesville.blog.microservices.gateway

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._



class GWTests extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080")
  //.headers(Map("auth"->"someauth"))// Here is the root for all relative URLs

  val scn = scenario("Sample")
    .exec(http("GetIP")
      .get("/getip"))
    .pause(100 milliseconds)
  setUp(scn.inject(rampUsers(1000) over(60 seconds)) .protocols(httpConf))
}