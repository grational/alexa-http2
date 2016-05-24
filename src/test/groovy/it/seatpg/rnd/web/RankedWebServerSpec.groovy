package it.seatpg.rnd.web

import spock.lang.Specification

/**
 * This spec tests 3 fake classes implementing an HTTP2, SPDY, HTTP/1.1
 * webserver.
 */
class RankedWebServerSpec extends Specification {

  def "test an HTTP2 webserver"() {
    setup:
      HTTPStatus server = new HTTPStatus.FakeHTTP2()
    when:
      Boolean h2Status   = server.http2()
      Boolean spdyStatus = server.spdy()
    then:
      h2Status   == true
      spdyStatus == false
  }

  def "test a SPDY webserver"() {
    setup:
      HTTPStatus server = new HTTPStatus.FakeSPDY()
    when:
      Boolean h2Status   = server.http2()
      Boolean spdyStatus = server.spdy()
    then:
      h2Status   == false
      spdyStatus == true
  }

  def "test an HTTP/1.1 webserver"() {
    setup:
      HTTPStatus server = new HTTPStatus.FakeHTTP1()
    when:
      Boolean h2Status   = server.http2()
      Boolean spdyStatus = server.spdy()
    then:
      h2Status   == false
      spdyStatus == false
  }
}
