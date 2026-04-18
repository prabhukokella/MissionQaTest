package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.base.BasePage;



public class CheckoutPage extends BasePage {

    private By first = By.id("first-name");
    private By last = By.id("last-name");
    private By zip = By.id("postal-code");
    private By continueBtn = By.id("continue");

    private By prices = By.className("inventory_item_price");
    private By total = By.className("summary_subtotal_label");
    private By tax = By.className("summary_tax_label");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage typeFirstName(String value) {
        type(first, value);
        return this;
    }

    public CheckoutPage typeLastName(String value) {
        type(last, value);
        return this;
    }

    public CheckoutPage typeZip(String value) {
        type(zip, value);
        return this;
    }

    public CheckoutPage clickContinue() {
        click(continueBtn);
        return this;
    }

    public double calculateItemTotal() {
        return driver.findElements(prices)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "")))
                .sum();
    }

    public double getDisplayedTotal() {
        return Double.parseDouble(getText(total).replaceAll("[^0-9.]", ""));
    }

    public double getTax() {
        return Double.parseDouble(getText(tax).replaceAll("[^0-9.]", ""));
    }
}