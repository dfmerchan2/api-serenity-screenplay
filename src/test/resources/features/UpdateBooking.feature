Feature: Back end - Update Reservations on the Platform

  Background:
    Given "Diego" creates successful reservation

  Scenario: Update reservation information
    When update the information of a reservation created
    Then the response must contain the new reservation information

  Scenario: Update the information of a reservation with a non-existent ID
    When to update information for a non-existing reservation
    Then the service response should have the message "Method Not Allowed"

  Scenario: Update reservation information without sending ID
    When to update reservation information without sending the ID
    Then the service response should have the message "Not Found"

  Scenario: Update reservation information sending fields in null
    When to update reservation information by sending null values
    Then the service response should have the message "Bad Request"
