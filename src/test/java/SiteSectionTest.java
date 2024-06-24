import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import settings.WebDriverSwitch;
import user.UserGen;
import user.UserPojo;
import user.UserStep;

public class SiteSectionTest extends WebDriverSwitch {
  MainPage objMainPage;
  LoginPage objLoginPage;
  RegisterPage objRegPage;
  AccountPage objAccountPage;
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
    objAccountPage = new AccountPage(driver);
  }

  @After
  @DisplayName("Удаление созданного пользователя")
  public void deleteUser(){
    userStep.deleteUser(accessToken);
  }

  @Test
  @DisplayName("Переход в личный кабинет")
  public void checkGetInCabinetPageTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.clickCabinetButton();
    objAccountPage.waitForLoadAccountPage();
    boolean isButtonVisible = objAccountPage.isExitButtonVisible();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Переход по кнопке Конструктор из личного кабинета")
  public void checkGetInConstructorPageByButtonTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.clickCabinetButton();
    objAccountPage.clickConstructorButton();
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isIngredientsConstructorVisible();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Переход через Логотип в конструктор из личного кабинета")
  public void checkGetInConstructorPageByLogoTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.clickCabinetButton();
    objAccountPage.clickLogoButton();
    objMainPage.waitForLoadMainPage();
    boolean isButtonVisible = objMainPage.isIngredientsConstructorVisible();
    assertTrue(isButtonVisible);
  }

  @Test
  @DisplayName("Выход из аккаунта")
  public void checkExitFromAccountTest() {
    objMainPage.openMainPage();
    objMainPage.clickEnterButton();
    objLoginPage.setUserData(user.getEmail(), user.getPassword());
    objMainPage.clickCabinetButton();
    objAccountPage.clickExitButton();
    objLoginPage.waitForLoadLoginPage();
    boolean isButtonVisible = objLoginPage.isEnterButtonVisible();
    assertTrue(isButtonVisible);
  }
}
