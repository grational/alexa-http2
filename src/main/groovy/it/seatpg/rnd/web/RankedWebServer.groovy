package it.seatpg.rnd.web

import it.seatpg.rnd.http.HTTPCheck
import it.seatpg.rnd.shell.OpenSSL
import it.seatpg.rnd.printers.Media
import it.seatpg.rnd.rest.SpdyCheckSite

/**
 * This class represents a site (actually its webserver) with a rank of some
 * type (e.g, Alexa Site Ranking).
 * <p>
 * A RankedWebServer report its http2/spdy support and can print itself on
 * a Media of some type.
 */
final class RankedWebServer implements HTTPStatus {
  final private Integer   rank
  final private String    url
  final private HTTPCheck httpChecker

  /**
   * Secondary Constructor
   * <p>
   * @param rnk  is an Integer representing the rank position of the site
   * @param url  it's the actual url of the site
   */
  RankedWebServer(Integer rnk, String url) {
    this.rank = rnk
    this.url  = url
    //this.httpChecker = new SpdyCheckSite(url)
    this.httpChecker = new OpenSSL(url)
  }
  /**
   * Primary Constructor
   * <p>
   * @param rnk   is an Integer representing the rank position of the site
   * @param url   it's the actual url of the site
   * @param hchk  this is a process able to query the remote site for its http
   * support
   */
  RankedWebServer( Integer rnk,
                   String url,
                   HTTPCheck hchk ) {
    this.rank        = rnk
    this.url         = url
    this.httpChecher = hchk
  }

  /**
   * Tell if the webserver support the HTTP2 protocol or not
   * <p>
   * @return a Boolean with the support status of the protocol
   */
  Boolean http2() {
    httpChecker.protocols().any { it =~ '^h2' }
  }
  /**
   * Tell if the webserver support the SPDY protocol or not
   * <p>
   * @return a Boolean with the support status of the protocol
   */
  Boolean spdy() {
    httpChecker.protocols().any { it =~ '^spdy' }
  }

  /**
   * Print the relevant properties of the object on a Media object
   * <p>
   * @param media It's a Media object (e.g., CsvMedia, JsonMedia, XMLMedia)
   * @return the same object with the relevant properties impressed
   */
  Media print(Media media) {
    media.with('rank',  "${this.rank}")
         .with('url',   this.url)
         .with('HTTP2', "${this.http2()}")
         .with('SPDY',  "${this.spdy()}")
  }

  /**
   * Custom equals method to test the response of the class
   */
  @Override
  boolean equals(Object obj) {
    this.url == RankedWebServer.cast(obj).url
  }

  /**
   * Custom hashCode method to test the response of the class
   */
  @Override
  int hashCode() {
    this.url.hashCode()
  }
}
