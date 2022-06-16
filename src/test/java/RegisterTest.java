import config.BrowserConfigurator;
import config.Environment;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.page.ConstructorPage;
import ui.page.RegisterPage;
import util.Ryuk;

import static com.codeborne.selenide.Selenide.open;
import static util.DataGenerator.generateUser;

@DisplayName("Регистрация")
public class RegisterTest {

  private final Ryuk ryuk = new Ryuk();

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();
  }

  @Before
  public void beforeEach() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage().logoutIfAuthorized();
  }

  @After
  public void afterEach() {
    ryuk.wakeUp();
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
        .register(ryuk.remember(user))
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
        .register(ryuk.remember(user))
        .shouldFailRegister()
        .shouldHaveFormError(RegisterPage.INCORRECT_PASSWORD_ERROR_TEXT);
  }

}