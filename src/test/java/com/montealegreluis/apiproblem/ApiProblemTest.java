package com.montealegreluis.apiproblem;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import java.net.URI;
import org.junit.jupiter.api.Test;

final class ApiProblemTest {
  @Test
  void it_can_be_created_from_a_given_status() {
    var problem = ApiProblem.from(Status.NOT_FOUND);

    assertEquals(Status.NOT_FOUND.code(), problem.getStatus());
    assertEquals(Status.NOT_FOUND.type(), problem.getType());
    assertEquals(Status.NOT_FOUND.reason(), problem.getTitle());
  }

  @Test
  void it_can_be_created_with_a_default_type() {
    var problem = ApiProblem.witDefaultType(Status.CONFLICT);

    assertEquals(Status.CONFLICT.code(), problem.getStatus());
    assertEquals(ApiProblem.DEFAULT_TYPE, problem.getType());
    assertEquals(Status.CONFLICT.reason(), problem.getTitle());
  }

  @Test
  void it_prevents_creating_a_problem_without_a_title() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ApiProblem(null, 404, URI.create("https://example.com"), null, null, null));
  }

  @Test
  void it_prevents_creating_a_problem_without_a_status() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ApiProblem("Not Found", null, URI.create("https://example.com"), null, null, null));
  }

  @Test
  void it_prevents_creating_a_problem_without_a_type() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ApiProblem("Not Found", 404, null, null, null, null));
  }

  @Test
  void it_can_be_compared_to_another_problem() {
    var problem1 = ApiProblem.from(Status.FORBIDDEN);
    var problem2 = ApiProblem.witDefaultType(Status.FORBIDDEN);
    var problem3 =
        ApiProblemBuilder.aProblem()
            .withType(URI.create("https://example.org/not-found"))
            .withTitle("Concert not found")
            .withStatus(Status.NOT_FOUND.code())
            .withDetails("Concert with ID 2ca332b9-3f69-4882-abc9-58f534217bdd cannot be found")
            .with("concert", "2ca332b9-3f69-4882-abc9-58f534217bdd")
            .build();

    assertEquals(problem1, problem1);
    assertNotEquals(problem1, problem2);
    assertNotEquals(problem1, null);
    assertNotEquals(problem2, problem3);
  }
}
