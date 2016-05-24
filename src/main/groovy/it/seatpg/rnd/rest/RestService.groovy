package it.seatpg.rnd.rest

/**
 * This class accepts an Alexa webpage containing a list of site of one
 * specific country and it returns these URLs as a list.
 */
final class RestService {
  private final String baseURL
  private final String pathParams

  /**
   * Primary Constructor. Accept the url corresponding to the remote
   * SpdyCheckSite.org service
   * <p>
   * @param burl  the url of the SpdyCheckSite rest service which may be queryed
   * @param pparms  the url of the SpdyCheckSite rest service which may be queryed
   */
  RestService( Map<String,?> m) {
    this.baseURL    = m.base_url
    this.pathParams = m.p_params
  }

  /**
   * This method build the query string of the rest.
   * Using the inject incremental method build the query string traversing all
   * the couples key, value of the map passed as parameter.
   * <p>
   * @return the encoded query String
   */
  private String queryString(Map<String,?> queryParameters) {
    queryParameters.inject('') { qs, k, v ->
      if ( qs ) { qs += '&' }
      qs += "${k}=${URLEncoder.encode(v as String)}"
    }
  }
  /**
   * This method calls the remote service passed as an
   * This method parses the HTML of the page to extract the url list and
   * returns it as a List of {@link RankedWebServer} objects.
   * <p>
   * @return a list of {@link RankedWebServer} objects representing a site with
   * its rank
   */
  String result(Map<String,?> queryParameters) throws IOException {
    String queryString = this.queryString(queryParameters)
    URL restUrl = new URL( this.baseURL + this.pathParams + '?' +  queryString )
    restUrl.text
  }
}
