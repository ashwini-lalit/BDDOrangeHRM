package com.digite.stepdefinitions;

import com.digite.actions.Employee;
import com.digite.actions.Login;
import com.digite.core.DriverManager;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class EmployeeSteps {
    private Employee employee;
    private Login login;
    private String fullName;

    public EmployeeSteps() {
        this.employee = new Employee(DriverManager.getDriver());
        this.login = new Login(DriverManager.getDriver());
    }

    @Given("admin logins to OrangeHRM")
    public void adminLogin(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        login.performLogin(username, password);
        Assert.assertTrue("Login failed - Dashboard not visible", login.successfulLogin());
    }

    @When("they create new Employee")
    public void theyCreateNewEmployee() {
        employee.navigateToEmployeeList();
    }

    @When("enter employee details")
    public void enterEmployeeDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String firstName = data.get(0).get("firstName");
        String middleName = data.get(0).get("middleName");
        String lastName = data.get(0).get("lastName");
        String employeeId = new Faker().number().digits(3);
        fullName = firstName + " " + middleName + " "+ lastName;
        employee.createNewEmployee(firstName, middleName, lastName, employeeId);
    }

    @Then("employee should get successfully added")
    public void verifyEmployeeAdded() {
        Assert.assertTrue("Employee not found in the list", employee.verifyEmployeeCreated(fullName));
    }
}