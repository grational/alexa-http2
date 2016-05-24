package it.seatpg.rnd.rest

import it.seatpg.rnd.http.HTTPCheck
import groovy.json.JsonSlurper

/**
 * This class accepts an Alexa webpage containing a list of site of one
 * specific country and it returns these URLs as a list.
 */
final class SpdyCheckSite implements HTTPCheck {
  private final RestService rest
  private final String site
  private final JsonSlurper jslurp

  /**
   * Secondaty Constructor. Accept the url corresponding to the remote
   * SpdyCheckSite.org service
   * <p>
   * @param rs  the url of the SpdyCheckSite rest service which may be queryed
   * @param st  the url of the SpdyCheckSite rest service which may be queryed
   */
  SpdyCheckSite(String st) {
    this (
      new RestService(
        base_url:'https://spdycheck.org',
        p_params:'/Check.ashx'
      ),
      st,
      new JsonSlurper()
    )
  }
  /**
   * Primary Constructor. Accept the url corresponding to the remote
   * SpdyCheckSite.org service
   * <p>
   * @param rs  the url of the SpdyCheckSite rest service which may be queryed
   * @param st  the url of the SpdyCheckSite rest service which may be queryed
   */
  SpdyCheckSite(RestService rs, String st, JsonSlurper js) {
    this.rest   = rs
    this.site   = st
    this.jslurp = js
  }

  /**
   * Call the remote webservice offered by the site spdycheck.org to check
   * the site passed in the constructor.
   * If there is no protocols it returns the default one (http/1.1)
   *
   * <p>
   * @return a list of protocols supported as strings
   */
  @Override
  List<String> protocols() {

    String result = this.rest.result(host:this.site)
    Object json   = this.jslurp.parseText(result)
    if ( json.excessive == true ) {
      throw new IOException('Too many request to spdycheck.org: game over!')
    }
    json.SPDYProtocols ?: ['http/1.1']
  }

}
