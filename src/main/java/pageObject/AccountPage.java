package pageObject;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

  private final By exitButton = By.xpath(".//button[text() = 'Выход']");
  private final By profileButton = By.xpath(".//a[text() = 'Профиль']");
  private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
  private final By logoButton = By.xpath(".//header/nav/div");

  private WebDriver driver;

  public AccountPage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Ожидание открытия страницы профиля")
  public void waitForLoadAccountPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
  }

  @Step("Видимость кнопки Выход")
  public boolean isExitButtonVisible() {
    return driver.findElement(exitButton).isDisplayed();
  }

  @Step("Нажатие на кнопку Выход")
  public void clickExitButton() {
    waitForLoadAccountPage();
    driver.findElement(exitButton).click();
  }

  @Step("Нажатие на кнопку Конструктор")
  public void clickConstructorButton() {
    waitForLoadAccountPage();
    driver.findElement(constructorButton).click();
  }

  @Step("Нажатие на логотип")
  public void clickLogoButton() {
    waitForLoadAccountPage();
    driver.findElement(logoButton).click();
  }
}
