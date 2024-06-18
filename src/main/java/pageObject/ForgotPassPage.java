package pageObject;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassPage {

  private final By recoverPassText = By.xpath(".//main/div/h2");
  private final By enterLink = By.xpath(".//a[text() = 'Войти']");

  private WebDriver driver;

  public ForgotPassPage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Ожидание открытия страницы восстановления пароля")
  public void waitForLoadRecoverPassPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(driver -> driver.findElement(recoverPassText).isDisplayed());
  }

  @Step("Нажатие на линк Войти")
  public void clickEnterLink() {
    waitForLoadRecoverPassPage();
    driver.findElement(enterLink).click();
  }
}
