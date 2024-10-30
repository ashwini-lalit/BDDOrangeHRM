package com.digite.stepdefinitions;

import com.digite.core.DriverManager;
import com.digite.pages.EmployeePage;
import com.digite.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class EmployeeSteps {
    private EmployeePage employeePage;
    private LoginPage loginPage;
    private String fullName;

    public EmployeeSteps() {
        this.employeePage = new EmployeePage(DriverManager.getDriver());
        this.loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Given("admin logins to OrangeHRM")
    public void adminLogin(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();

        Assert.assertTrue("Login failed - Dashboard not visible", loginPage.isDashboardVisible());
    }

    @When("they navigate to PIM module")
    public void navigateToPIM() {
        employeePage.clickPIMMenu();
    }

    @When("click on Add Employee button")
    public void clickAddEmployee() {
        employeePage.clickAddEmployee();
    }

    @When("enter employee details")
    public void enterEmployeeDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String firstName = data.get(0).get("firstName");
        String middleName = data.get(0).get("middleName");
        String lastName = data.get(0).get("lastName");

        fullName = firstName + " " + lastName;
        employeePage.enterEmployeeDetails(firstName, middleName, lastName);
    }

    @When("create login credentials")
    public void createLoginCredentials(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        employeePage.createLoginDetails(username, password);
        employeePage.saveEmployee();
    }

    @When("enter additional details")
    public void enterAdditionalDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String nationality = data.get(0).get("nationality");
        String maritalStatus = data.get(0).get("maritalStatus");
        String dateOfBirth = data.get(0).get("dateOfBirth");

        employeePage.enterPersonalDetails(nationality, maritalStatus, dateOfBirth);
    }

    @Then("employee should get successfully added")
    public void verifyEmployeeAdded() {
        employeePage.clickPIMMenu();
    }

    @Then("employee should be visible in employee list")
    public void verifyEmployeeInList() {
        employeePage.searchEmployee(fullName);
        Assert.assertTrue("Employee not found in the list", employeePage.isEmployeeDisplayed(fullName));
    }
}