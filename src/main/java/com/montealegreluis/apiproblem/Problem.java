package com.montealegreluis.apiproblem;

import java.net.URI;
import java.util.Map;

public interface Problem {
  URI DEFAULT_TYPE = URI.create("about:blank");

  /**
   * An absolute URI that identifies the problem type.
   *
   * <p>When this member is not present, its value is assumed to be "about:blank".
   */
  URI getType();

  /**
   * A short, human-readable summary of the problem type.
   *
   * <p>It SHOULD NOT change from occurrence to occurrence of the problem.
   */
  String getTitle();

  /** The HTTP status code generated by the origin server for this occurrence of the problem. */
  Integer getStatus();

  /** A human-readable explanation specific to this occurrence of the problem. */
  String getDetails();

  /** An absolute URI that identifies the specific occurrence of the problem. */
  URI getInstance();

  /**
   * Optional additional attributes of the problem.
   *
   * <p>Implementations can choose to ignore this in favor of concrete typed fields.
   */
  Map<String, Object> getAdditionalProperties();
}
