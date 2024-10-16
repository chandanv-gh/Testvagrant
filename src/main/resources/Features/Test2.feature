Feature: Registration and Login
  Background: Launch the application url
    Given I launch the browser and navigate to the application url

  @LoginNegative
  Scenario: Login with Blank username and Blank Password
    Given I enter "Username,Password" of the "Blank" user
    And click on "Login" button
    Then I should see the "Error" message

  @LoginPositive
  Scenario: Login as the registered user
    Given I enter "Username,Password" of the "Registered" user
    And click on "Login" button
    Then I validate the "FullName" of the logged in user

  @Home
  Scenario: Validate all the elements in the homepage
    Given I validate all the elements in the "Home" page