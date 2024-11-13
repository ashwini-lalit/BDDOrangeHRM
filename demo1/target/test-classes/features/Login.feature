@Login
Feature: OrangeHRM Login Functionality

#  Scenario: Successful login with valid credentials
#    Given user has loaded the OrangeHRM application
#    When user provides valid credentials
#    Then user should be able to login successfully

  Scenario: login with valid credentials step by step
    Given I am on the OrangeHRM login page
    When I enter username as "admin"
    And I enter password as "admin123"
    And I click on the login button
    Then I should see success message

  Scenario: Failed login with invalid credentials
    Given I am on the OrangeHRM login page
    When I enter username as "invalid"
    And I enter password as "invalid123"
    And I click on the login button
    Then I should see error message

