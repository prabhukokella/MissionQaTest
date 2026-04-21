package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.base.BasePage;
import core.config.ConfigLoader;

import java.util.List;

public class HomePage extends BasePage {

    private By cart = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {

        String baseUrl = ConfigLoader.get("base.url");
        String path = ConfigLoader.get("inventory.path");

        if (path == null) {
            throw new RuntimeException("inventory.path is missing in config");
        }

        driver.get(baseUrl + path);
    }

    public void addItems(List<String> items) {

        for (String item : items) {

            String id = item.toLowerCase()
                    .replace(" ", "-");

            click(By.id("add-to-cart-" + id));
        }
    }

    public int getCartCount() {
        String count = getText(cartBadge);
        return Integer.parseInt(count);
    }

    public void openCart() {
        click(cart);
    }
}