package it.seatpg.rnd.web

import spock.lang.Specification

/**
 * This spec check the output of a fake Alexa webpage against
 * a static list of website
 */
class AlexaPageSpec extends Specification {

  /**
   * Test the result against a static list of the first 25 italian website
   * listed by Alexa on 25 April 2016
   */
  def "get 25 top italian sites"() {
    given:
      RankingSitePage fake = new RankingSitePage.Fake()
      List<RankedWebServer> result = fake.rankings()
    expect:
      result[index] == new RankedWebServer((index + 1), site)
    where:
      index | site
      0     | 'google.it'
      1     | 'facebook.com'
      2     | 'youtube.com'
      3     | 'google.com'
      4     | 'amazon.it'
      5     | 'wikipedia.org'
      6     | 'yahoo.com'
      7     | 'libero.it'
      8     | 'repubblica.it'
      9     | 'ebay.it'
      10    | 'bing.com'
      11    | 'live.com'
      12    | 'msn.com'
      13    | 'subito.it'
      14    | 'corriere.it'
      15    | 'tim.it'
      16    | 'twitter.com'
      17    | 'linkedin.com'
      18    | 'blastingnews.com'
      19    | 'mediaset.it'
      20    | 'gazzetta.it'
      21    | 'altervista.org'
      22    | 'paypal.com'
      23    | 'poste.it'
      24    | 'unicredit.it'
  }
}
