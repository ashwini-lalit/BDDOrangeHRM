package com.digite.actions;

import com.digite.pages.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * The Login class provides methods to perform login actions on the LoginPage.
 */
public class Login {
    private LoginPage loginPage;

    /**
     * Constructs a Login object with the specified WebDriver.
     *
     * @param driver the WebDriver instance to interact with the LoginPage
     */
    public Login(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    /**
     * Performs the login action using the provided username and password.
     *
     * @param username the username to be entered
     * @param password the password to be entered
     * @return true if the login is successful and the dashboard is visible, false otherwise
     */
    public boolean performLogin(String username, String password) {
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        return loginPage.isDashboardVisible();
    }

    /**
     * Checks if the login was successful by verifying the visibility of the dashboard.
     *
     * @return true if the dashboard is visible, false otherwise
     */
    public boolean successfulLogin() {
        return loginPage.isDashboardVisible();
    }
}