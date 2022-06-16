import config.BrowserConfigurator;
import config.Environment;
import io.qameta.allure.junit4.DisplayName;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.page.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@DisplayName("Конструктор")
public class ConstructorTest {

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();
  }

  @Test
  @DisplayName("Переход к булкам по клику на вкладку 'Булки'")
  public void shouldGotoBuns() {
    var selectedTabText = open(Environment.STAND_URL, ConstructorPage.class)
        .clickSaucesTab()
        .clickBunsTab()
        .getSelectedTabText();
    assertEquals(ConstructorPage.BUNS_TAB_TEXT, selectedTabText);
  }

  @Test
  @DisplayName("Переход к соусам по клику на вкладку 'Соусы'")
  public void shouldGotoSauces() {
    var selectedTabText = open(Environment.STAND_URL, ConstructorPage.class)
        .clickSaucesTab()
        .getSelectedTabText();
    assertEquals(ConstructorPage.SAUCES_TAB_TEXT, selectedTabText);
  }

  @Test
  @DisplayName("Переход к начинкам по клику на вкладку 'Начинки'")
  public void shouldGotoFillings() {
    var selectedTabText = open(Environment.STAND_URL, ConstructorPage.class)
        .clickFillingsTab()
        .getSelectedTabText();
    assertEquals(ConstructorPage.FILLINGS_TAB_TEXT, selectedTabText);
  }

}