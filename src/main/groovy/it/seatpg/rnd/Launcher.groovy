package it.seatpg.rnd

import it.seatpg.rnd.web.AlexaPage
import it.seatpg.rnd.web.RankingSitePage
import it.seatpg.rnd.printers.Media
import it.seatpg.rnd.printers.CsvMedia

import groovy.util.logging.Slf4j

import java.security.SecureRandom

/**
 * This class is used as entry point for the entire application.
 * <p>
 * Initially i've used an external script to accomplish this task but an internal
 * class has several advantages:<ul>
 * <li>never leave the main session of coding
 * <li>automatic code check as the regular class
 * <li>lint check (codenarc)
 * <li>execute with gradle through the 'application' plugin</ul>
 */
@Slf4j
class Launcher {

  /**
   * Entry point of the application
   */
  static void main(String... args) {

    String        urlHead    = 'http://www.alexa.com/topsites/countries;'
    String        urlTail    = '/IT'
    // each page contains 25 site:
    // 0:[1-25], 1:[26-50], 2:[51-75], 3:[76-100]
    //List<Integer> range      = [0, 1, 2, 3]
    List<Integer> range      = [2, 3]
    String        csvHeader  = 'rank,url,HTTP2,SPDY'
    Integer       minSleep   = 60000  // 30s
    Integer       maxSleep   = 120000 // 60s

    // Load Net Disguise Configuration
    ConfigObject net = new ConfigSlurper().parse(
      this.getClass().getResourceAsStream(
        '/netdisguise.groovy'
      ).text
    )

    // print headers
    log.info csvHeader

    range.each { current ->

      String alexaUrl = "${urlHead}${current}${urlTail}"
      RankingSitePage rankingPage = new AlexaPage(alexaUrl)
      SecureRandom sr = new SecureRandom()

      rankingPage.rankings().each { website ->

        Integer u = sr.nextInt(net.userAgents.size())
        Integer p = sr.nextInt(net.proxies.size())
        //System.setProperty('http.agent', net.userAgents[i])
        //System.properties << [
          //'http.agent':net.userAgents[u],
          //'http.proxyHost':net.proxies[p],
          //'http.proxyPort':net.proxyDefaultPort
        //]
        //log.info "http.agent     -> ${net.userAgents[u]}"
        //log.info "http.proxyHost -> ${net.proxies[p]}"
        Media csvPrinter = website.print( new CsvMedia() )
        log.info csvPrinter.row()
        //sleep random(minSleep, maxSleep)
      } // close rankingPage.rankings().each
    } // close range.each
  }

  static Integer random(Integer min, Integer max) {
    SecureRandom random = new SecureRandom()
    random.nextInt ((max + 1) - min) + min
  }

}
