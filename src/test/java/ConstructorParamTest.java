import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import pageObject.MainPage;
import settings.WebDriverSwitch;

@RunWith(Parameterized.class)
public class ConstructorParamTest extends WebDriverSwitch {
  MainPage objMainPage;

  private final String sectionName;
  private final String expectedHeader;

  public ConstructorParamTest(String sectionName, String expectedHeader) {
    this.sectionName = sectionName;
    this.expectedHeader = expectedHeader;
  }

  @Parameterized.Parameters(name = "Название секции: {0}, Ожидаемое: {1}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        { "Булки", "Булки" },
        { "Соусы", "Соусы" },
        { "Начинки", "Начинки" }
    });
  }

  @Test
  @DisplayName("Переход по секциям конструктора")
  public void sectionConstructorTest() {
    objMainPage = new MainPage(driver);
    objMainPage.openMainPage();
    objMainPage.waitForLoadMainPage();
    objMainPage.clickConstructorButton();

    switch (sectionName) {
      case "Булки":
        objMainPage.clickHeaderSauce(); // клик в другой раздел так, как Булки выбраны по умолчанию
        objMainPage.clickHeaderBuns();
        break;
      case "Соусы":
        objMainPage.clickHeaderSauce();
        break;
      case "Начинки":
        objMainPage.clickHeaderFilling();
        break;
    }

    assertEquals(expectedHeader, objMainPage.getHeaderCurrent());
  }
}

