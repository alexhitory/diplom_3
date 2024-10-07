import WebDriver.BrowserPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScrollMenuTest {
    private WebDriver webDriver;
    private final String browser;
    MainPage mainPage;

    @Parameterized.Parameters
    public static Object[] browsers() {
        return new Object[] { "chrome", "yandex" }; // Параметры браузеров для теста
    }

    public ScrollMenuTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = BrowserPage.getDriver(browser);
        mainPage = new MainPage(webDriver);
        mainPage.openConstructorPage();

    }

    @Test
    @DisplayName("Тест перехода к разделу булки")
    public void scrollToBunsSectionTest() throws InterruptedException {
        mainPage.scrollToFillings();
        mainPage.scrollToBuns();

        assertTrue(mainPage.bunsIsVisible());
    }

    @Test
    @DisplayName("Тест перехода к разделу начинки")
    public void scrollToFillingsSectionTest() throws InterruptedException {
        mainPage.scrollToSouse();

        assertTrue(mainPage.souseIsVisible());
    }

    @Test
    @DisplayName("Тест перехода к разделу соусы")
    public void scrollToSouseSectionTest() throws InterruptedException {
        mainPage.scrollToFillings();

        assertTrue(mainPage.fillingsIsVisible());
    }

    @After
    public void tearDown() {
        BrowserPage.driverQuit(webDriver);
    }
}

