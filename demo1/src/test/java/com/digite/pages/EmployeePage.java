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

    public void enterEmployeeDetails(String firstName, String middleName, String lastName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("firstname.input")))).sendKeys(firstName);
        driver.findElement(By.xpath(properties.getProperty("middlename.input"))).sendKeys(middleName);
        driver.findElement(By.xpath(properties.getProperty("lastname.input"))).sendKeys(lastName);
    }

    public void createLoginDetails(String username, String password) {
        WebElement element = driver.findElement(By.xpath(properties.getProperty("toggle.login.details")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("username.input")))).sendKeys(username);
        driver.findElement(By.xpath(properties.getProperty("password.input"))).sendKeys(password);
        driver.findElement(By.xpath(properties.getProperty("confirm.password.input"))).sendKeys(password);
    }

    public void saveEmployee() {
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath(properties.getProperty("save.button")))).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

    }

    public void enterPersonalDetails(String nationality, String maritalStatus, String dob) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(properties.getProperty("nationality.dropdown")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'] and text()='" + nationality + "']"))).click();

        driver.findElement(By.xpath(properties.getProperty("marital.status.dropdown"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'] and text()='" + maritalStatus + "']"))).click();

        driver.findElement(By.xpath(properties.getProperty("dob.input"))).sendKeys(dob);
        driver.findElement(By.xpath(properties.getProperty("gender.male.radio"))).click();

        driver.findElement(By.xpath(properties.getProperty("save.personal.details"))).click();
    }

    public void searchEmployee(String employeeName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("employee.search.input")))).sendKeys(employeeName);
        driver.findElement(By.xpath(properties.getProperty("search.button"))).click();
    }

    public boolean isEmployeeDisplayed(String fullName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(properties.getProperty("employee.list.name"))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

