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

@DisplayName("Личный кабинет")
public class AccountTest {

  private static User user;

  @BeforeClass
  public static void beforeAll() {
    BrowserConfigurator.useDefault();

    user = generateUser();
    open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage()
        .clickAccount()
        .shouldOpenLoginPage()
        .clickRegister()
        .register(user)
        .shouldSuccessRegister();
  }

  @Before
  public void beforeEach() {
    var page = open(Environment.STAND_URL, ConstructorPage.class)
        .assertThatConstructorPage();

    if (!page.isAuthorized()) {
      page.clickAccount()
          .shouldOpenLoginPage()
          .login(user)
          .shouldSuccessLogin();
    }
  }

  @Test
  @DisplayName("Переход в личный кабинет из конструктора")
  public void shouldGotoAccountPage() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenAccountPage();
  }

  @Test
  @DisplayName("Выход из аккаунта через личный кабинет")
  public void shouldLogout() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenAccountPage()
        .clickLogout()
        .assertThatLoginPage();
  }

  @Test
  @DisplayName("Переход из личного кабинета в конструктор по клику на кнопку 'Конструктор' ы хидере")
  public void shouldGotoConstructorPageByClickConstructorButton() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenAccountPage()
        .clickConstructor()
        .assertThatConstructorPage();
  }

  @Test
  @DisplayName("Переход из личного кабинета в конструктор по клику на логотип в хидере")
  public void shouldGotoConstructorPageByClickLogo() {
    open(Environment.STAND_URL, ConstructorPage.class)
        .clickAccount()
        .shouldOpenAccountPage()
        .clickLogo()
        .assertThatConstructorPage();
  }

}