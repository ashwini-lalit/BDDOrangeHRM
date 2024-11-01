package com.digite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The LoginPage class contains methods to interact with the login page of the application.
 */
public class LoginPage {
    private WebDriver driver;
    private Properties properties;
    private WebDriverWait wait;

    /**
     * Initializes a new instance of the LoginPage class.
     *
     * @param driver the WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadProperties();
    }

    /**
     * Loads the properties from the login page properties file.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/pageObjects/login.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enters the specified username into the username input field.
     *
     * @param username the username to enter
     */
    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("login.username.input"))));
        usernameInput.sendKeys(username);
    }

    /**
     * Enters the specified password into the password input field.
     *
     * @param password the password to enter
     */
    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("login.password.input"))));
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the submit button on the login page.
     */
    public void clickSubmit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(properties.getProperty("login.submit.button"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(properties.getProperty("login.submit.button")))));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("login.submit.button"))));
        submitButton.click();
    }

    /**
     * Navigates to the login page of the application.
     */
    public void navigateToLoginPage() {
        driver.get(properties.getProperty("login.page.url"));
    }

    /**
     * Verify is the user has successfully logged in by checking the Dashboard placeholder on main page
     *
     */
    public boolean isDashboardVisible() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(properties.getProperty("login.dashboard.header"))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}