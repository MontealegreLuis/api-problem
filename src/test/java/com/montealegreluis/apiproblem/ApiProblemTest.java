package com.montealegreluis.apiproblem;

import static org.junit.jupiter.api.Assertions.*;

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
}
