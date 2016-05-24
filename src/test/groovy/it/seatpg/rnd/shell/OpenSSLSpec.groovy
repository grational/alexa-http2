package it.seatpg.rnd.shell

import spock.lang.Specification
import it.seatpg.rnd.http.HTTPCheck

/**
 * This class check the OpenSsl class against 3 types of fake
 * webservers.
 */
class OpenSSLSpec extends Specification {

  /*
   * Test against a fake HTTP2 website
   */
  def "Test HTTP2 site"() {
    setup:
      HTTPCheck check = new HTTPCheck.FakeHTTP2()
    when:
      List<String> result = check.protocols()
    then:
      result.any { it =~ '^h2' }
  }

  /*
   * Test against a fake SPDY (not HTTP2) website
   */
  def "Test SPDY site"() {
    setup:
      HTTPCheck check = new HTTPCheck.FakeSPDY()
    when:
      List<String> result = check.protocols()
    then:
      result.any { it =~ '^spdy' }
  }

  /*
   * Test against a regular HTTP/1.1 website
   */
  def "Test HTTP1.1 site"() {
    setup:
      HTTPCheck check = new HTTPCheck.FakeHTTP1()
    when:
      List<String> result = check.protocols()
    then:
      result.any { it =~ '^http/1.1$' }
  }
}
