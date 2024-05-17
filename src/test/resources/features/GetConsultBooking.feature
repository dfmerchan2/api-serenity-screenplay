Feature: Back end - Consult Reservations on the Platform

  Background:
    Given "Diego" creates successful reservation

  Scenario: Consult an existing reservation by ID
    When requests to consult the reservation by ID
    Then should be able to see the reservation information

  Scenario: Consult existing reservations on the platform
    When consult existing reservations on the platform
    Then should be able to see the id of the reservation created

  Scenario: Consult a non-existent reservation
    When consult the reservation with ID a non-existent
    Then the response should contain the error message "Not Found"