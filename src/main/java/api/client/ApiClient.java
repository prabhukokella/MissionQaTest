package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiClient {

    private static final String API_KEY = "pub_18bf8784cba02010bc1386e2ec5d6d0a17fb450225633eff21c6610864594b1d"; // 🔥 replace with real key if needed

    public static Response get(String url) {
        return given()
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY)   // ✅ FIX HERE
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public static Response post(String url, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY)   // ✅ FIX HERE
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}