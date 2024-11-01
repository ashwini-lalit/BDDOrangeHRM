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

public class EmployeePage {
    private WebDriver driver;
    private Properties properties;
    private WebDriverWait wait;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/pageObjects/Employee.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPIMMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("pim.menu")))).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("add.employee.button")))).click();
    }

    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("firstname.input")))).sendKeys(firstName);
    }

    public void setMiddleName(String middleName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("middlename.input")))).sendKeys(middleName);
    }

    public void setLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("lastname.input")))).sendKeys(lastName);
    }

    public void setEmployeeID(String employeeID) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("employeeID.input")))).sendKeys(employeeID);
    }

    public void enableLoginDetails() {
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("toggle.login.details"))));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", radioButton);
    }

    public void setUsername(String username) {
        WebElement usernameField = driver.findElement(By.xpath(properties.getProperty("username.input")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+username+"';",usernameField);

    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("password.input")))).sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("confirm.password.input")))).sendKeys(password);
    }

    public void setLicenseNumber(String licenseNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseNumber.input")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseNumber.input")))).sendKeys(licenseNumber);
    }

    public void setLicenseExpiryDate(String expiryDate) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("licenseExpiryDate.input")))).sendKeys(expiryDate);
    }

    public void setDateOfBirth(String dob) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("dob.input")))).sendKeys(dob);
    }

    public void selectNationality(String nationality) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("nationality.dropdown")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][contains(.,'"+nationality+"')]"))).click();
    }

    public void selectMaritalStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("maritalStatus.dropdown")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and contains(.,'"+status+"')]"))).click();

    }

    public void selectMaleGender() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("gender.male.radio")))).click();
    }

    public void selectFemaleGender() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("gender.female.radio")))).click();
    }

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

    public void addEmployeeBasicDetails(String firstName, String middleName, String lastName, String employeeID) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
      //  setEmployeeID(employeeID);
        clickSave();
    }

    public void addEmployeeWithLoginDetails(String username, String password) {
        enableLoginDetails();
        setUsername(username);
        setPassword(password);
        setConfirmPassword(password);
        clickSave();
    }

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

    public void searchEmployee(String employeeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("employee.search.input")))).sendKeys(employeeName);
        driver.findElement(By.xpath(properties.getProperty("search.button"))).click();
    }

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

