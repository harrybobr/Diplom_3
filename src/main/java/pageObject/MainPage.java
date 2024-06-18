package pageObject;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

  public static final String BURGER_URL = "https://stellarburgers.nomoreparties.site";
  private final By createBurgerText = By.xpath(".//main/section[1]/h1");
  private final By enterAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
  private final By cabinetButton = By.xpath(".//p[text()='Личный Кабинет']");
  private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
  private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
  private final By ingredientsConstructor = By.className("constructor-element");
  private final By headerBuns = By.xpath(".//span[text() = 'Булки']");
  private final By headerSauce = By.xpath(".//span[text() = 'Соусы']");
  private final By headerFilling = By.xpath(".//span[text() = 'Начинки']");
  private final By headerCurrent = By.xpath(".//div[contains(@class,'tab_tab_type_current')]");

  private final WebDriver driver;

  public MainPage(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Открытие главной страницы")
  public void openMainPage() {
    driver.get(BURGER_URL);
  }

  @Step("Ожидание открытия главной страницы")
  public void waitForLoadMainPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(createBurgerText));
  }

  @Step("Нажатие на кнопку Войти в аккаунт")
  public void clickEnterButton() {
    driver.findElement(enterAccountButton).click();
  }

  @Step("Нажатие на кнопку Личный кабинет")
  public void clickCabinetButton() {
    driver.findElement(cabinetButton).click();
  }

  @Step("Нажатие на кнопку Конструктор")
  public void clickConstructorButton() {
    driver.findElement(constructorButton).click();
  }

  @Step("Проверка Авторизации, кнопка Оформить заказ отображается")
  public boolean isCreateOrderButtonDisplayed() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    return driver.findElement(createOrderButton).isDisplayed();
  }

  @Step("Проверка отображения состава ингредиентов")
  public boolean isIngredientsConstructorVisible() {
    return driver.findElement(ingredientsConstructor).isDisplayed();
  }

  @Step("Нажатие на секцию Булки")
  public void clickHeaderBuns() {
    driver.findElement(headerBuns).click();
  }

  @Step("Нажатие на секцию Соусы")
  public void clickHeaderSauce() {
    driver.findElement(headerSauce).click();
  }

  @Step("Нажатие на секцию Начинки")
  public void clickHeaderFilling() {
    driver.findElement(headerFilling).click();
  }

  @Step("Получение названия текущего раздела")
  public String getHeaderCurrent() {
    return driver.findElement(headerCurrent).getText();
  }
}
