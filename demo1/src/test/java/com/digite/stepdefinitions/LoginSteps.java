package com.digite.stepdefinitions;

import com.digite.core.DriverManager;
import com.digite.core.WebDriverFactory;
import com.digite.actions.Login;
import com.digite.pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private Login login;
    private WebDriverWait wait;
    private Properties properties;

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/user.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.createDriver();
        DriverManager.setDriver(driver);
        loginPage = new LoginPage(driver);
        login = new Login(driver);
        loadProperties();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("user has loaded the OrangeHRM application")
    public void userHasLoadedTheOrangeHRMApplication() {
        loginPage.navigateToLoginPage();
    }

    @When("user provides valid credentials")
    public void userProvidesValidCredentials() throws InterruptedException {
        //print the username and password to console
        String username = properties.getProperty("validUsername").toString();
        String password = properties.getProperty("validUserPassword").toString();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
   }

    @Then("user should be able to login successfully")
    public void userShouldBeAbleToLoginSuccessfully() {
        Assert.assertTrue("Dashboard is not visible after login", loginPage.isDashboardVisible());

}

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheOrangeHRMLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I enter username as {string}")
    public void iEnterUsernameAs(String arg0) {
        loginPage.enterUsername(arg0);
    }

    @And("I enter password as {string}")
    public void iEnterPasswordAs(String arg0) {
        loginPage.enterPassword(arg0);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
    loginPage.clickSubmit();
    }

    @Then("I should see error message")
    public void verifyErrorMessage() {
        Assert.assertFalse("Login succeeded when it should have failed", loginPage.isDashboardVisible());
    }

    @Then("I should see success message")
    public void iShouldSeeSuccessMessage() {
        Assert.assertTrue("Dashboard is not visible after login", loginPage.isDashboardVisible());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }

        if (driver != null) {
            driver.quit();
            DriverManager.removeDriver();
        }
    }



}