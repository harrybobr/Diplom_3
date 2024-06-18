package user;

import static io.restassured.RestAssured.given;
import static pageObject.MainPage.BURGER_URL;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserStep {

  public static final String AUTHORIZATION = "Authorization";
  public static final String USER_REGISTER = "/api/auth/register";
  public static final String USER_LOGIN = "/api/auth/login";
  public static final String USER_DELETE = "/api/auth/user";

  @Step("Регистрация пользователя POST api/auth/register")
  public Response regUser(UserPojo user) {
    return given()
        .contentType(ContentType.JSON)
        .baseUri(BURGER_URL)
        .body(user)
        .when()
        .post(USER_REGISTER);
  }

  @Step("Логин пользователя POST api/auth/login")
  public Response loginUser(UserPojo user) {
    return given()
        .contentType(ContentType.JSON)
        .baseUri(BURGER_URL)
        .body(user)
        .when()
        .post(USER_LOGIN);
  }

  @Step("Удаление пользователя DELETE api/auth/user")
  public Response deleteUser(String accessToken) {
    return given()
        .contentType(ContentType.JSON)
        .baseUri(BURGER_URL)
        .headers(AUTHORIZATION, accessToken)
        .when()
        .delete(USER_DELETE);
  }
}
