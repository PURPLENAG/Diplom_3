package config;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConfigurator {

  public static final BrowserType DEFAULT_BROWSER_TYPE = BrowserType.CHROME;

  public enum BrowserType {
    CHROME, YANDEX
  }

  public static void useDefault() {
    useBrowser(DEFAULT_BROWSER_TYPE);
  }

  public static void useBrowser(BrowserType browserType) {
    if (browserType == BrowserType.CHROME) {
      useChrome();
    } else if (browserType == BrowserType.YANDEX) {
      useYandex();
    } else {
      throw new UnsupportedOperationException(
          String.format("BrowserType '%s' is not supported!", browserType));
    }
  }

  public static void useChrome() {
  }

  public static void useYandex() {
    var manager = ChromeDriverManager.getInstance(DriverManagerType.CHROME);
    manager.driverVersion("96.0.4664.45");
    //manager.driverRepositoryUrl();
    manager.setup();

    var prefs = new HashMap<String, Object>();
    prefs.put("credentials_enable_service", false);

    var options = new ChromeOptions();
    //options.addArguments("--start-maximized");
    options.setBinary(
        "C:\\Users\\evil\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
    options.setExperimentalOption("prefs", prefs);

    WebDriverRunner.setWebDriver(new ChromeDriver(options));
  }
}
