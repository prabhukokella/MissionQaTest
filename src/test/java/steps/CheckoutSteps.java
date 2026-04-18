package steps;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import core.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.CheckoutService;

public class CheckoutSteps {

    WebDriver driver;
    CheckoutService service;

    @Given("I am on the home page")
    public void openHome() {
        driver = DriverFactory.get();
        service = new CheckoutService(driver);
        service.openHome();
    }

    @Given("I login in with the following details")
    public void login(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        service.login(data.get("userName"), data.get("Password"));
    }

    @Given("I add the following items to the basket")
    public void addItems(DataTable table) {
        service.addItems(table.asList());
    }

    @Given("I should see {int} items added to the shopping cart")
    public void verifyCart(int count) {
        Assert.assertEquals(service.getCartCount(), count);
    }

    @Given("I click on the shopping cart")
    public void openCart() {
        service.openCart();
    }

    @Given("I verify that the QTY count for each item should be 1")
    public void verifyQty() {
        Assert.assertTrue(service.verifyQty());
    }

    @Given("I remove the following item:")
    public void removeItem(DataTable table) {
        service.removeItem(table.asList().get(0));
    }

    @Given("I should see only {int} items added")
    public void verifyAfterRemove(int count) {
        Assert.assertEquals(service.getCartCount(), count);
    }

    @Given("I click on the CHECKOUT button")
    public void checkout() {
        service.clickCheckout();
    }

    @Given("I type {string} for First Name")
    public void first(String f) {
        service.typeFirst(f);
    }

    @Given("I type {string} for Last Name")
    public void last(String l) {
        service.typeLast(l);
    }

    
   // @Given("I type {string} for ZIP/Postal Code")
    @Given("I type {string} for ZIP\\/Postal Code")
    public void enterZip(String zip) {
        service.typeZip(zip);
    }
    
    

    @When("I click on the CONTINUE button")
    public void cont() {
        service.continueCheckout();
    }

    @Then("Item total will be equal to the total of items on the list")
    public void validateTotal() {
        Assert.assertEquals(service.getCalcTotal(), service.getActualTotal(), 0.01);
    }

    @Then("a Tax rate of {int} % is applied to the total")
    public void validateTax(int percent) {
        double total = service.getActualTotal();
        Assert.assertEquals(service.getTax(), total * percent / 100, 0.01);
    }
}