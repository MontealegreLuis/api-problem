package com.montealegreluis.apiproblem;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import org.junit.jupiter.api.Test;

final class StatusTest {
  @Test
  void it_knows_its_reason_phrase_code_and_type() {
    var status = Status.METHOD_NOT_ALLOWED;

    assertEquals(405, status.code());
    assertEquals("Method Not Allowed", status.reason());
    assertEquals(
        URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.6"),
        status.type());
  }
}
