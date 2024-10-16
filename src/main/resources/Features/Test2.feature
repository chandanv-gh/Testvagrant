Feature: Registration and Login
  Background: Launch the application url
    Given I launch the browser and navigate to the application url

  @RegisterNegative
  Scenario: Register with given TestData
    When I click on "Register"
    Then I enter all the required details
      |firstName|
      |lastName |
      |street   |
      |city     |
      |state    |
      |zipCode   |
      |phone     |
      |ssn       |
    And click on "Register" button
    Then I should see the "UserAlreadyRegistered" message

  @LoginPositive
  Scenario: Login as the registered user
    Given I enter "Username,Password" of the "Registered" user
    And click on "Login" button
    Then I validate the "FullName" of the logged in user