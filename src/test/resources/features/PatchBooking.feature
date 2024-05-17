Feature: Back end - Patch Reservations on the Platform

  Background:
    Given "Diego" creates successful reservation

  Scenario: Patch reservation information
    When patch the information of a reservation created
    Then can see the new reservation information

  Scenario: Patch the information of a reservation with a non-existent ID
    When patch information for a non-existing reservation
    Then should be able to see the message "Method Not Allowed"

  Scenario: Patch reservation information without sending ID
    When patch reservation information without sending the ID
    Then should be able to see the message "Not Found"