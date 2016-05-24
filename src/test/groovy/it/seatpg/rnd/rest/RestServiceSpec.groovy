package it.seatpg.rnd.rest

import spock.lang.Specification
import groovy.json.JsonSlurper

/**
 * This class check the OpenSslHTTPCheck class against 3 types of fake
 * webservers.
 */
class RestServiceSpec extends Specification {

  /*
   * Test against a fake HTTP2 website
   */
  def "Test a json webservice"() {
    setup:
      RestService rest = new RestService (
                           base_url:'https://spdycheck.org',
                           p_params:'/Check.ashx'
                         )
      JsonSlurper js = new JsonSlurper()
    when:
      String site = 'www.google.com'
      Object json = js.parseText rest.result(host:site)
    then:
      json.Host          == site
      json.SupportsSPDY  == true
      json.SPDYProtocols == ['h2', 'spdy/3.1', 'http/1.1']
  }
}
