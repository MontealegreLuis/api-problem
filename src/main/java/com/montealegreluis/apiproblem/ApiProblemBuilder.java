package com.montealegreluis.apiproblem;

import static com.montealegreluis.activityfeed.ExceptionContextFactory.contextFrom;

import com.montealegreluis.assertions.Assert;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiProblemBuilder {
  protected static final List<String> RESERVED_PROPERTIES =
      List.of("type", "title", "status", "detail", "instance");
  protected String title;
  protected Integer status;
  protected URI type;
  protected String detail;
  protected URI instance;
  protected final Map<String, Object> additionalProperties = new LinkedHashMap<>();

  public static ApiProblemBuilder aProblem() {
    return new ApiProblemBuilder();
  }

  public ApiProblemBuilder from(Status status) {
    title = status.reason();
    this.status = status.code();
    type = status.type();
    return this;
  }

  public ApiProblemBuilder witDefaultType(Status status) {
    title = status.reason();
    this.status = status.code();
    type = Problem.DEFAULT_TYPE;
    return this;
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

  public ApiProblemBuilder withDetail(String detail) {
    Assert.notBlank(detail, "Detail cannot be blank or null");
    this.detail = detail;
    return this;
  }

  public ApiProblemBuilder withInstance(URI instance) {
    Assert.notNull(instance, "Instance cannot be null");
    this.instance = instance;
    return this;
  }

  public ApiProblemBuilder withException(Throwable exception) {
    Assert.notNull(exception, "Exception cannot be null");
    additionalProperties.put("exception", contextFrom(exception));
    return this;
  }

  public ApiProblemBuilder with(String additionalProperty, Object value) {
    Assert.notIn(additionalProperty, RESERVED_PROPERTIES, "%s is a reserved property");
    additionalProperties.put(additionalProperty, value);
    return this;
  }

  public ApiProblem build() {
    return new ApiProblem(title, status, type, detail, instance, additionalProperties);
  }
}
