package com.digite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The EmployeePage class provides methods to interact with the Employee page
 * in the web application. It includes methods to perform actions such as
 * navigating to the employee list, adding a new employee, and verifying
 * employee details.
 */
public class EmployeePage {
    private WebDriver driver;
    private Properties properties;
    private WebDriverWait wait;

    /**
     * Constructs an EmployeePage object with the specified WebDriver.
     *
     * @param driver the WebDriver instance to interact with the Employee page
     */
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadProperties();
    }

    /**
     * Loads the properties file containing the locators for the Employee page elements.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/pageObjects/Employee.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clicks on the PIM menu to navigate to the employee list.
     */
    public void clickPIMMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("pim.menu")))).click();
    }

    /**
     * Clicks on the Add Employee button to navigate to the add employee form.
     */
    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("add.employee.button")))).click();
    }

    /**
     * Sets the first name of the employee in the add employee form.
     *
     * @param firstName the first name of the employee
     */
    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("firstname.input")))).sendKeys(firstName);
    }

    /**
     * Sets the middle name of the employee in the add employee form.
     *
     * @param middleName the middle name of the employee
     */
    public void setMiddleName(String middleName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("middlename.input")))).sendKeys(middleName);
    }

    /**
     * Sets the last name of the employee in the add employee form.
     *
     * @param lastName the last name of the employee
     */
    public void setLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("lastname.input")))).sendKeys(lastName);
    }

    /**
     * Sets the employee ID in the add employee form.
     *
     * @param employeeID the employee ID
     */
    public void setEmployeeID(String employeeID) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("employeeID.input")))).sendKeys(employeeID);
    }

    /**
     * Enables the login details section in the add employee form.
     */
    public void enableLoginDetails() {
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("toggle.login.details"))));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", radioButton);
    }

    /**
     * Sets the username in the login details section of the add employee form.
     *
     * @param username the username for the employee login
     */
    public void setUsername(String username) {
        WebElement usernameField = driver.findElement(By.xpath(properties.getProperty("username.input")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+username+"';",usernameField);

    }

    /**
     * Sets the password in the login details section of the add employee form.
     *
     * @param password the password for the employee login
     */
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("password.input")))).sendKeys(password);
    }

    /**
     * Sets the confirm password in the login details section of the add employee form.
     *
     * @param password the password for the employee login
     */
    public void setConfirmPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("confirm.password.input")))).sendKeys(password);
    }

    /**
     * Sets the license number in the add employee form.
     *
     * @param licenseNumber the license number of the employee
     */
    public void setLicenseNumber(String licenseNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseNumber.input")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseNumber.input")))).sendKeys(licenseNumber);
    }

    /**
     * Sets the license expiry date in the add employee form.
     *
     * @param expiryDate the expiry date of the employee license
     */
    public void setLicenseExpiryDate(String expiryDate) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseExpiryDate.input")))).sendKeys(expiryDate);
    }

    /**
     * Sets the date of birth of the employee in the add employee form.
     *
     * @param dob the date of birth of the employee
     */
    public void setDateOfBirth(String dob) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("dob.input")))).sendKeys(dob);
    }

    /**
     * Select the nationality of the employee in the add employee form.
     * @param nationality the nationality of the employee
     */
    public void selectNationality(String nationality) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("nationality.dropdown")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][contains(.,'"+nationality+"')]"))).click();
    }

    /**
     * Select the marital status of the employee in the add employee form.
     *
     * @param status the marital status of the employee
     */
    public void selectMaritalStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("maritalStatus.dropdown")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and contains(.,'"+status+"')]"))).click();

    }

    /**
     * Select then gender of the employee in the add employee form.
     */
    public void selectMaleGender() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("gender.male.radio")))).click();
    }

    /**
     * Select then gender of the employee in the add employee form.
     */
    public void selectFemaleGender() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("gender.female.radio")))).click();
    }

    /**
     * Clicks the Save button to save the employee details.
     */
    public void clickSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

// Wait for loader to appear (optional, but sometimes helps)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-form-loader")));

// Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

// Wait for button and click
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button--secondary.orangehrm-left-space")));

// Use JavaScript click for added reliability
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        System.out.println("Save button clicked");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("save.button")))).click();
    }

    /**
     * Adds the basic details of an employee in the add employee form.
     *
     * @param firstName the first name of the employee
     * @param middleName the middle name of the employee
     * @param lastName the last name of the employee
     * @param employeeID the employee ID
     */
    public void addEmployeeBasicDetails(String firstName, String middleName, String lastName, String employeeID) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
      //  setEmployeeID(employeeID);
        clickSave();
    }

    /**
     * Adds the login details of an employee in the add employee form.
     *
     * @param username the username for the employee login
     * @param password the password for the employee login
     */
    public void addEmployeeWithLoginDetails(String username, String password) {
        enableLoginDetails();
        setUsername(username);
        setPassword(password);
        setConfirmPassword(password);
        clickSave();
    }

    /**
     * Adds the additional details of an employee in the add employee form.
     *
     * @param licenseNumber the license number of the employee
     * @param expiryDate the expiry date of the employee license
     * @param dob the date of birth of the employee
     * @param nationality the nationality of the employee
     * @param maritalStatus the marital status of the employee
     * @param gender the gender of the employee - Male or Female
     */
    public void addEmployeeAdditionalDetails(String licenseNumber, String expiryDate, String dob, String nationality, String maritalStatus,String gender) {
        setLicenseNumber(licenseNumber);
        setLicenseExpiryDate(expiryDate);
        setDateOfBirth(dob);
        selectNationality(nationality);
        selectMaritalStatus(maritalStatus);
        if (gender == "male")
            selectMaleGender();
        else
            selectFemaleGender();
        clickSave();
    }

    /**
     * Searches for an employee by name in the employee list.
     *
     * @param employeeName the name of the employee to search for
     */
    public void searchEmployee(String employeeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("employee.search.input")))).sendKeys(employeeName);
        driver.findElement(By.xpath(properties.getProperty("search.button"))).click();
    }

    /**
     * Verifies if the employee is displayed in the employee list.
     *
     * @param fullName the full name of the employee
     * @return true if the employee is displayed, false otherwise
     */
    public boolean isEmployeeDisplayed(String fullName) {
        try {
            String xpathExpression = properties.getProperty("employee.list.name") + "'" + fullName+"']";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

