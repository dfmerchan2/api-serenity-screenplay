Feature: Back end - Authentication Token Generation

  Scenario: Successful token generation with valid credentials
    When "Diego" login with username "admin" and password "password123"
    Then should see the token generated successfully

  Scenario Outline: Create token with incorrect credentials
    When "Diego" login with username "<user>" and password "<password>"
    Then should see the error message "Bad credentials"
    Examples:
      | user           | password          |
      | adminIncorrect | password123       |
      | admin          | passwordIncorrect |