package it.seatpg.rnd.rest

import spock.lang.Specification

/**
 * This class check the OpenSslHTTPCheck class against 3 types of fake webservers.
 */
class SpdyCheckSiteSpec extends Specification {

  /*
   * Test against a fake HTTP2 website
   */
  def "Test HTTP2 site"() {
    setup:
      SpdyCheckSite check = new SpdyCheckSite('www.google.com')
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
      SpdyCheckSite check = new SpdyCheckSite('blog.liip.ch')
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
      SpdyCheckSite check = new SpdyCheckSite('www.polito.it')
    when:
      List<String> result = check.protocols()
    then:
      result.any { it =~ '^http/1.1$' }
  }
}
