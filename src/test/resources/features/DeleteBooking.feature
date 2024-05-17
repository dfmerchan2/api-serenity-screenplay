Feature: Back end - Delete reservation on the platform

  Background:
    Given "Diego" creates successful reservation

  Scenario: Delete an existing reservation
    When deletes the created reservation
    Then should see the response "Created" when deleting the reservation

  Scenario: Delete a non-existent reservation
    When delete a reservation that does not exist
    Then should see the response "Method Not Allowed" when deleting the reservation

  Scenario: Delete a reservation without sending the ID
    When sent the request to delete a reservation without sending the ID
    Then should see the response "Not Found" when deleting the reservation
