package com.montealegreluis.apiproblem;

import com.montealegreluis.assertions.Assert;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiProblemBuilder {
  private static final List<String> RESERVED_PROPERTIES =
      List.of("type", "title", "status", "details", "instance");
  private String title;
  private Integer status;
  private URI type;
  private String details;
  private URI instance;
  private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

  public static ApiProblemBuilder aProblem() {
    return new ApiProblemBuilder();
  }

  public ApiProblemBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public ApiProblemBuilder withStatus(Integer status) {
    this.status = status;
    return this;
  }

  public ApiProblemBuilder withType(URI type) {
    this.type = type;
    return this;
  }

  public ApiProblemBuilder withDetails(String details) {
    Assert.notBlank(details, "Details cannot be blank or null");
    this.details = details;
    return this;
  }

  public ApiProblemBuilder withInstance(URI instance) {
    Assert.notNull(instance, "Instance cannot be null");
    this.instance = instance;
    return this;
  }

  public ApiProblemBuilder with(String additionalProperty, Object value) {
    Assert.notIn(additionalProperty, RESERVED_PROPERTIES, "%s is a reserved property");
    additionalProperties.put(additionalProperty, value);
    return this;
  }

  public ApiProblem build() {
    return new ApiProblem(title, status, type, details, instance, additionalProperties);
  }
}
