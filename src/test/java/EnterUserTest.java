import WebDriver.BrowserPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EnterUserTest {
    private WebDriver webDriver;
    private final String browser;
    RegistrationPage registrationPage;
    MainPage mainPage;
    LoginPage loginPage;
    ResetPasswordPage resetPasswordPage;
    private final String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";

    @Parameterized.Parameters
    public static Object[] browsers() {
        return new Object[] { "chrome", "yandex" }; // Параметры браузеров для теста
    }

    public EnterUserTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = BrowserPage.getDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(webDriver);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        resetPasswordPage = new ResetPasswordPage(webDriver);
        registrationPage.openRegistrationPage();  // Открытие страницы регистрации
    }

    @Test
    @DisplayName("Успешный вход по кнопке «Войти в аккаунт» на главной")
    public void loginInMainPageTest() throws InterruptedException {
        mainPage.openConstructorPage();
        mainPage.clickToEnterAccount();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();

        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Личный кабинет»")
    public void loginInPersonalAccountTest() throws InterruptedException {
        mainPage.openConstructorPage();
        mainPage.clickToPersonalAccount();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();

        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Успешный вход через кнопку в форме регистрации")
    public void loginInRegistrationPageTest() throws InterruptedException {
        registrationPage.openRegistrationPage();
        registrationPage.clickToEnterBottom();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();

        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Успешный вход через кнопку в форме восстановления пароля")
    public void loginInResetPasswordTest() throws InterruptedException {
        resetPasswordPage.openResetPasswordPage();
        resetPasswordPage.clickToEnter();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();

        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void tearDown() {
        registrationPage.deleteUserInApi();
        BrowserPage.driverQuit(webDriver);
    }
}