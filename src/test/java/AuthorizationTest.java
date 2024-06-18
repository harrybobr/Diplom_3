import static org.junit.Assert.assertTrue;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.ForgotPassPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import settings.WebDriverSwitch;
import user.UserGen;
import user.UserPojo;
import user.UserStep;

public class AuthorizationTest extends WebDriverSwitch {
  MainPage objMainPage;
  LoginPage objLoginPage;
  RegisterPage objRegPage;
  ForgotPassPage objForgotPassPage;
  UserStep userStep = new UserStep();
  String accessToken;
  UserPojo user;

  @Before
  @DisplayName("Создать нового пользователя")
  public void createUser() {
    user = UserGen.genUser();
    Response response = userStep.regUser(user);
    accessToken = response.then().extract().path("accessToken");
    objMainPage = new MainPage(driver);
    objLoginPage = new LoginPage(driver);
    objRegPage = new RegisterPage(driver);
    objForgotPassPage = new ForgotPassPage(driver);
  }

  @After
  @DisplayName("Удаление созданного пользователя")
  public void deleteUser(){
    userStep.deleteUser(accessToken);
  }

  @Test
  @DisplayName("Вход по кнопке Войти в аккаунт на главной")
  public void userAuthByLoginButtonTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Вход через кнопку Личный кабинет")
  public void userAuthByPersonalCabinetButtonTest() {
    objMainPage.openMainPage();
    objMainPage.clickCabinetButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Вход через форму регистрации")
  public void userAuthFromRegPageTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.clickRegLink();
    objRegPage.clickEnterLink();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Вход через форму восстановления пароля")
  public void userAuthFromRecoverPassPageTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.clickRecoverPassLink();
    objForgotPassPage.clickEnterLink();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
    assertTrue(isButtonVisible);
  }
}
