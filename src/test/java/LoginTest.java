import api.client.AuthClient;
import common.converter.UserConverter;
import config.BrowserConfigurator;
import config.Environment;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.model.User;
import ui.page.ConstructorPage;
import util.Ryuk;

import static com.codeborne.selenide.Selenide.open;
import static util.DataGenerator.generateUser;

@DisplayName("Авторизация")
public class LoginTest {

  private static final Ryuk ryuk = new Ryuk();
  private static User user;

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();
    user = generateUser();
    AuthClient.register(ryuk.remember(UserConverter.toUserDto(user)));
  }

  @AfterClass
  public static void afterAll() {
    ryuk.wakeUp();
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