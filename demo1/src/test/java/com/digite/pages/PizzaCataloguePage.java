package com.digite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class PizzaCataloguePage {

    private WebDriver driver;
    private Properties properties;
    private WebDriverWait wait;

        public PizzaCataloguePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            loadProperties();
        }

        /**
         * Loads the properties from the Pizza_Catalogue page properties file.
         */
        private void loadProperties() {
            properties = new Properties();
            try {
                FileInputStream file = new FileInputStream("src/test/resources/pageObjects/PizzaCatalogue.properties");
                properties.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void navigateToMenuPage() {
            WebElement menuLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(properties.getProperty("pizza_menu"))));
            menuLink.click();
        }

    public String searchPizza(String searchType, String searchText) {
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(properties.getProperty("search_input"))));
        searchInput.clear();
        searchInput.sendKeys(searchText);

        List<WebElement> pizzaItems = driver.findElements(By.cssSelector(properties.getProperty("pizza_items")));

        for (WebElement pizza : pizzaItems) {
            String pizzaInfo = "";
            switch(searchType) {
                case "name":
                    String pizzaNameXpath = properties.getProperty("pizza_name").replace("${dynamicValue}", searchText);
                    if(pizza.findElement(By.xpath(pizzaNameXpath)).isDisplayed()){
                        pizzaInfo = pizza.findElement(By.xpath(pizzaNameXpath)).getText();
                    }
                    break;
                case "size":
                    String pizzaSizeXpath = properties.getProperty("pizza_size").replace("${dynamicValue}", searchText);
                    if(pizza.findElement(By.xpath(pizzaSizeXpath)).isDisplayed()){
                        pizzaInfo = pizza.findElement(By.xpath(pizzaSizeXpath)).getText();
                    }
                    //pizzaInfo = pizza.findElement(By.cssSelector(properties.getProperty("pizza_size"))).getText();
                    break;
                case "price":
                    String pizzaPriceXpath = properties.getProperty("pizza_price").replace("${dynamicValue}", searchText);
                    if(pizza.findElement(By.xpath(pizzaPriceXpath)).isDisplayed()){
                        pizzaInfo = pizza.findElement(By.xpath(pizzaPriceXpath)).getText();
                    }
                    break;
            }

            if (matchesSearchCriteria(searchType, searchText, pizzaInfo)) {
                return getPizzaName(pizza);
            }
        }
        return "Not Found";
    }

        private boolean matchesSearchCriteria(String searchType, String searchText, String pizzaInfo) {
        switch(searchType) {
            case "name":
                return pizzaInfo.toLowerCase().contains(searchText.toLowerCase());
            case "size":
                return pizzaInfo.equalsIgnoreCase(searchText);
            case "price":
                return pizzaInfo.contains(searchText);
            default:
                return false;
        }
    }

        private String getPizzaName(WebElement pizza) {
         return pizza.findElement(By.cssSelector(properties.getProperty("pizza.name"))).getText();
        }

        public boolean verifySearchResults(String searchResult) {
                String xpathExpression = properties.getProperty(searchResult);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
                return true;
        }
}
