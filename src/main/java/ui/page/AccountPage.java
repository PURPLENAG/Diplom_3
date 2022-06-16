package ui.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.util.SelenideConditions.clickable;

public class AccountPage {

  private static final int MENU_ITEM_COUNT = 3;
  private static final String MENU_ACCOUNT_TEXT = "Профиль";
  private static final String MENU_ORDER_HISTORY_TEXT = "История заказов";
  private static final String MENU_LOGOUT_TEXT = "Выход";

  private final By header = byCssSelector("header");
  private final By headerConstructor = byText("Конструктор");
  private final By headerLogo = byCssSelector("header .AppHeader_header__logo__2D0X2>a");
  private final By menuItems = byCssSelector("main nav ul li a, main nav ul li button");

  public AccountPage assertThatAccountPage() {
    $$(menuItems).should(size(MENU_ITEM_COUNT), texts(
        MENU_ACCOUNT_TEXT, MENU_ORDER_HISTORY_TEXT, MENU_LOGOUT_TEXT));
    return this;
  }

  public LoginPage clickLogout() {
    $$(menuItems).should(size(MENU_ITEM_COUNT)).find(text(MENU_LOGOUT_TEXT))
        .should(clickable).click();
    return page(LoginPage.class).assertThatLoginPage();
  }

  public ConstructorPage clickConstructor() {
    $(header).$(headerConstructor).should(clickable).click();
    return page(ConstructorPage.class).assertThatConstructorPage();
  }

  public ConstructorPage clickLogo() {
    $(header).$(headerLogo).should(clickable).click();
    return page(ConstructorPage.class).assertThatConstructorPage();
  }
}
