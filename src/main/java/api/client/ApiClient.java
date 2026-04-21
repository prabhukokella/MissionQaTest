package api.client;

import core.interfaces.APIExecutor;
import core.config.ConfigLoader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient implements APIExecutor {

    private final String baseUrl;
    private final String apiKey;

    public ApiClient() {
        this.baseUrl = ConfigLoader.get("api.base.url");
        this.apiKey = ConfigLoader.get("api.key");   // ✅ NEW
    }

    // ✅ Centralized request builder
    private RequestSpecification request() {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey);   // 🔥 CRITICAL FIX
    }

    @Override
    public Response get(String endpoint) {
        return request()
                .when()
                .get(endpoint);
    }

    @Override
    public Response post(String endpoint, Object body) {
        return request()
                .body(body)
                .when()
                .post(endpoint);
    }

    @Override
    public Response put(String endpoint, Object body) {
        return request()
                .body(body)
                .when()
                .put(endpoint);
    }

    @Override
    public Response delete(String endpoint) {
        return request()
                .when()
                .delete(endpoint);
    }
}