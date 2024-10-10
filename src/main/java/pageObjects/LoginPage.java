package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import api.Api;
import user.User;
import user.UserGenerator;

public class LoginPage {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Открытие страницы логин")
    public void openLoginPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Создание юзера через апи и авторизация на странице логина")
    public void loginInAccountFromUI() {
        User user = UserGenerator.randomUser();
        Api.createUserInApi(user);
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(0).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(1).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.enterBottomLogPage).click();
    }
}