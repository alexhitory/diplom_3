package pageObjects;

import org.openqa.selenium.By;

public class ElementsLocators {

    //Страница регистрации

    public By inputDataFieldForRegistration = By.cssSelector("input.text.input__textfield.text_type_main-default");
    public By buttonForRegistration = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    public By passwordErrorMassage = By.xpath("//p[contains(text(), 'Некорректный пароль')]");
    public By buttonOfEnterRegPage = By.xpath("//a[@href='/login']");

    //Главная страница

    public By buttonOfEnterInAccount = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    public By buttonOfEnterInPersonalArea = By.xpath("//a[@href='/account']");
    public By buttonOfBuns = By.xpath("//span[contains(text(), 'Булки')]");
    public By buttonOfSauces = By.xpath("//span[contains(text(), 'Соусы')]");
    public By buttonOfFillings = By.xpath("//span[contains(text(), 'Начинки')]");
    public By bunsHeader = By.xpath("//h2[contains(text(), 'Булки')]");
    public By saucesHeader = By.xpath("//h2[contains(text(), 'Соусы')]");
    public By fillingsHeader = By.xpath("//h2[contains(text(), 'Начинки')]");
    public By activeTabLocator = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    //Страница авторизации

    public By inputDataFieldForLogin = By.xpath("//*/input[@class='text input__textfield text_type_main-default']");
    public By enterBottomLogPage = By.xpath("//button[contains(text(), 'Войти')]");
    public By enterBottomForgotPage = By.xpath("//a[@href='/login']");

    //Личный кабинет

    public By exitBottomProfilePage = By.xpath("//li/button");
    public By logoutBottomLogPage = By.xpath("//div/a[@href='/']");
    public By constructorBottomLogPage = By.xpath("//li/a[@href='/']");

}
