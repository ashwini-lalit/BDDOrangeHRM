package com.digite.stepdefinitions;

import com.digite.actions.PizzaCatalogue;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.digite.core.WebDriverFactory;
import com.digite.core.DriverManager;

import java.io.FileInputStream;
import java.util.Properties;

import java.io.IOException;
import java.time.Duration;

public class SearchPizzaSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private Properties properties;
    private PizzaCatalogue pizzaCatalogue;
    private String searchResult;

    @Before
    public void setup() {
        driver = WebDriverFactory.createDriver();
        DriverManager.setDriver(driver);
        pizzaCatalogue = new PizzaCatalogue(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("store has pizza menu as below \\(product catalogue)")
    public void store_has_pizza_menu() {
        pizzaCatalogue.navigateToMenuPage();
    }

    @When("Jane searches {string}")
    public void jane_searches(String searchText) {

        if (searchText.equalsIgnoreCase("small") || searchText.equalsIgnoreCase("medium") || searchText.equalsIgnoreCase("large")) {
            searchResult = pizzaCatalogue.searchPizzaBySize(searchText);
        } else if (searchText.startsWith("$")) {
            searchResult = pizzaCatalogue.searchPizzaByPrice(searchText);
        } else {
            searchResult = pizzaCatalogue.searchPizzaByName(searchText);
        }
    }

    @Then("she should be able to find {string}")
    public void she_should_be_able_to_find(String expectedResult) {
        Assert.assertEquals(expectedResult, searchResult);
    }

    @Given("user has accessed the pizza store")
    public void userHasAccessedThePizzaStore() {
        pizzaCatalogue.navigateToMenuPage();
    }
}
