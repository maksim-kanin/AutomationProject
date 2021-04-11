package in.reqres;

import in.reqres.lifecycle.ApiTestsLifeCycle;
import in.reqres.pojo.CreatedUser;
import in.reqres.pojo.UpdatedUser;
import in.reqres.pojo.User;
import in.reqres.pojo.Users;
import in.reqres.steps.CommonSteps;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.InputStream;

import static in.reqres.Endpoints.*;
import static in.reqres.filters.LogFilter.filters;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
@ExtendWith(ApiTestsLifeCycle.class)
public class ReqresTests {
    private final CommonSteps commonSteps = new CommonSteps();

    @Test
    @Feature("GET")
    @DisplayName("Check list users endpoint '/api/users?page=2' and verify it response")
    public void getListUsersTest() {
        Response response = when()
                .get(LIST_USERS.path());

        Users users = commonSteps
                .checkResponseWithStatusCode(response, 200)
                .as(Users.class);

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(users.getData().size()).isEqualTo(6);
        assertions.assertThat(users.getData().get(5).getEmail()).isEqualTo("rachel.howell@reqres.in");
        assertions.assertAll();
    }

    @Test
    @Feature("GET")
    @DisplayName("Check single user endpoint '/api/users/2' and verify it response")
    public void getSingleUserTest() {
        InputStream pathToYAML = commonSteps.getUserInputStream("user.yaml");

        Response response = when()
                .get(SINGLE_USER.path());

        User actualUser = commonSteps
                .checkResponseWithStatusCode(response, 200)
                .as(User.class);

        User expectedUser = commonSteps.yamlToBean(pathToYAML, User.class);
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    @Feature("POST")
    @DisplayName("Check create user endpoint '/api/users' and verify it response")
    public void postCreateUserTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Maxim\",\"job\": \"Lead\"}")
                .when()
                .post(CREATE.path());
        CreatedUser createdUser = commonSteps
                .checkResponseWithStatusCode(response, 201)
                .as(CreatedUser.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdUser.getName()).isEqualTo("Maxim");
        assertions.assertThat(createdUser.getJob()).isEqualTo("Lead");
        assertions.assertAll();
    }

    @Test
    @Feature("DELETE")
    @DisplayName("Check delete user endpoint '/api/users/2' and verify it response")
    public void deleteUserTest() {
        Response response = when()
                .delete(DELETE.path());
        commonSteps.checkResponseWithStatusCode(response, 204);
    }

    @Test
    @Feature("PATCH")
    @DisplayName("Check update user endpoint '/api/users/2' and verify it response")
    public void updateUserTest() {
        Response response = given()
                .filter(filters().withCustomTemplates())
                .body("{\"name\": \"Maxim\",\"job\": \"Manager\"}")
                .when()
                .patch(UPDATE.path());
        UpdatedUser updated = commonSteps
                .checkResponseWithStatusCode(response, 200)
                .as(UpdatedUser.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(updated.getName()).isEqualTo("Maxim");
        assertions.assertThat(updated.getJob()).isEqualTo("Manager");
        assertions.assertAll();
    }
}
