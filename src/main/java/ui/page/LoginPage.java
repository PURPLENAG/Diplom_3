package ui.page;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ui.model.User;
import ui.page.LoginPage.LoginFormField;

import java.util.Set;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ui.util.SelenideConditions.clickable;

public class LoginPage implements FormPage<LoginPage, LoginFormField> {

  public static final String FORM_HEADER_TEXT = "Вход";

  private final By header = byCssSelector("header");
  private final By headerConstructor = byText("Конструктор");
  private final By headerLogo = byCssSelector("header .AppHeader_header__logo__2D0X2>a");
  private final By formHeader = byCssSelector("main h2");
  private final By formFields = byCssSelector("main form fieldset input");
  private final By loginBtn = byCssSelector("main form>button");
  private final By registerBtn = byLinkText("Зарегистрироваться");
  private final By restorePasswordBtn = byLinkText("Восстановить пароль");

  public enum LoginFormField {
    EMAIL, PASSWORD
  }

  @Override
  public ElementsCollection getFormElements() {
    return $$(formFields);
  }

  @Override
  public Set<LoginFormField> getFormFields() {
    return Set.of(LoginFormField.values());
  }

  public LoginPage setValues(User user) {
    setFormValue(LoginFormField.EMAIL, user.getEmail());
    setFormValue(LoginFormField.PASSWORD, user.getPassword());
    return this;
  }

  public LoginPage assertThatLoginPage() {
    $(formHeader).should(text(FORM_HEADER_TEXT));
    return this;
  }

  public ConstructorPage clickConstructor() {
    $(header).$(headerConstructor).should(clickable).click();
    return page(ConstructorPage.class).assertThatConstructorPage();
  }

  public ConstructorPage clickLogo() {
    $(header).$(headerLogo).should(clickable).click();
    return page(ConstructorPage.class).assertThatConstructorPage();
  }

  public LoginPage clickLogin() {
    $(loginBtn).should(clickable).click();
    return this;
  }

  public RegisterPage clickRegister() {
    $(registerBtn).should(clickable).click();
    return page(RegisterPage.class).assertThatRegisterPage();
  }

  public RestorePasswordPage clickRestorePassword() {
    $(restorePasswordBtn).should(clickable).click();
    return page(RestorePasswordPage.class).assertThatRestorePasswordPage();
  }

  public LoginPage login(User user) {
    return assertThatLoginPage()
        .setValues(user)
        .clickLogin();
  }

  public ConstructorPage shouldSuccessLogin() {
    return page(ConstructorPage.class).assertThatConstructorPage();
  }

  public LoginPage shouldFailLogin() {
    return this.assertThatLoginPage();
  }

}
