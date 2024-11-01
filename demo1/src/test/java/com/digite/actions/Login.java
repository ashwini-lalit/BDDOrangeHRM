package com.digite.actions;

import com.digite.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class Login {
    private LoginPage loginPage;

    public Login(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public boolean performLogin(String username, String password) {
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        return loginPage.isDashboardVisible();
    }

    public boolean successfulLogin() {
        return loginPage.isDashboardVisible();
    }
}