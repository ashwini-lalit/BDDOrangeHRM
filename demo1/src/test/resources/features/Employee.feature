@employee
Feature: Employee Management

  Background:
    Given admin logins to OrangeHRM
      | username | password |
      | Admin    | admin123 |

  @employee @addEmployee
  Scenario: Create new employee with valid details
    When they navigate to PIM module
    And click on Add Employee button
    And enter employee details
      | firstName  | middleName | lastName |
      | Seba      | Stalin     | Jodef    |
    And create login credentials
      | username      | password    |
      | seba.stalin166   | Test@123    |
    And enter additional details
      | nationality | maritalStatus | dateOfBirth |
      | Indian      | Single        | 1990-05-15  |
    Then employee should get successfully added
    And employee should be visible in employee list