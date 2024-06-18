import static org.junit.Assert.assertEquals;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import settings.WebDriverSwitch;
import user.UserGen;

@RunWith(Parameterized.class)
public class RegPassParamTest extends WebDriverSwitch {
  private final int passwordLength;
  private final boolean isCorrect;
  MainPage objMainPage;
  LoginPage objLoginPage;
  RegisterPage objRegPage;

  public RegPassParamTest(int passwordLength, boolean isCorrect) {
    this.passwordLength = passwordLength;
    this.isCorrect = isCorrect;
  }

  @Parameterized.Parameters(name = "Длина пароля: {0}; Корректный: {1}")
  public static Object[] getData() {
    return new Object[][]{
        {0, true}, // Сообщение о неверном пароле нет, но зарегистрироваться нельзя
        {5, false},
        {6, true},
        {7, true},
    };
  }

  @Test
  @DisplayName("Проверка регистрации с разной длиной пароля")
  public void checkPasswordOnRegistrationTest() {
    objMainPage = new MainPage(driver);
    objMainPage.openMainPage();
    objMainPage.waitForLoadMainPage();
    objMainPage.clickEnterButton();

    objLoginPage = new LoginPage(driver);
    objLoginPage.waitForLoadLoginPage();
    objLoginPage.clickRegLink();

    objRegPage = new RegisterPage(driver);
    objRegPage.waitForLoadRegPage();

    String password = generatePassword(passwordLength);
    objRegPage.setRegData(UserGen.genName(), UserGen.genEmail(), password);

    boolean isTextVisible = objRegPage.isWrongPassTextVisible();
    assertEquals(isCorrect, !isTextVisible);
  }

  private String generatePassword(int length) {
    if (length == 0) {
      return "";
    } else if (length < 6) {
      return UserGen.genWrongPassword();
    } else {
      return UserGen.genPassword();
    }
  }

  @After
  public void cleanUpRegData() {
    if (objRegPage != null) {
      objRegPage.clearRegData();
    }
  }
}
