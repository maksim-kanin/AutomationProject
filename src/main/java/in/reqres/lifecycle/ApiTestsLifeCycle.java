package in.reqres.lifecycle;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static in.reqres.filters.LogFilter.filters;

public class ApiTestsLifeCycle implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.filters(filters().withCustomTemplates());
        RestAssured.baseURI = "https://reqres.in/";
    }
}