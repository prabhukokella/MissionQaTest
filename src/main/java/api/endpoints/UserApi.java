package api.endpoints;

import api.client.ApiClient;
import io.restassured.response.Response;

public class UserApi {

    private static final String BASE = "https://reqres.in/api";

    public Response getUsers(int page) {
        return ApiClient.get("https://reqres.in/api/users?page=" + page);
    }

    public Response getUser(int id) {
        return ApiClient.get(BASE + "/users/" + id);
    }

    public Response createUser(Object body) {
        return ApiClient.post(BASE + "/users", body);
    }

    public Response login(Object body) {
        return ApiClient.post(BASE + "/login", body);
    }

    public Response delayedUsers() {
        return ApiClient.get(BASE + "/users?delay=3");
    }
}