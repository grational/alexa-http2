package it.seatpg.rnd.http

/**
 * This interface defines objects that can report the HTTP versions used.
 */
interface HTTPCheck {

  /**
   * Report a list of protocols supported by the webserver
   * <p>
   * @return a list of String each one representing an http version
   */
  List<String> protocols()

  /**
   * Fake class used by tests to report an http2 enabled webserver
   */
  final class FakeHTTP2 implements HTTPCheck {
    @Override
    List<String> protocols() {
      // www.google.com
      ['h2', 'spdy/3.1', 'http/1.1']
    }
  }

  /**
   * Fake class used by tests to report a spdy (not http2) enabled webserver
   */
  final class FakeSPDY implements HTTPCheck {
    @Override
    List<String> protocols() {
      // web.telegram.org
      ['spdy/3.1', 'http/1.1']
    }
  }

  /**
   * Fake class used by tests to report a regular http/1.1 webserver
   */
  final class FakeHTTP1 implements HTTPCheck {
    @Override
    List<String> protocols() {
      // www.polito.it
      ['http/1.1']
    }
  }
}
