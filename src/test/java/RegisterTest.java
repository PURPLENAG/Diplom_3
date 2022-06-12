import static com.codeborne.selenide.Selenide.open;
import static utils.DataGenerator.generateUser;

import config.BrowserConfigurator;
import config.Environment;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import page.ConstructorPage;
import page.RegisterPage;

@DisplayName("Регистрация")
public class RegisterTest {

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();
  }

  @Before
  public void beforeEach() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage().logoutIfAuthorized();
  }

  @Test
  @DisplayName("Успешная регистрация")
  public void shouldSuccessRegister() {
    var user = generateUser();

    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage()
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRegister()
        .register(user)
        .shouldSuccessRegister();
  }

  @Test
  @DisplayName("Ошибка заполнения формы регистрации - короткий пароль")
  public void shouldFailRegisterWithShortPassword() {
    var user = generateUser();
    user.setPassword("at_p");

    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage()
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRegister()
        .register(user)
        .shouldFailRegister()
        .shouldHaveFormError(RegisterPage.INCORRECT_PASSWORD_ERROR_TEXT);
  }

}