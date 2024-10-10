package pageObjects;

import api.Api;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import user.User;
import user.UserGenerator;

public class RegistrationPage {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;
    private final Faker faker;
    private User user;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
        faker = new Faker();
    }

    @Step("Открытие страницы регистрации")
    public void openRegistrationPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Заполнение формы корректными данными")
    public User fillingRegistrationFormCorrectData() {
        user = UserGenerator.randomUser();

        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(2).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.buttonForRegistration).click();
        return user;
    }

    @Step("Заполенение формы с паролем меньше 6 символов")
    public void fillingRegistrationFormShortPassword() {
        user = UserGenerator.randomUser();

        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistration).get(2).sendKeys(user.getIncorrectPassword());
        webDriver.findElement(elementsLocators.buttonForRegistration).click();
    }

    @Step("Удаление юзера через апи")
    public void deleteUserInApi() {
        Api.deleteUser();
    }

    @Step("Логин юзером через апи")
    public Response loginUserInApi(User user) {
        Response response = Api.loginUser(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Логин юзером через апи")
    public Response loginUserInApi() {
        Response response = Api.loginUser(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Проверка видимости ошибки ввода пароля")
    public boolean passwordError() {
        return webDriver.findElement(elementsLocators.passwordErrorMassage).isDisplayed();
    }

    @Step("Клик по кнопке входа на странице регистрации")
    public void clickToEnterBottom() {
        webDriver.findElement(elementsLocators.buttonOfEnterRegPage).click();
    }
}