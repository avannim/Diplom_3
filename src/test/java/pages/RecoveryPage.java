package pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class RecoveryPage extends BasePage{

    private final String pageUrl = Config.baseUri+"/forgot-password";

    private By loginButton = xpath(".//a[@class = 'Auth_link__1fOlj']");

    public RecoveryPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
    }

    @Step("Нажимаю на кнопку \"Вход\"")
    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }
}
