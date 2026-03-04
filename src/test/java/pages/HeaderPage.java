package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class HeaderPage extends BasePage{

    //Кнопка Конструктора
    private By constructorButton = xpath("//header//a[@href = '/']");
    //Кнопка Личного кабинета
    private By personalAccountButton = xpath("//header//a[@href = '/account']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public ConstructorPage clickConstructorButton(){
        driver.findElement(constructorButton).click();
        return new ConstructorPage(driver);
    }

    public PersonalAccountPage clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
        return new PersonalAccountPage(driver);
    }

}
