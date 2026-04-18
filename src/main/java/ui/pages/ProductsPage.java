package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.base.BasePage;

public class ProductsPage extends BasePage {

    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage addItem(String item) {
        click(By.xpath("//div[text()='" + item + "']/ancestor::div[@class='inventory_item']//button"));
        return this;
    }

    public int getCartCount() {
        return Integer.parseInt(getText(cartBadge));
    }

    public CartPage openCart() {
        click(cartIcon);
        return new CartPage(driver);
    }
}