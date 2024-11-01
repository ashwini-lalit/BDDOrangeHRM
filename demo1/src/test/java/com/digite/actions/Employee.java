package com.digite.actions;

import com.digite.pages.EmployeePage;
import org.openqa.selenium.WebDriver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {

    private EmployeePage employeePage;

    public Employee(WebDriver driver) {
        employeePage = new EmployeePage(driver);
    }

    public void navigateToEmployeeList() {
        employeePage.clickPIMMenu();
    }

    public void createNewEmployee(String firstName, String middleName, String lastName, String employeeID) {
        employeePage.clickAddEmployee();
        employeePage.addEmployeeBasicDetails(firstName, middleName, lastName, employeeID);
    }

    public void addEmployeeLoginCredentials(String username, String password) {
       employeePage.addEmployeeWithLoginDetails(username, password);
    }

    public void addEmployeeAdditionalDetails(String licenseNumber, String expiryDate, String dob, String nationality, String maritalStatus, String gender) {
        employeePage.addEmployeeAdditionalDetails(licenseNumber, expiryDate, dob, nationality, maritalStatus, gender);
    }

    public boolean verifyEmployeeCreated(String employeeName) {
        employeePage.clickPIMMenu();
        employeePage.searchEmployee(employeeName);
        return employeePage.isEmployeeDisplayed(employeeName);
    }
}
