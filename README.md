# API Problem

[![CI workflow](https://github.com/montealegreluis/api-problem/actions/workflows/ci.yml/badge.svg)](https://github.com/montealegreluis/api-problem/actions/workflows/ci.yml)
[![Release workflow](https://github.com/montealegreluis/api-problem/actions/workflows/release.yml/badge.svg)](https://github.com/montealegreluis/api-problem/actions/workflows/release.yml)
[![semantic-release: conventional-commits](https://img.shields.io/badge/semantic--release-conventionalcommits-e10079?logo=semantic-release)](https://github.com/semantic-release/semantic-release)

API Problem implements [application/problem+json](https://tools.ietf.org/html/rfc7807).
It comes with convenient factories and builders for most common use cases.

## Installation

1. [Authenticating to GitHub Packages](https://github.com/MontealegreLuis/api-problem/blob/main/docs/installation/authentication.md)
2. [Maven](https://github.com/MontealegreLuis/api-problem/blob/main/docs/installation/maven.md)
3. [Gradle](https://github.com/MontealegreLuis/api-problem/blob/main/docs/installation/gradle.md)

## Usage

### Creating problems

There are cases in which an HTTP status code is enough to convey the necessary information. 

```java
ApiProblem.from(Status.NOT_FOUND);
```

Will produce this:

```json
{
 "title": "Not Found",
 "status": 404,
 "type": "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.5"
}
```

As specified by [Predefined Problem Types](https://tools.ietf.org/html/rfc7807#section-4.2):

> The "about:blank" URI, when used as a problem type, indicates that the problem has no additional semantics beyond that of the HTTP status code.

In this case use the following factory.

```java
ApiProblem.witDefaultType(Status.NOT_FOUND);
```

It will produce this:

```json
{
 "title": "Not Found",
 "status": 404
}
```

### Problem Builder

Most of the time you'll need to define problem types that are unique to your application. 
The Problem Builder offers a fluent API to create problem instances without the need to create custom classes:

```java
ApiProblemBuilder.aProblem()
  .withType(URI.create("https://example.org/not-found"))
  .withTitle("Concert not found")
  .withStatus(Status.NOT_FOUND.code())
  .withDetails("Concert with ID 2ca332b9-3f69-4882-abc9-58f534217bdd cannot be found")
  .with("concert", "2ca332b9-3f69-4882-abc9-58f534217bdd")
  .build();
```

Producing the following response.

```json
{
  "type": "https://example.org/not-found",
  "title": "Concert not found",
  "status": 404,
  "details": "Concert with ID 2ca332b9-3f69-4882-abc9-58f534217bdd cannot be found",
  "concert": "2ca332b9-3f69-4882-abc9-58f534217bdd"
}
```

### Custom Problems

The final stage of customization is achieved by sub-classing `ApiProblem`. 

```java
public final class ConcertNotFoundProblem extends ApiProblem {
  private static final URI TYPE = URI.create("https://example.com/not-found");
  private final String concert;

  public ConcertNotFoundProblem(final String concert) {
    super("Concert not found", 404, TYPE, format("Concert with ID %s cannot be found", concert), null, null);
    this.product = product;
  }

  public String getConcert() {
    return concert;
  }
}
```

### Exceptions in Problem responses

It is also possible to include exception information in an API Problem response.

```java
aProblem()
  .from(INTERNAL_SERVER_ERROR)
  .withDetails(exception.getMessage())
  .withException(exception)
  .build());
```

The example above would be represented as JSON as follows.

```json
{
  "type": "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.1",
  "title": "Internal Server Error",
  "status": 500,
  "details": "For input string \"two\"",
  "exception": {
    "message": "For input string \"two\"",
    "class": "java.lang.NumberFormatException",
    "line": 65,
    "file": "NumberFormatException.java",
    "trace": [
      "java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)",
      "java.base/java.lang.Integer.parseInt(Integer.java:652)",
      "java.base/java.lang.Integer.parseInt(Integer.java:770)",
      "com.montealegreluis.activityfeed.Application.main(Application.java:10)"
    ]
  }
}
```

The response will include previous exceptions if available.

## Contribute

Please refer to [CONTRIBUTING](https://github.com/MontealegreLuis/api-problem/blob/main/CONTRIBUTING.md) for information on how to contribute to API Problem.

## License

Released under the [BSD-3-Clause](https://github.com/MontealegreLuis/api-problem/blob/main/LICENSE).
