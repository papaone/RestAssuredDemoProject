package methods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import pojo_models.requests.UserBodyRequest;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    @Step
    public static ValidatableResponse createUser(Integer id, String username, String firstname, String lastname,
                                              String email, String password, String phone, Integer userStatus) {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new UserBodyRequest(id, username, firstname, lastname, email, password, phone, userStatus))
                .when()
                .post("/user")
                .then();
    }

    @Step
    public static ValidatableResponse getUserByUserName(String username) {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/user/" + username)
                .then();
    }

    @Step
    public static ValidatableResponse createUsers(Integer id, String username, String firstname, String lastname,
                                                 String email, String password, String phone, Integer userStatus) {
        ArrayList<UserBodyRequest> userBodyRequests = new ArrayList<>();
        userBodyRequests.add(new UserBodyRequest(id, username, firstname, lastname, email, password, phone, userStatus));
        userBodyRequests.add(new UserBodyRequest(id, username, firstname, lastname, email, password, phone, userStatus));
        userBodyRequests.add(new UserBodyRequest(id, username, firstname, lastname, email, password, phone, userStatus));

        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(userBodyRequests)
                .when()
                .post("/user/createWithList")
                .then();
    }
}
