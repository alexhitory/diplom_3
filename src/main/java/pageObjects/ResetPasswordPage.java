package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {

    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Открытие страницы восстанавления пароля")
    public void openResetPasswordPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Клик по кнопке входа на странице восстанавления пароля")
    public void clickToEnter() {
        webDriver.findElement(elementsLocators.enterBottomForgotPage).click();
    }
}