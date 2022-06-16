package ui.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ui.util.SelenideConditions.clickable;

public class ConstructorPage {

  public static final String HEADER_TEXT = "Соберите бургер";

  public static final int TABS_COUNT = 3;
  public static final String BUNS_TAB_TEXT = "Булки";
  public static final String SAUCES_TAB_TEXT = "Соусы";
  public static final String FILLINGS_TAB_TEXT = "Начинки";
  public static final String SELECTED_TAB_CSS_CLASS = "tab_tab_type_current__2BEPc";

  private final By overlays = byCssSelector(".Modal_modal_overlay__x2ZCr");
  private final By header = byCssSelector("main h1");
  private final By accountBtn = byLinkText("Личный Кабинет");
  private final By loginBtn = byText("Войти в аккаунт");
  private final By orderBtn = byText("Оформить заказ");
  private final By tabs = byCssSelector("main div.tab_tab__1SPyG");
  private final By selectedTab = byCssSelector("main div.tab_tab__1SPyG.tab_tab_type_current__2BEPc");

  public ConstructorPage assertThatConstructorPage() {
    $(header).should(text(HEADER_TEXT));
    return this;
  }

  public ConstructorPage clickAccount() {
    waitLoading();
    $(accountBtn).should(clickable).click();
    return this;
  }

  public ConstructorPage clickLogin() {
    waitLoading();
    $(loginBtn).should(clickable).click();
    return this;
  }

  public ConstructorPage clickOrder() {
    waitLoading();
    $(orderBtn).should(clickable).click();
    return this;
  }

  public LoginPage shouldOpenLoginPage() {
    return page(LoginPage.class).assertThatLoginPage();
  }

  public AccountPage shouldOpenAccountPage() {
    return page(AccountPage.class).assertThatAccountPage();
  }

  public boolean isAuthorized() {
    waitLoading();
    return $(loginBtn).is(disappear) && $(orderBtn).is(clickable);
  }

  public ConstructorPage logoutIfAuthorized() {
    if (isAuthorized()) {
      return clickAccount()
          .shouldOpenAccountPage()
          .clickLogout()
          .assertThatLoginPage()
          .clickConstructor();
    }
    return this;
  }

  public ConstructorPage clickBunsTab() {
    return clickTab(BUNS_TAB_TEXT);
  }

  public ConstructorPage clickSaucesTab() {
    return clickTab(SAUCES_TAB_TEXT);
  }

  public ConstructorPage clickFillingsTab() {
    return clickTab(FILLINGS_TAB_TEXT);
  }

  public String getSelectedTabText() {
    waitLoading();
    return $(selectedTab).getText();
  }

  private ConstructorPage clickTab(String tabText) {
    waitLoading();
    var prevTabText = getSelectedTabText();
    var prevTab = $$(tabs).should(size(TABS_COUNT)).find(text(prevTabText));
    var nextTab = $$(tabs).should(size(TABS_COUNT)).find(text(tabText));

    prevTab.should(cssClass(SELECTED_TAB_CSS_CLASS));
    nextTab.should(not(cssClass(SELECTED_TAB_CSS_CLASS)));

    nextTab.should(clickable).should(clickable).click(usingDefaultMethod());
    waitForStrangeThings();

    prevTab.should(not(cssClass(SELECTED_TAB_CSS_CLASS)));
    nextTab.should(cssClass(SELECTED_TAB_CSS_CLASS));

    return this;
  }

  private void waitLoading() {
    $$(overlays).forEach(it -> it.should(hidden));
  }

  // Костыль от которого не получилось избавиться
  //
  // Проблема:
  // при клике на вкладку класс "выбранности" (SELECTED_TAB_CSS_CLASS)
  // начинает "прыгать" по всем вкладкам от 1-го до 4-х раз,
  // не понятно как такое поведение отловить явными/неявными ожиданиями.
  //
  // Вероятно это специально оставленная пасхалка, ещё бы узнать как такое победить :)
  private void waitForStrangeThings() {
    try {
      Thread.sleep(400);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
