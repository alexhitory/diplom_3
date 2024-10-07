package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import user.User;

import static io.restassured.RestAssured.given;

public class Api {
    private static final String URI_FOR_LOGIN_USER = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String URI_FOR_UPDATE_OR_DELETE_USER = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String URI_FOR_REGISTER_USER = "https://stellarburgers.nomoreparties.site/api/auth/register";
    public static String accessToken;

    @Step("Создание юзера через апи")
    public static void createUserInApi(User user) {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(user)
                .when()
                .post(URI_FOR_REGISTER_USER);
        accessToken = response.then().extract().path("accessToken");

    }

    @Step("Логин юзером через апи")
    public static Response loginUser(User user) {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URI_FOR_LOGIN_USER);
        accessToken = response.then().extract().path("accessToken");
        return response;
    }

    @Step("Удаление юзера через апи")
    public static void deleteUser() {
        if (accessToken != null) {
            given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", accessToken)
                    .when()
                    .delete(URI_FOR_UPDATE_OR_DELETE_USER);
        }
    }
}