package steps;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import services.UserService;
import services.interfaces.UserServiceProvider;


import java.util.List;
import java.util.Map;

public class APISteps {

    // ✅ Lazy initialization (better than field init)
    private UserServiceProvider service;

    private UserServiceProvider getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    // ---------- LIST USERS ----------
    @Given("I get the default list of users for on 1st page")
    public void getFirstPage() {
        getService().getFirstPageUsers();
    }

    @When("I get the list of all users within every page")
    public void getAllUsers() {
        getService().getAllUsers();
    }

    @Then("I should see total users count equals the number of user ids")
    public void validateUsers() {
        Assert.assertEquals(
                getService().getTotalUsers(),
                getService().getUniqueUserIdsCount()
        );
    }

    // ---------- SINGLE USER ----------
    @Given("I make a search for user {int}")
    public void searchUser(int id) {
        getService().getUser(id);
    }

    @Then("I should see the following user data")
    public void validateUser(DataTable table) {

        Map<String, String> data = getFirstRow(table);

        Assert.assertEquals(getService().getField("first_name"), data.get("first_name"));
        Assert.assertEquals(getService().getField("email"), data.get("email"));
    }

    // ---------- USER NOT FOUND ----------
    @Then("I receive error code {int} in response")
    public void validateError(int code) {
        Assert.assertEquals(getService().getStatusCode(), code);
    }

    // ---------- CREATE USER ----------
    @Given("I create a user with following {word} {word}")
    public void createUser(String name, String job) {
        getService().createUser(name, job);
    }

    @Then("response should contain the following data")
    public void validateCreateUser(DataTable table) {

        Map<String, String> data = getFirstRow(table);

        for (String key : data.keySet()) {

            if (key.equals("id") || key.equals("createdAt")) {
                Assert.assertNotNull(getService().getResponseField(key));
            } else {
                Assert.assertEquals(getService().getResponseField(key), data.get(key));
            }
        }
    }

    // ---------- LOGIN ----------
    @Given("I login unsuccessfully with the following data")
    public void login(DataTable table) {

        Map<String, String> data = getFirstRow(table);

        getService().login(data.get("Email"), data.get("Password"));
    }

    @Then("I should get a response code of {int}")
    public void validateStatus(int code) {
        Assert.assertEquals(getService().getStatusCode(), code);
    }

    @Then("I should see the following response message:")
    public void validateMessage(DataTable table) {

        String expected = table.asList().get(0);

        Assert.assertTrue(getService().getResponseBody().contains(expected));
    }

    // ---------- DELAY ----------
    @Given("I wait for the user list to load")
    public void delayed() {
        getService().getDelayedUsers();
    }

    @Then("I should see that every user has a unique id")
    public void uniqueIds() {
        Assert.assertTrue(getService().areUserIdsUnique());
    }

    // ---------- COMMON UTIL ----------
    private Map<String, String> getFirstRow(DataTable table) {

        List<Map<String, String>> dataList = table.asMaps();

        if (dataList == null || dataList.isEmpty()) {
            throw new RuntimeException("DataTable is empty");
        }

        return dataList.get(0);
    }
}