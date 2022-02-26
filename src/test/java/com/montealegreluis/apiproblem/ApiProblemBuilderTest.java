package com.montealegreluis.apiproblem;

import static com.montealegreluis.apiproblem.ApiProblemBuilder.aProblem;
import static com.montealegreluis.apiproblem.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.montealegreluis.assertions.IllegalArgumentException;
import java.net.URI;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

final class ApiProblemBuilderTest {
  @Test
  void it_prevents_creating_a_problem_without_a_title() {
    assertThrows(IllegalArgumentException.class, () -> aProblem().build());
  }

  @Test
  void it_prevents_creating_a_problem_without_a_status() {
    assertThrows(IllegalArgumentException.class, () -> aProblem().withTitle("Not Found").build());
  }

  @Test
  void it_prevents_creating_a_problem_without_a_type() {
    assertThrows(
        IllegalArgumentException.class,
        () -> aProblem().withTitle("Not Found").withStatus(404).build());
  }

  @Test
  void it_creates_a_problem_with_minimum_information() {
    var problem = ApiProblem.from(INTERNAL_SERVER_ERROR);

    assertEquals(
        problem,
        aProblem()
            .withTitle(INTERNAL_SERVER_ERROR.reason())
            .withStatus(INTERNAL_SERVER_ERROR.code())
            .withType(INTERNAL_SERVER_ERROR.type())
            .build());
  }

  @Test
  void it_creates_a_problem_with_details() {
    var details = "Cannot find item, 123 is not a valid identifier";

    var problem =
        aProblem()
            .withTitle(UNPROCESSABLE_ENTITY.reason())
            .withStatus(UNPROCESSABLE_ENTITY.code())
            .withType(UNPROCESSABLE_ENTITY.type())
            .withDetails(details)
            .build();

    assertEquals(UNPROCESSABLE_ENTITY.reason(), problem.getTitle());
    assertEquals(UNPROCESSABLE_ENTITY.code(), problem.getStatus());
    assertEquals(UNPROCESSABLE_ENTITY.type(), problem.getType());
    assertEquals(details, problem.getDetails());
  }

  @Test
  void it_prevents_creating_a_problem_with_blank_or_null_details() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            aProblem()
                .withTitle(UNPROCESSABLE_ENTITY.reason())
                .withStatus(UNPROCESSABLE_ENTITY.code())
                .withType(UNPROCESSABLE_ENTITY.type())
                .withDetails(" ")
                .build());

    assertThrows(
        IllegalArgumentException.class,
        () ->
            aProblem()
                .withTitle(UNPROCESSABLE_ENTITY.reason())
                .withStatus(UNPROCESSABLE_ENTITY.code())
                .withType(UNPROCESSABLE_ENTITY.type())
                .withDetails(null)
                .build());
  }

  @Test
  void it_creates_a_problem_with_instance() {
    var instance = URI.create("https://example.com/problem");

    var problem =
        aProblem()
            .withTitle(BAD_REQUEST.reason())
            .withStatus(BAD_REQUEST.code())
            .withType(BAD_REQUEST.type())
            .withInstance(instance)
            .build();

    assertEquals(BAD_REQUEST.reason(), problem.getTitle());
    assertEquals(BAD_REQUEST.code(), problem.getStatus());
    assertEquals(BAD_REQUEST.type(), problem.getType());
    assertEquals(instance, problem.getInstance());
  }

  @Test
  void it_prevents_creating_a_problem_with_a_null_instance() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            aProblem()
                .withTitle(UNPROCESSABLE_ENTITY.reason())
                .withStatus(UNPROCESSABLE_ENTITY.code())
                .withType(UNPROCESSABLE_ENTITY.type())
                .withInstance(null)
                .build());
  }

  @Test
  void it_creates_a_problem_with_additional_properties() {
    var username = "jane.doe";

    var problem =
        aProblem()
            .withTitle(PRECONDITION_FAILED.reason())
            .withStatus(PRECONDITION_FAILED.code())
            .withType(PRECONDITION_FAILED.type())
            .with("username", username)
            .build();

    assertEquals(PRECONDITION_FAILED.reason(), problem.getTitle());
    assertEquals(PRECONDITION_FAILED.code(), problem.getStatus());
    assertEquals(PRECONDITION_FAILED.type(), problem.getType());
    assertEquals(1, problem.getAdditionalProperties().size());
    assertTrue(problem.getAdditionalProperties().containsKey("username"));
    assertEquals(username, problem.getAdditionalProperties().get("username"));
  }

  @DisplayName("it prevents adding properties_to_a_problem_using_reserved_properties")
  @ParameterizedTest(name = "property={0}, value={1}")
  @MethodSource("propertiesProvider")
  void it_prevents_adding_properties_to_a_problem_using_reserved_properties(
      String propertyName, Object propertyValue) {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            aProblem()
                .withTitle(UNPROCESSABLE_ENTITY.reason())
                .withStatus(UNPROCESSABLE_ENTITY.code())
                .withType(UNPROCESSABLE_ENTITY.type())
                .with(propertyName, propertyValue)
                .build());
  }

  @Test
  void it_creates_a_problem_with_an_exception() {
    var exception = new RuntimeException("Something went wrong");

    var problem =
        aProblem()
            .withTitle(BAD_REQUEST.reason())
            .withStatus(BAD_REQUEST.code())
            .withType(BAD_REQUEST.type())
            .withException(exception)
            .build();

    assertEquals(BAD_REQUEST.reason(), problem.getTitle());
    assertEquals(BAD_REQUEST.code(), problem.getStatus());
    assertEquals(BAD_REQUEST.type(), problem.getType());
    assertEquals(1, problem.getAdditionalProperties().size());
    assertTrue(problem.getAdditionalProperties().containsKey("exception"));
  }

  @Test
  void it_prevents_creating_a_problem_with_a_null_exception() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            aProblem()
                .withTitle(BAD_REQUEST.reason())
                .withStatus(BAD_REQUEST.code())
                .withType(BAD_REQUEST.type())
                .withException(null)
                .build());
  }

  private static Stream<Arguments> propertiesProvider() {
    return Stream.of(
        Arguments.of("type", URI.create("https://example.com/problem")),
        Arguments.of("title", "Unprocessable Entity"),
        Arguments.of("status", 422),
        Arguments.of("details", "Input provided is invalid"),
        Arguments.of("instance", URI.create("https://example.com/instance")));
  }
}
