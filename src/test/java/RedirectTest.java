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
public class RedirectTest {
    private WebDriver webDriver;
    private final String browser;
    RegistrationPage registrationPage;
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    @Parameterized.Parameters
    public static Object[] browsers() {
        return new Object[] { "chrome", "yandex" }; // Параметры браузеров для теста
    }

    public RedirectTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = BrowserPage.getDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(webDriver);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        profilePage = new ProfilePage(webDriver);
    }

    @Test
    @DisplayName("Переход авторизованного пользователя в личный кабинет")
    public void redirectAuthorizedUserToPersonalAccountTest() throws InterruptedException {
        mainPage.openConstructorPage();
        mainPage.clickToPersonalAccount();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Попытка перехода неавторизованного пользователя в личный кабинет с перенаправлением на страницу логин")
    public void redirectNoAuthorizedUserToPersonalAccountTest() throws InterruptedException {
        mainPage.openConstructorPage();
        mainPage.clickToPersonalAccount();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Переход авторизованного юзера по логотипу сайта из ЛК в конструктор")
    public void redirectAuthorizedUserInPersonalAccountToMainPageTest() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();
        profilePage.clickToLogo();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Переход авторизованного юзера по кнопке конструктор из ЛК в конструктор")
    public void redirectAuthorizedUserInPersonalAreaToConstructorTest() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();
        profilePage.clickToConstructor();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Выход авторизованного пользователя из личного кабинета")
    public void exitAuthorizedUserInPersonalAccountTest() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.loginInAccountFromUI();
        mainPage.clickToPersonalAccount();
        profilePage.clickToExit();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void tearDown() {
        registrationPage.deleteUserInApi();
        BrowserPage.driverQuit(webDriver);
    }
}
