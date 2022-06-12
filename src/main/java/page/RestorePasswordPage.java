package page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static util.SelenideConditions.clickable;

import org.openqa.selenium.By;

public class RestorePasswordPage {

  public static final String FORM_HEADER_TEXT = "Восстановление пароля";

  private final By formHeader = byCssSelector("main h2");
  private final By loginBtn = byLinkText("Войти");

  public RestorePasswordPage assertThatRestorePasswordPage() {
    $(formHeader).should(text(FORM_HEADER_TEXT));
    return this;
  }

  public LoginPage clickLogin() {
    $(loginBtn).should(clickable).click();
    return page(LoginPage.class).assertThatLoginPage();
  }

}
