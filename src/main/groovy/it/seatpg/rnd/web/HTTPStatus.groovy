package it.seatpg.rnd.web

/**
 * This interface defines an object capable of declaring its support status for
 * the http2 and spdy protocols (usually a webserver).
 */
interface HTTPStatus {
  Boolean http2()
  Boolean spdy()

  /**
   * Fake class modelling an http2 webserver
   */
  final class FakeHTTP2 implements HTTPStatus {
    @Override
    Boolean http2() {
      true
    }
    @Override
    Boolean spdy() {
      false
    }
  }

  /**
   * Fake class modelling an spdy (not http2) webserver
   */
  final class FakeSPDY implements HTTPStatus {
    @Override
    Boolean http2() {
      false
    }
    @Override
    Boolean spdy() {
      true
    }
  }

  /**
   * Fake class modelling a regular http/1.1 webserver
   */
  final class FakeHTTP1 implements HTTPStatus {
    @Override
    Boolean http2() {
      false
    }
    @Override
    Boolean spdy() {
      false
    }
  }
}
