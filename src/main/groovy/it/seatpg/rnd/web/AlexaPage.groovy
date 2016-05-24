package it.seatpg.rnd.web

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * This class accepts an Alexa webpage containing a list of site of one
 * specific country and it returns these URLs as a list.
 */
final class AlexaPage implements RankingSitePage {
  private final String url

  /**
   * Primary Constructor. Accept the url corresponding to the remote Alexa
   * webpage
   * <p>
   * @param url  the url of the Alexa webpage containing the list of the top
   * url of a country
   */
  AlexaPage(String url) {
    this.url = url
  }

  /**
   * This method parses the HTML of the page to extract the url list and
   * returns it as a List of {@link RankedWebServer} objects.
   * <p>
   * @return a list of {@link RankedWebServer} objects representing a site with
   * its rank
   */
  List<RankedWebServer> rankings() {
    Document doc = Jsoup.connect(this.url).get()

    doc.select('li.site-listing').collect { el ->
      Integer rank = Integer.parseInt( el.select('div.count').text() )
      String  site = el.select('div.desc-container > p.desc-paragraph > a[href]').text().toLowerCase()
      new RankedWebServer(rank, site)
    }
  }

}
