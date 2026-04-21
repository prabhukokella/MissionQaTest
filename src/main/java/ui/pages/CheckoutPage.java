package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

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

    // ✅ ONLY RETURN DATA (NO CALCULATION)
    public List<Double> getItemPrices() {
        return driver.findElements(prices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public double getDisplayedTotal() {
        return Double.parseDouble(getText(total).replaceAll("[^0-9.]", ""));
    }

    public double getDisplayedTax() {
        return Double.parseDouble(getText(tax).replaceAll("[^0-9.]", ""));
    }
}