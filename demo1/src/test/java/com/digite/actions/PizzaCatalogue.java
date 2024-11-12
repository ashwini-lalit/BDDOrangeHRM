package com.digite.actions;
import com.digite.pages.PizzaCataloguePage;
import org.openqa.selenium.WebDriver;

public class PizzaCatalogue {

    private PizzaCataloguePage pizzaCataloguePage;

    public PizzaCatalogue(WebDriver driver) {
        pizzaCataloguePage = new PizzaCataloguePage(driver);
    }

    public void navigateToMenuPage() {
        pizzaCataloguePage.navigateToMenuPage();
    }

    public String searchPizzaByName(String searchText) {
        return pizzaCataloguePage.searchPizza("name", searchText);
    }

    public String searchPizzaBySize(String size) {
        return pizzaCataloguePage.searchPizza("size", size);
    }

    public String searchPizzaByPrice(String price) {
        return pizzaCataloguePage.searchPizza("price", price);
    }
}
