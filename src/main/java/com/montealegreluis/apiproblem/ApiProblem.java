package com.montealegreluis.apiproblem;

import com.montealegreluis.assertions.Assert;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ApiProblem implements Problem {
  private final String title;
  private final Integer status;
  private final URI type;
  private final String detail;
  private final URI instance;
  private final Map<String, Object> additionalProperties;

  public static ApiProblem from(Status status) {
    return new ApiProblem(status.reason(), status.code(), status.type(), null, null, null);
  }

  public static ApiProblem witDefaultType(Status status) {
    return new ApiProblem(status.reason(), status.code(), DEFAULT_TYPE, null, null, null);
  }

  public ApiProblem(
      String title,
      Integer status,
      URI type,
      String detail,
      URI instance,
      Map<String, Object> additionalProperties) {
    Assert.notBlank(title, "Title cannot be blank or null");
    this.title = title;
    Assert.notNull(status, "Status cannot be null");
    this.status = status;
    Assert.notNull(type, "Type cannot be null");
    this.type = type;
    this.detail = detail;
    this.instance = instance;
    this.additionalProperties =
        Optional.ofNullable(additionalProperties).orElseGet(LinkedHashMap::new);
  }

  @Override
  public URI getType() {
    return type;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public Integer getStatus() {
    return status;
  }

  @Override
  public String getDetail() {
    return detail;
  }

  @Override
  public URI getInstance() {
    return instance;
  }

  @Override
  public Map<String, Object> getAdditionalProperties() {
    return Collections.unmodifiableMap(additionalProperties);
  }
}
