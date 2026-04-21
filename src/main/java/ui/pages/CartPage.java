package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ui.base.BasePage;



public class CartPage extends BasePage {

    private By qty = By.className("cart_quantity");
    private By items = By.className("cart_item");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ✅ FIX: Add this method
    public boolean verifyQtyIsOne() {

        List<WebElement> quantities = driver.findElements(qty);

        for (WebElement q : quantities) {
            if (!q.getText().equals("1")) {
                return false;
            }
        }

        return true;
    }


    public int getItemCount() {
        return driver.findElements(items).size();
    }

    public CartPage removeItem(String item) {
        click(By.xpath("//div[text()='" + item + "']/ancestor::div[@class='cart_item']//button"));
        return this;
    }

    public CheckoutPage clickCheckout() {
        click(checkoutBtn);
        return new CheckoutPage(driver);
    }
}