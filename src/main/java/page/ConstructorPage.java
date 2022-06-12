package page;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static util.SelenideConditions.clickable;

import org.openqa.selenium.By;

public class ConstructorPage {

  public static final String HEADER_TEXT = "Соберите бургер";

  public static final int TABS_COUNT = 3;
  public static final String BUNS_TAB_TEXT = "Булки";
  public static final String SAUCES_TAB_TEXT = "Соусы";
  public static final String FILLINGS_TAB_TEXT = "Начинки";

  private final By overlays = byCssSelector(".Modal_modal_overlay__x2ZCr");
  private final By header = byCssSelector("main h1");
  private final By accountBtn = byLinkText("Личный Кабинет");
  private final By loginBtn = byText("Войти в аккаунт");
  private final By orderBtn = byText("Оформить заказ");
  private final By tabs = byCssSelector("main div.tab_tab__1SPyG");
  private final By selectedTab = byCssSelector(
      "main div.tab_tab__1SPyG.tab_tab_type_current__2BEPc");

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
    waitLoading();
    $$(tabs).should(size(TABS_COUNT)).find(text(BUNS_TAB_TEXT)).should(clickable).click();
    delay();
    return this;
  }

  public ConstructorPage clickSaucesTab() {
    waitLoading();
    $$(tabs).should(size(TABS_COUNT)).find(text(SAUCES_TAB_TEXT)).should(clickable).click();
    delay();
    return this;
  }

  public ConstructorPage clickFillingsTab() {
    waitLoading();
    $$(tabs).should(size(TABS_COUNT)).find(text(FILLINGS_TAB_TEXT)).should(clickable).click();
    delay();
    return this;
  }

  public String getSelectedTabText() {
    waitLoading();
    return $(selectedTab).getText();
  }

  private void waitLoading() {
    $$(overlays).forEach(it -> it.should(hidden));
  }

  private void delay() {
    try {
      Thread.sleep(600);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
