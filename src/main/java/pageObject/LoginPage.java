package pageObject;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

  private final By enterText = By.xpath(".//main/div/h2");
  private final By emailField = By.xpath(".//*[@type='text']");
  private final By passField = By.xpath(".//*[@type='password']");
  private final By enterButton = By.xpath(".//button[text() = 'Войти']");
  private final By regLink = By.xpath("//div/p[1]/*[@href='/register']");
  private final By recoverPassLink = By.xpath(".//a[text() = 'Восстановить пароль']");

  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Ожидание открытия страницы авторизации")
  public void waitForLoadLoginPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(driver -> driver.findElement(enterText).isDisplayed());
  }

  @Step("Нажатие на линк Зарегистрироваться")
  public void clickRegLink() {
    waitForLoadLoginPage();
    driver.findElement(regLink).click();
  }

  @Step("Нажатие на линк Восстановить пароль")
  public void clickRecoverPassLink() {
    waitForLoadLoginPage();
    driver.findElement(recoverPassLink).click();
  }

  @Step("Проверка отображения кнопки Войти")
  public boolean isEnterButtonVisible() {
    return driver.findElement(enterButton).isDisplayed();
  }

  @Step("Нажатие на кнопку Войти")
  public void clickEnterButton() {
    driver.findElement(enterButton).click();
  }

  @Step("Заполнить поля авторизации")
  public void setUserData(String email, String password) {
    waitForLoadLoginPage();
    setEmail(email);
    setPass(password);
    clickEnterButton();
  }

  public void setEmail(String email) {
    driver.findElement(emailField).sendKeys(email);
  }

  public void setPass(String password) {
    driver.findElement(passField).sendKeys(password);
  }
}
