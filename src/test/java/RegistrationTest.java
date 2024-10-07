import WebDriver.BrowserPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.RegistrationPage;
import user.User;


import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.hc.core5.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.matchesRegex;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver webDriver;
    private final String browser;
    RegistrationPage registrationPage;

    @Parameterized.Parameters
    public static Object[] browsers() {
        return new Object[] { "chrome", "yandex" }; // Параметры браузеров для теста
    }

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = BrowserPage.getDriver(browser);
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.openRegistrationPage();
    }

    @Test
    @DisplayName("Попытка регистрации с паролем меньше 6 символов")
    public void userRegistrationNotValidPasswordTest() {
        registrationPage.fillingRegistrationFormShortPassword();
        registrationPage.loginUserInApi().then()
                .statusCode(SC_UNAUTHORIZED);
        assertTrue(registrationPage.passwordError());
    }

    @Test
    @DisplayName("Успешная регистрация с валидными данными")
    public void userRegistrationTest() {
        User user = registrationPage.fillingRegistrationFormCorrectData();
        registrationPage.loginUserInApi(user).then()
                .statusCode(SC_OK)
                .assertThat().body("accessToken", matchesRegex("^Bearer .*"));
    }

    @After
    public void tearDown() {
        registrationPage.deleteUserInApi();
        BrowserPage.driverQuit(webDriver);
    }
}