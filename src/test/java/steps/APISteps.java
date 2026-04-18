package steps;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import services.UserService;

import java.util.List;
import java.util.Map;

public class APISteps {

    private UserService service = new UserService();

    // ---------- LIST USERS ----------
    @Given("I get the default list of users for on 1st page")
    public void getFirstPage() {
        service.getFirstPageUsers();
    }

    @When("I get the list of all users within every page")
    public void getAllUsers() {
        service.getAllUsers();
    }

    @Then("I should see total users count equals the number of user ids")
    public void validateUsers() {
        Assert.assertEquals(service.getTotalUsers(), service.getUniqueUserIdsCount());
    }

    // ---------- SINGLE USER ----------
    @Given("I make a search for user {int}")
    public void searchUser(int id) {
        service.getUser(id);
    }

    @Then("I should see the following user data")
    public void validateUser(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);

        Assert.assertEquals(service.getField("first_name"), data.get("first_name"));
        Assert.assertEquals(service.getField("email"), data.get("email"));
    }

    // ---------- USER NOT FOUND ----------
    @Then("I receive error code {int} in response")
    public void validateError(int code) {
        Assert.assertEquals(service.getStatusCode(), code);
    }

    // ---------- CREATE USER ----------

    @Given("I create a user with following {word} {word}")
    public void createUser(String name, String job) {
        service.createUser(name, job);
    }

    @Then("response should contain the following data")
    public void validateCreateUser(DataTable table) {

        List<Map<String, String>> dataList = table.asMaps();

        if (dataList.isEmpty()) {
            throw new RuntimeException("DataTable is empty — please provide at least one row");
        }

        Map<String, String> data = dataList.get(0);

        for (String key : data.keySet()) {
            if (key.equals("id") || key.equals("createdAt")) {
                Assert.assertNotNull(service.getResponseField(key));
            } else {
                Assert.assertEquals(service.getResponseField(key), data.get(key));
            }
        }
    }

    // ---------- LOGIN ----------
    @Given("I login unsuccessfully with the following data")
    public void login(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);

        service.login(data.get("Email"), data.get("Password"));
    }

    @Then("I should get a response code of {int}")
    public void validateStatus(int code) {
        Assert.assertEquals(service.getStatusCode(), code);
    }

    @Then("I should see the following response message:")
    public void validateMessage(DataTable table) {
        String expected = table.asList().get(0);
        Assert.assertTrue(service.getResponseBody().contains(expected));
    }

    // ---------- DELAY ----------
    @Given("I wait for the user list to load")
    public void delayed() {
        service.getDelayedUsers();
    }

    @Then("I should see that every user has a unique id")
    public void uniqueIds() {
        Assert.assertTrue(service.areUserIdsUnique());
    }
}