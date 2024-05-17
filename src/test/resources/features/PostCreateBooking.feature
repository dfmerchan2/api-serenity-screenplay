Feature: Back end - Create reservation on the platform

  Scenario: Create a Successful Reservation on the Platform
    When "Diego" creates successful reservation
    Then the response should contain the information of the created reservation

  Scenario: Create a reservation by sending fields in null
    When "Diego" sends a reservation creation request with null values
    Then the response should have the message "Internal Server Error"