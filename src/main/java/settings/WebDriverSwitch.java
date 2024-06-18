package settings;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;

public class WebDriverSwitch {

  protected WebDriver driver;

  private static final String YANDEX_BROWSER_PATH =
      "C:\\Users\\harry\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
  private static final String YANDEX_DRIVER_PATH = "src/main/resources/yandexdriver.exe";

  @Before
  public void setup() {
    driver = init();
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  public static WebDriver init() {
    WebDriver driver;
    String browserName = System.getProperty("browser");
    if (browserName == null || browserName.isEmpty()) {
      browserName = "yandex"; // Запускает Yandex по умолчанию, если нужно поменять - пишем "chrome"
    }

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    switch (browserName.toLowerCase()) {
      case "chrome":
        driver = new ChromeDriver(options);
        driver = new Augmenter().augment(driver);
        break;
      case "yandex":
        System.setProperty("webdriver.chrome.driver",YANDEX_DRIVER_PATH);
        options.setBinary(YANDEX_BROWSER_PATH);
        driver = new ChromeDriver(options);
        driver = new Augmenter().augment(driver);
        break;
      default:
        throw new RuntimeException("Браузер не установлен: " + browserName);
    }

    return driver;
  }
}
