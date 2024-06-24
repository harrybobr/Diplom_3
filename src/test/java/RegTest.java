import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import settings.WebDriverSwitch;
import user.UserGen;
import user.UserPojo;
import user.UserStep;

public class RegTest extends WebDriverSwitch {
  MainPage objMainPage;
  LoginPage objLoginPage;
  RegisterPage objRegPage;
  UserStep userStep = new UserStep();
  UserPojo user;

  @After
  public void deleteUser(){
    Response response = userStep.loginUser(user);
    String accessToken = response.then().extract().path("accessToken");
    userStep.deleteUser(accessToken);
  }

  @Test
  @DisplayName("Регистрация нового пользователя")
  public void regNewUserTest() {
    user = UserGen.genUser();

    objMainPage = new MainPage(driver);
    objMainPage.openMainPage();
    objMainPage.waitForLoadMainPage();
    objMainPage.clickCabinetButton();

    objLoginPage = new LoginPage(driver);
    objLoginPage.waitForLoadLoginPage();
    objLoginPage.clickRegLink();

    objRegPage = new RegisterPage(driver);
    objRegPage.waitForLoadRegPage();
    objRegPage.setRegData(user.getName(), user.getEmail(), user.getPassword());
    objRegPage.clickRegButton();

    objMainPage.clickCabinetButton();

    objLoginPage.setUserData(user.getEmail(), user.getPassword());

    boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
    assertTrue(isButtonVisible);
  }
}
