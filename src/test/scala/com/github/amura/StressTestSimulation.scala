package com.github.amura

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class StressTestSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources(WhiteList(""".*localhost:8090.*"""), BlackList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("en-US,en;q=0.8,id;q=0.6")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")

	val headers_0 = Map("Postman-Token" -> "1bb14537-a11b-b203-3bc7-5cc88ba2c419")

	val uri1 = "http://localhost:8090/v1/hello"

	val scn = scenario("StressTestSimulation")
		  .repeat(10000) {
        exec(http("request_0")
          .get("/v1/hello")
          .headers(headers_0))
      }

	setUp(
		scn
			.inject(atOnceUsers(100))
	).protocols(httpProtocol)
}