package com.montealegreluis.apiproblem;

import java.net.URI;

public enum Status {
  CONTINUE(100, "Continue", URI.create("https://tools.ietf.org/html/rfc7231#section-6.2.1")),
  SWITCHING_PROTOCOLS(
      101, "Switching Protocols", URI.create("https://tools.ietf.org/html/rfc7231#section-6.2.2")),
  PROCESSING(102, "Processing", URI.create("https://tools.ietf.org/html/rfc2518#section-10.1")),
  CHECKPOINT(
      103,
      "Checkpoint",
      URI.create("https://code.google.com/p/gears/wiki/ResumableHttpRequestsProposal")),
  OK(200, "OK", URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.1")),
  CREATED(
      201,
      "Created",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.2")),
  ACCEPTED(
      202,
      "Accepted",
      URI.create("http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.3")),
  NON_AUTHORITATIVE_INFORMATION(
      203,
      "Non-Authoritative Information",
      URI.create("https://tools.ietf.org/html/rfc7231#section-6.3.4")),
  NO_CONTENT(
      204,
      "No Content",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.5")),
  RESET_CONTENT(
      205,
      "Reset Content",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.6")),
  PARTIAL_CONTENT(
      206,
      "Partial Content",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.7")),
  MULTI_STATUS(207, "Multi-Status", URI.create("https://tools.ietf.org/html/rfc4918#section-13")),
  ALREADY_REPORTED(
      208, "Already Reported", URI.create("https://tools.ietf.org/html/rfc5842#section-7.1")),
  IM_USED(226, "IM Used", URI.create("https://tools.ietf.org/html/rfc3229#section-10.4.1")),
  MULTIPLE_CHOICES(
      300, "Multiple Choices", URI.create("https://tools.ietf.org/html/rfc7231#section-6.4.1")),
  MOVED_PERMANENTLY(
      301,
      "Moved Permanently",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.2")),
  FOUND(
      302,
      "Found",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.3")),
  SEE_OTHER(
      303,
      "See Other",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.4")),
  NOT_MODIFIED(
      304,
      "Not Modified",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.5")),
  USE_PROXY(
      305,
      "Use Proxy",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.6")),
  TEMPORARY_REDIRECT(
      307,
      "Temporary Redirect",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.8")),
  PERMANENT_REDIRECT(308, "Permanent Redirect", URI.create("https://tools.ietf.org/html/rfc7238")),
  BAD_REQUEST(
      400,
      "Bad Request",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.1")),
  UNAUTHORIZED(
      401,
      "Unauthorized",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.2")),
  PAYMENT_REQUIRED(
      402,
      "Payment Required",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.3")),
  FORBIDDEN(
      403,
      "Forbidden",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.4")),
  NOT_FOUND(
      404,
      "Not Found",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.5")),
  METHOD_NOT_ALLOWED(
      405,
      "Method Not Allowed",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.6")),
  NOT_ACCEPTABLE(
      406,
      "Not Acceptable",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.7")),
  PROXY_AUTHENTICATION_REQUIRED(
      407,
      "Proxy Authentication Required",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.8")),
  REQUEST_TIMEOUT(
      408,
      "Request Timeout",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.9")),
  CONFLICT(
      409,
      "Conflict",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.10")),
  GONE(
      410,
      "Gone",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.11")),
  LENGTH_REQUIRED(
      411,
      "Length Required",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.12")),
  PRECONDITION_FAILED(
      412,
      "Precondition Failed",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.13")),
  REQUEST_ENTITY_TOO_LARGE(
      413,
      "Request Entity Too Large",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.14")),
  REQUEST_URI_TOO_LONG(
      414,
      "Request-URI Too Long",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.15")),
  UNSUPPORTED_MEDIA_TYPE(
      415,
      "Unsupported Media Type",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.16")),
  REQUESTED_RANGE_NOT_SATISFIABLE(
      416,
      "Requested Range Not Satisfiable",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.17")),
  EXPECTATION_FAILED(
      417,
      "Expectation Failed",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.18")),
  I_AM_A_TEAPOT(
      418, "I'm a teapot", URI.create("https://tools.ietf.org/html/rfc2324#section-2.3.2")),
  UNPROCESSABLE_ENTITY(
      422, "Unprocessable Entity", URI.create("https://tools.ietf.org/html/rfc4918#section-11.2")),
  LOCKED(423, "Locked", URI.create("https://tools.ietf.org/html/rfc4918#section-11.3")),
  FAILED_DEPENDENCY(
      424, "Failed Dependency", URI.create("https://tools.ietf.org/html/rfc4918#section-11.4")),
  UPGRADE_REQUIRED(
      426, "Upgrade Required", URI.create("https://tools.ietf.org/html/rfc2817#section-6")),
  PRECONDITION_REQUIRED(
      428, "Precondition Required", URI.create("https://tools.ietf.org/html/rfc6585#section-3")),
  TOO_MANY_REQUESTS(
      429, "Too Many Requests", URI.create("https://tools.ietf.org/html/rfc6585#section-4")),
  REQUEST_HEADER_FIELDS_TOO_LARGE(
      431,
      "Request Header Fields Too Large",
      URI.create("https://tools.ietf.org/html/rfc6585#section-5")),
  UNAVAILABLE_FOR_LEGAL_REASONS(
      451,
      "Unavailable For Legal Reasons",
      URI.create("https://tools.ietf.org/html/rfc7725#section-3")),
  INTERNAL_SERVER_ERROR(
      500,
      "Internal Server Error",
      URI.create("https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.1")),
  ;

  private final int code;
  private final String reason;
  private final URI type;

  Status(int code, String reason, URI type) {
    this.code = code;
    this.reason = reason;
    this.type = type;
  }

  public int code() {
    return code;
  }

  public String reason() {
    return reason;
  }

  public URI type() {
    return type;
  }
}
