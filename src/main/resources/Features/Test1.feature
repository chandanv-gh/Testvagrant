Feature: Registration and Login
  Background: Launch the application url
    Given I launch the browser and navigate to the application url

  @RegisterPositive
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
    Then I should see the "Success" message
    Then I validate the "FullName" of the logged in user