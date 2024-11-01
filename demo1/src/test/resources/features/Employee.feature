@Employee
Feature: Employee Management

  Background:
    Given admin logins to OrangeHRM
      | username | password |
      | Admin    | admin123 |

  @employee @addEmployee
  Scenario: Create new employee with valid details
    When they create new Employee
    And enter employee details
      | firstName  | middleName | lastName |
      | Jack     | Solen       | Gouzou   |
    Then employee should get successfully added
