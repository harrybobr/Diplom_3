package pageObject;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

  private final By regText = By.xpath(".//*[text()='Регистрация']");
  private final By nameField = By.xpath("//div/form/fieldset[1]//input[@name='name']");
  private final By emailField = By.xpath("//div/form/fieldset[2]//input[@name='name']");
  private final By passField = By.xpath("//div/form/fieldset[3]//input[@name='Пароль']");
  private final By regButton = By.xpath(".//*[text()='Зарегистрироваться']");
  private final By wrongPassText = By.xpath(".//p[text() = 'Некорректный пароль']");
  private final By enterLink = By.xpath(".//a[text() = 'Войти']");

  private WebDriver driver;

  public RegisterPage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Ожидание открытия страницы регистрации")
  public void waitForLoadRegPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(driver -> driver.findElement(regText).isDisplayed());
  }

  @Step("Нажатие на поле Имя")
  public void clickNameField() {
    driver.findElement(nameField).click();
  }

  public void setName(String name) {
    driver.findElement(nameField).sendKeys(name);
  }

  public void setEmail(String email) {
    driver.findElement(emailField).sendKeys(email);
  }

  public void setPass(String password) {
    driver.findElement(passField).sendKeys(password);
  }

  @Step("Нажатие на кнопку Зарегистрироваться")
  public void clickRegButton() {
    driver.findElement(regButton).click();
  }

  @Step("Нажатие на линк Войти")
  public void clickEnterLink() {
    waitForLoadRegPage();
    driver.findElement(enterLink).click();
  }

  @Step("Заполнение полей для регистрации")
  public void setRegData(String name, String email, String password) {
    setName(name);
    setEmail(email);
    setPass(password);
    clickNameField();
  }

  public void clearRegData() {
    driver.findElement(nameField).clear();
    driver.findElement(emailField).clear();
    driver.findElement(passField).clear();
  }

  @Step("Проверка отображения надписи о некорректности пароля")
  public boolean isWrongPassTextVisible() {
    return driver.findElements(wrongPassText).size() > 0;
  }
}
