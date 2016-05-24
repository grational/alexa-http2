package it.seatpg.rnd.printers

/**
 * This class is used to build a CSV representation of an object
 */
class CsvMedia implements Media {

  private final Map accumulator
  private final String fieldsSeparator = ','

  /**
   * Secondary constructor
   */
  CsvMedia() {
    this([:])
  }

  /**
   * Primary constructor
   *
   * @param acc  a LinkedHashMap used to store the couples (key, value)
   */
  CsvMedia(Map acc) {
    this.accumulator = acc
  }

  /**
   * This method load a couple (key, value) into the class accumulator
   *
   * @param name  a LinkedHashMap used to store the couples (key, value)
   * @param value  a LinkedHashMap used to store the couples (key, value)
   * @return Media  return an object CsvMedia that contains all the couples
   */
  @Override
  Media with(String name, String value) {
    this.accumulator.put(name, value)
    new CsvMedia(this.accumulator)
  }

  /**
   * Returns a CSV representation of all the keys
   *
   * @return String  a csv row composed by the keys in the accumulator
   */
  String header() {
    this.accumulator.keySet().join(fieldsSeparator)
  }

  /**
   * Returns a CSV representation of all the values
   *
   * @return String  a csv row composed by the values in the accumulator
   */
  String row() {
    this.accumulator.values().join(fieldsSeparator)
  }
}
