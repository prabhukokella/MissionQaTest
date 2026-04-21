package api.endpoints;

import api.client.ApiClient;
import io.restassured.response.Response;
import core.interfaces.APIExecutor;

public class UserApi {

    private final APIExecutor apiClient;

    // ✅ Constructor Injection
    public UserApi(APIExecutor apiClient) {
        this.apiClient = apiClient;
    }

    // ---------- GET USERS ----------
    public Response getUsers(int page) {
        return apiClient.get("/users?page=" + page);
    }

    // ---------- GET SINGLE USER ----------
    public Response getUser(int id) {
        return apiClient.get("/users/" + id);
    }

    // ---------- CREATE USER ----------
    public Response createUser(Object body) {
        return apiClient.post("/users", body);
    }

    // ---------- LOGIN ----------
    public Response login(Object body) {
        return apiClient.post("/login", body);
    }

    // ---------- DELAY USERS ----------
    public Response delayedUsers() {
        return apiClient.get("/users?delay=3");
    }
}