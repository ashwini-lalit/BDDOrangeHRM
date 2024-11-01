package com.digite.actions;

import com.digite.pages.EmployeePage;
import org.openqa.selenium.WebDriver;

/**
 * The Employee class provides methods to perform various actions related to employee management
 * on the EmployeePage.
 */
public class Employee {

    private EmployeePage employeePage;

    /**
     * Constructs an Employee object with the specified WebDriver.
     *
     * @param driver the WebDriver instance to interact with the EmployeePage
     */
    public Employee(WebDriver driver) {
        employeePage = new EmployeePage(driver);
    }

    /**
     * Navigates to the employee list page.
     */
    public void navigateToEmployeeList() {
        employeePage.clickPIMMenu();
    }

    /**
     * Creates a new employee with the provided details.
     *
     * @param firstName the first name of the employee
     * @param middleName the middle name of the employee
     * @param lastName the last name of the employee
     * @param employeeID the ID of the employee
     */
    public void createNewEmployee(String firstName, String middleName, String lastName, String employeeID) {
        employeePage.clickAddEmployee();
        employeePage.addEmployeeBasicDetails(firstName, middleName, lastName, employeeID);
    }

    /**
     * Adds login credentials for the employee.
     *
     * @param username the username for the employee's login
     * @param password the password for the employee's login
     */
    public void addEmployeeLoginCredentials(String username, String password) {
       employeePage.addEmployeeWithLoginDetails(username, password);
    }

    /**
     * Adds additional details for the employee.
     *
     * @param licenseNumber the license number of the employee
     * @param expiryDate the expiry date of the license
     * @param dob the date of birth of the employee
     * @param nationality the nationality of the employee
     * @param maritalStatus the marital status of the employee
     * @param gender the gender of the employee
     */
    public void addEmployeeAdditionalDetails(String licenseNumber, String expiryDate, String dob, String nationality, String maritalStatus, String gender) {
        employeePage.addEmployeeAdditionalDetails(licenseNumber, expiryDate, dob, nationality, maritalStatus, gender);
    }

    /**
     * Verifies if the employee with the specified name is created successfully.
     *
     * @param employeeName the name of the employee to verify
     * @return true if the employee is created successfully, false otherwise
     */
    public boolean verifyEmployeeCreated(String employeeName) {
        employeePage.clickPIMMenu();
        employeePage.searchEmployee(employeeName);
        return employeePage.isEmployeeDisplayed(employeeName);
    }
}
