import static com.codeborne.selenide.Selenide.open;
import static utils.DataGenerator.generateUser;

import config.BrowserConfigurator;
import config.Environment;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import page.ConstructorPage;

@DisplayName("Авторизация")
public class LoginTest {

  private static User user;

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();

    user = generateUser();
    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage()
        .logoutIfAuthorized()
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRegister()
        .register(user)
        .shouldSuccessRegister();
  }

  @Before
  public void beforeEach() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage().logoutIfAuthorized();
  }

  @Test
  @DisplayName("Авторизация через кнопку 'Войти в аккаунт' в конструкторе")
  public void shouldLoginByConstructorLoginButton() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickLogin()
        .shouldOpenLoginPage()
        .login(user)
        .shouldSuccessLogin();
  }

  @Test
  @DisplayName("Авторизация через кнопку 'Личный кабинет' в хидере")
  public void shouldLoginByHeaderAccountButton() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenLoginPage()
        .login(user)
        .shouldSuccessLogin();
  }

  @Test
  @DisplayName("Авторизация через кнопку 'Войти' под формой регистрации")
  public void shouldLoginByRegisterFormButton() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRegister()
        .clickLogin()
        .login(user)
        .shouldSuccessLogin();
  }

  @Test
  @DisplayName("Авторизация через кнопку 'Войти' под формой восстановления пароля")
  public void shouldLoginByRestorePasswordFormButton() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRestorePassword()
        .clickLogin()
        .login(user)
        .shouldSuccessLogin();
  }

}