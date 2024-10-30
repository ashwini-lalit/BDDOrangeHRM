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

public class LoginPage {
    private WebDriver driver;
    private Properties properties;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/pageObjects/login.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("login.username.input"))));
        usernameInput.sendKeys(username);
        //print the username to console
        System.out.println("Username------------------->: " + username);

    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("login.password.input"))));
        passwordInput.sendKeys(password);
        //print the password to console
        System.out.println("Password------------------->: " + password);
    }

    public void clickSubmit() {
        //print the submit button to console
        System.out.println("Submit Button------------------->: ");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(properties.getProperty("login.submit.button"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(properties.getProperty("login.submit.button")))));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("login.submit.button"))));
        submitButton.click();
    }

    public void navigateToLoginPage() {
        driver.get(properties.getProperty("login.page.url"));
    }

    public boolean isDashboardVisible() {
        try {
            //print the dashboard header to console
            System.out.println("Dashboard Header------------------->: ");

            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(properties.getProperty("login.dashboard.header"))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}