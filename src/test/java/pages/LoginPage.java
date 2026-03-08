package pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class LoginPage extends BasePage{

    private String pageUrl = Config.baseUri+"/login";

    //Заголовок формы авторизации
    private By headerLoginForm = xpath(".//h2[text()='Вход']");
    //Поле "Email"
    private By emailField = xpath(".//input[@name = 'name']");
    //Поле "Пароль"
    private By passwordField = xpath(".//input[@name = 'Пароль']");
    //Кнопка "Войти"
    private By loginButton = xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//button");

     public LoginPage(WebDriver driver) {
         super(driver);
         driver.get(pageUrl);
     }

     @Step("Ожидаю пока загрузится страница авторизации")
     public void waitLoginPageToLoad(){
         new WebDriverWait(driver, Duration.ofSeconds(3))
                 .until(ExpectedConditions.visibilityOfElementLocated(headerLoginForm));
     }

    @Step("")
    public void setEmailField(String email) {
         driver.findElement(emailField).sendKeys(email);
    }

    @Step("")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("")
    public ConstructorPage clickLoginButton(){
         driver.findElement(loginButton).click();
         return new ConstructorPage(driver);
    }

    @Step("")
    public void setLoginFormFields(String email, String password) {
         setEmailField(email);
         setPasswordField(password);
    }
}
