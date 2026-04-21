package core.interfaces;

import io.restassured.response.Response;

public interface APIExecutor {

    Response get(String endpoint);

    Response post(String endpoint, Object body);

    Response put(String endpoint, Object body);

    Response delete(String endpoint);
}