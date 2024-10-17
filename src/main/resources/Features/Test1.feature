Feature: Registration
  Background: Launch the application url
    Given I launch the browser

#  @RegisterPositive
#  Scenario: Register with given TestData
#    When I click on "Register"
#    Then I enter all the required details
#      |firstName|
#      |lastName |
#      |street   |
#      |city     |
#      |state    |
#      |zipCode   |
#      |phone     |
#      |ssn       |
#      |username  |
#      |password  |
#      |confirmpassword|
#    And click on "Register" button
#    Then I should see the "Success" message
#    Then I validate the "FullName" of the logged in user

  @SuccessfulRegistration
  Scenario: Successful registration
    Given I am on the "Parabank" homepage
    Then I autogenerate "Username" and "Password"
    When I click on the "Register" link
    And I fill in "First Name" with "testData"
    And I fill in "Last Name" with "testData"
    And I fill in "Address" with "testData"
    And I fill in "City" with "testData"
    And I fill in "State" with "testData"
    And I fill in "Zip Code" with "testData"
    And I fill in "Phone" with "testData"
    And I fill in "SSN" with "testData"
    And I fill in "Username" with "testData"
    And I fill in "Password" with "testData"
    And I fill in "Confirm Password" with "testData"
    When I click the "Register" button
    Then I should see a confirmation message "Your account was created successfully."

  @RegistrationWithBlankFields
  Scenario: Registration with missing fields
    Given I am on the "Parabank" homepage
    When I click on the "Register" link
    And I leave "Username" blank
    And I fill in "Password" with "Password123"
    When I click the "Register" button
    Then I should see an error message "Username is required."