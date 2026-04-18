package services;

import api.endpoints.UserApi;
import api.models.User;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserService {

    private UserApi api = new UserApi();
    private Response response;

    // ---------- LIST USERS ----------
    public void getFirstPageUsers() {
        response = api.getUsers(1);
    }

    public void getAllUsers() {
        response = api.getUsers(2);
    }

    public int getTotalUsers() {

        if (response == null) {
            throw new RuntimeException("Response is null");
        }

        List<?> users = response.jsonPath().getList("data");

        if (users == null) {
            System.out.println("⚠️ Response body:");
            System.out.println(response.asPrettyString());
            throw new RuntimeException("Data is null in response");
        }

        return users.size();
    }

    public int getUniqueUserIdsCount() {

        List<Integer> ids = response.jsonPath().getList("data.id");

        if (ids == null) {
            System.out.println("⚠️ Response body:");
            System.out.println(response.asPrettyString());
            throw new RuntimeException("IDs are null → check API response");
        }

        return new HashSet<>(ids).size();
    }

    // ---------- SINGLE USER ----------
    public void getUser(int id) {
        response = api.getUser(id);
    }

    public String getField(String key) {
        return response.jsonPath().getString("data." + key);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    // ---------- CREATE USER ----------
    public void createUser(String name, String job) {
        response = api.createUser(new User(name, job));
    }

    public String getResponseField(String key) {
        return response.jsonPath().getString(key);
    }

    // ---------- LOGIN ----------
    public void login(String email, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("email", email);

        if (password != null && !password.isEmpty()) {
            body.put("password", password);
        }

        response = api.login(body);
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    // ---------- DELAY ----------
    public void getDelayedUsers() {
        response = api.delayedUsers();
    }

    public boolean areUserIdsUnique() {
        List<Integer> ids = response.jsonPath().getList("data.id");
        return ids.size() == new HashSet<>(ids).size();
    }
}