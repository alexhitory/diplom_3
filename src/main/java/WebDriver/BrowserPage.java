package WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserPage {
    private static WebDriver webdriver;

    public static WebDriver getDriver(String browser) {

        WebDriver webDriver = null;

        switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    webdriver = new ChromeDriver();
                    break;
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", ".\\src\\main\\java\\WebDriver\\yandexdriver.exe");
                    webdriver = new ChromeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

        return webdriver;
    }
    public static void driverQuit(WebDriver webDriver) {
        webDriver.quit();
    }
}