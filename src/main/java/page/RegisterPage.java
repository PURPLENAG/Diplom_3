package page;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static util.SelenideConditions.clickable;

import com.codeborne.selenide.ElementsCollection;
import java.util.Set;
import model.User;
import org.openqa.selenium.By;
import page.RegisterPage.RegisterFormField;

public class RegisterPage implements FormPage<RegisterPage, RegisterFormField> {

  public static final String FORM_HEADER_TEXT = "Регистрация";
  public static final String INCORRECT_PASSWORD_ERROR_TEXT = "Некорректный пароль";

  private final By formHeader = byCssSelector("main h2");
  private final By formFields = byCssSelector("main form fieldset input");
  private final By registerBtn = byCssSelector("main form>button");
  private final By formErrors = byCssSelector("main form .input__error");
  private final By loginBtn = byLinkText("Войти");

  public enum RegisterFormField {
    NAME, EMAIL, PASSWORD
  }

  @Override
  public ElementsCollection getFormElements() {
    return $$(formFields);
  }

  @Override
  public Set<RegisterFormField> getFormFields() {
    return Set.of(RegisterFormField.values());
  }

  public RegisterPage setValues(User user) {
    return setFormValue(RegisterFormField.NAME, user.getName())
        .setFormValue(RegisterFormField.EMAIL, user.getEmail())
        .setFormValue(RegisterFormField.PASSWORD, user.getPassword());
  }

  public RegisterPage assertThatRegisterPage() {
    $(formHeader).should(text(FORM_HEADER_TEXT));
    return this;
  }

  public RegisterPage clickRegister() {
    $(registerBtn).should(clickable).click();
    return this;
  }

  public LoginPage clickLogin() {
    $(loginBtn).should(clickable).click();
    return page(LoginPage.class).assertThatLoginPage();
  }

  public RegisterPage register(User user) {
    return assertThatRegisterPage()
        .setValues(user)
        .clickRegister();
  }

  public LoginPage shouldSuccessRegister() {
    return page(LoginPage.class).assertThatLoginPage();
  }

  public RegisterPage shouldFailRegister() {
    this.assertThatRegisterPage();
    $$(formErrors).should(sizeGreaterThan(0));
    return this;
  }

  public RegisterPage shouldHaveFormError(String errorText) {
    $$(formErrors).should(sizeGreaterThan(0))
        .find(text(errorText)).should(exist, visible);
    return this;
  }

}
