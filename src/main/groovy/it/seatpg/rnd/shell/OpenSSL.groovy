package it.seatpg.rnd.shell

import it.seatpg.rnd.http.HTTPCheck

/**
 * This class is responsible to scan a webserver for the http protocols
 * supported using the command line tool {@code openssl}.
 */
final class OpenSSL implements HTTPCheck {
  //Identity / Status
  private final String url
  private final ShellProcess proc

  /**
   * Secondary Constructor. Call the primary constructor passing a BashProcess
   * containing the openssl pipeline
   * <p>
   * @param url  the url of the remote site hosted on the webserver to scan
   */
  OpenSSL(String url) {
    this( url,
      new BashProcess(
        $/echo 'QUIT' |
        |openssl s_client -CApath /etc/ssl/certs -nextprotoneg '' -servername ${url} -connect ${url}:443 |&
        |grep -oP '(?<=^Protocols advertised by server: ).*'/$.stripMargin(),
        10 // seconds (default timeout)
      )
    )
  }
  /**
   * Primary Constructor.
   * <p>
   * @param url  the url of the remote site hosted on the webserver to scan
   * @param prc  a {@link ShellProcess} able to scan the url
   */
  OpenSSL(String url, ShellProcess prc) {
    this.url  = url
    this.proc = prc
  }

  /**
   * Split the command out of the openssl command to return a list of
   * protocols. If there is no protocols it returns the default one (http/1.1)
   *
   * @return a list of protocols supported as strings
   */
  @Override
  List<String> protocols() {
    this.proc.output().split(', ') ?: ['http/1.1']
  }
}
