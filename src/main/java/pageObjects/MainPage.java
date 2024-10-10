package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;
    private WebDriverWait wait;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
        wait = new WebDriverWait(webDriver, 3);
    }

    @Step("Открытие страницы конструктора")
    public void openConstructorPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Клик по кнопке входа в аккаунт")
    public void clickToEnterAccount() {
        webDriver.findElement(elementsLocators.buttonOfEnterInAccount).click();
    }

    @Step("Клик по кнопке личный кабинет")
    public void clickToPersonalAccount() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfEnterInPersonalArea).click();
        Thread.sleep(1500);
    }

    @Step("Проматывание до раздела булки")
    public void scrollToBuns() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfBuns).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.bunsHeader));
        Thread.sleep(1000);
    }

    @Step("Проматывание до раздела соусы")
    public void scrollToSouse() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfSauces).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.saucesHeader));
        Thread.sleep(1000);
    }

    @Step("Проматывание до раздела начинки")
    public void scrollToFillings() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfFillings).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.fillingsHeader));
        Thread.sleep(1000);
    }

    @Step("Проверка видимости раздела булки")
    public boolean bunsIsVisible() {
        String tabText = webDriver.findElement(elementsLocators.activeTabLocator).getText();
        if (!tabText.equals("Булки")) {
            throw new AssertionError("Текст активной вкладки не соответствует ожидаемому: 'Булки', фактический текст: '" + tabText + "'.");
        }
        return true;
    }

    @Step("Проверка видимости раздела соусы")
    public boolean souseIsVisible() {
        String tabText = webDriver.findElement(elementsLocators.activeTabLocator).getText();
        if (!tabText.equals("Соусы")) {
            throw new AssertionError("Текст активной вкладки не соответствует ожидаемому: 'Соусы', фактический текст: '" + tabText + "'.");
        }
        return true;
    }

    @Step("Проверка видимости раздела начинки")
    public boolean fillingsIsVisible() {
        String tabText = webDriver.findElement(elementsLocators.activeTabLocator).getText();
        if (!tabText.equals("Начинки")) {
            throw new AssertionError("Текст активной вкладки не соответствует ожидаемому: 'Начинки', фактический текст: '" + tabText + "'.");
        }
        return true;
    }
}