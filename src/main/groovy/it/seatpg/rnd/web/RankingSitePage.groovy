package it.seatpg.rnd.web

/**
 * A class implementing this interface qualify itself as a Ranking Webpage.
 * It has to report through the method ranking() its list of website with URL
 * and rank inside.
 */
interface RankingSitePage {

  /**
   * Return a list of couples of (rank, URL) contained in an object
   * RankedWebServer
   */
  List<RankedWebServer> rankings()

  /**
   * This is a fake implementation of a class implementing RankingSitePage.
   * Its purpose is to return a stable ranking list to make a stable test.
   */
  final class Fake implements RankingSitePage {
    @Override
    List<RankedWebServer> rankings() {
      List<RankedWebServer> list = []
      list << new RankedWebServer( 1, 'google.it')
      list << new RankedWebServer( 2, 'facebook.com')
      list << new RankedWebServer( 3, 'youtube.com')
      list << new RankedWebServer( 4, 'google.com')
      list << new RankedWebServer( 5, 'amazon.it')
      list << new RankedWebServer( 6, 'wikipedia.org')
      list << new RankedWebServer( 7, 'yahoo.com')
      list << new RankedWebServer( 8, 'libero.it')
      list << new RankedWebServer( 9, 'repubblica.it')
      list << new RankedWebServer(10, 'ebay.it')
      list << new RankedWebServer(11, 'bing.com')
      list << new RankedWebServer(12, 'live.com')
      list << new RankedWebServer(13, 'msn.com')
      list << new RankedWebServer(14, 'subito.it')
      list << new RankedWebServer(15, 'corriere.it')
      list << new RankedWebServer(16, 'tim.it')
      list << new RankedWebServer(17, 'twitter.com')
      list << new RankedWebServer(18, 'linkedin.com')
      list << new RankedWebServer(19, 'blastingnews.com')
      list << new RankedWebServer(20, 'mediaset.it')
      list << new RankedWebServer(21, 'gazzetta.it')
      list << new RankedWebServer(22, 'altervista.org')
      list << new RankedWebServer(23, 'paypal.com')
      list << new RankedWebServer(24, 'poste.it')
      list << new RankedWebServer(25, 'unicredit.it')
    }
  }
}
