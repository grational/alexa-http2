package it.seatpg.rnd.printers

import spock.lang.Specification

/**
 * This class test the correct output of the CsvMedia class
 */
class CsvMediaSpec extends Specification {

  /**
   * Load 3 couples key,value then compare with the expected result for header
   * and row
   */
  def "test the csv printer"() {
    setup:
      Media csvPrinter = new CsvMedia()
    when:
      csvPrinter
        .with('first',  'v1')
        .with('second', 'v2')
        .with('third',  'v3')
      String csvHeader = csvPrinter.header()
      String csvRow    = csvPrinter.row()
    then:
      csvHeader == 'first,second,third'
      csvRow    == 'v1,v2,v3'
  }
}
