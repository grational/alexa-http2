package it.seatpg.rnd

import static groovyx.gpars.GParsPool.withPool

import groovy.util.logging.Slf4j

import it.seatpg.rnd.web.AlexaPage
import it.seatpg.rnd.web.RankingSitePage
import it.seatpg.rnd.printers.Media
import it.seatpg.rnd.printers.CsvMedia

/**
 * This class is used as an entry point for the entire application.
 * <p>
 * Initially i've used an external script to accomplish this task but an internal
 * class has several advantages:
 * <ul>
 * <li>never leave the main session of coding
 * <li>automatic code check by eclim as a regular class
 * <li>lint check (codenarc)
 * <li>execute with gradle through the 'application' plugin (as simple as gradle run)
 * </ul>
 */
@Slf4j
class PLauncher {

  /**
   * Entry point of the application
   */
  static void main(String... args) {

    String        urlHead   = 'http://www.alexa.com/topsites/countries;'
    String        urlTail   = '/IT'
    // each page contains 25 site:
    // 0:[1-25], 1:[26-50], 2:[51-75], 3:[76-100]
    List<Integer> range     = [0, 1, 2, 3]
    String        csvHeader = 'rank,url,HTTP2,SPDY'
    List<String>  ranking   = []

    withPool {

      range.eachParallel { current ->

        String alexaUrl = "${urlHead}${current}${urlTail}"
        RankingSitePage rankingPage = new AlexaPage(alexaUrl)

        withPool(10) {
          rankingPage.rankings().makeConcurrent().each { website ->
            Media csvPrinter = website.print( new CsvMedia() )
            ranking << csvPrinter.row()
          } // close rankingPage.rankings().each
        } // close withAnotherPool
      } // close range.each
    } // close withPool

    log.info csvHeader
    // sort rankings and print
    final String FS = ','
    ranking.sort { a, b ->
      (a.split(FS).first() as Integer) <=> (b.split(FS).first() as Integer)
    }.each { log.info it }
  }
}
