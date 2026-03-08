package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public abstract class BasePage {

    WebDriver driver;

    //Кнопка Конструктора
    private By constructorButton = xpath(".//header//a[@href = '/']");
    //Кнопка Личного кабинета
    private By personalAccountButton = xpath(".//header//a[@href = '/account']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаю на кнопку \"Конструктор\"")
    public ConstructorPage clickConstructorButton(){
        driver.findElement(constructorButton).click();
        return new ConstructorPage(driver);
    }

    @Step("Нажимаю на кнопку \"Личный Кабинет\"")
    public PersonalAccountPage clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
        return new PersonalAccountPage(driver);
    }

}
