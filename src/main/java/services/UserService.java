package services;

import api.client.ApiClient;
import api.endpoints.UserApi;
import api.models.User;
import core.interfaces.APIExecutor;
import io.restassured.response.Response;
import services.interfaces.UserServiceProvider;

import java.util.*;

public class UserService implements UserServiceProvider {

    private final UserApi userApi;

    private Response response;
    private List<Integer> userIds = new ArrayList<>();

    // ✅ Inject dependency
    public UserService() {
        APIExecutor apiExecutor = new ApiClient();
        this.userApi = new UserApi(apiExecutor);
    }

    // ---------- LIST USERS ----------
    @Override
    public void getFirstPageUsers() {
        response = userApi.getUsers(1);
        extractUserIds(response);
    }

    @Override
    public void getAllUsers() {

        userIds.clear();

        int page = 1;
        int totalPages;

        do {
            Response res = userApi.getUsers(page);

            if (res.statusCode() != 200) {
                throw new RuntimeException("API failed: " + res.asString());
            }

            extractUserIds(res);

            totalPages = res.jsonPath().getInt("total_pages");

            page++;

        } while (page <= totalPages);
    }
    
    @Override
    public int getTotalUsers() {
        return userIds.size();
    }

    @Override
    public int getUniqueUserIdsCount() {
        return new HashSet<>(userIds).size();
    }

    // ---------- SINGLE USER ----------
    @Override
    public void getUser(int id) {
        response = userApi.getUser(id);
    }

    @Override
    public String getField(String key) {
        return response.jsonPath().getString("data." + key);
    }

    // ---------- STATUS ----------
    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    // ---------- CREATE USER ----------
    @Override
    public void createUser(String name, String job) {
        response = userApi.createUser(new User(name, job));
    }

    @Override
    public String getResponseField(String key) {
        return response.jsonPath().getString(key);
    }

    // ---------- LOGIN ----------
    @Override
    public void login(String email, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        response = userApi.login(body);
    }

    @Override
    public String getResponseBody() {
        return response.asString();
    }

    // ---------- DELAY ----------
    @Override
    public void getDelayedUsers() {
        response = userApi.delayedUsers();
        extractUserIds(response);
    }

    @Override
    public boolean areUserIdsUnique() {
        return userIds.size() == new HashSet<>(userIds).size();
    }

    // ---------- HELPER ----------
    private void extractUserIds(Response res) {

        List<Integer> ids = res.jsonPath().getList("data.id");

        if (ids != null) {
            userIds.addAll(ids);
        }
    }
    
    
}