package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Клик по логотипу")
    public void clickToLogo() {
        webDriver.findElement(elementsLocators.logoutBottomLogPage).click();
    }

    @Step("Клик по кнопке конструктор")
    public void clickToConstructor() {
        webDriver.findElement(elementsLocators.constructorBottomLogPage).click();
    }

    @Step("Клик по кнопке выход из профиля")
    public void clickToExit() throws InterruptedException {
        webDriver.findElement(elementsLocators.exitBottomProfilePage).click();
        Thread.sleep(1500);
    }
}