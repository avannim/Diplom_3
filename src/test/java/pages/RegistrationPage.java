package pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class RegistrationPage extends BasePage{

    private final String pageUrl = Config.baseUri+"/register";

    //Поле "Имя"
    private By nameField = xpath(".//input[@name = 'name' and preceding-sibling::label[text() = 'Имя']]");
    //Поле "Email"
    private By emailField = xpath(".//input[@name = 'name'and preceding-sibling::label[text() = 'Email']]");
    //Поле "Пароль"
    private By passwordField = xpath(".//input[@name = 'Пароль']");
    //Кнопка "Зарегистрироваться"
    private By registrationButton = xpath(".//form[@class = 'Auth_form__3qKeq mb-20']/button");
    //Кнопка "Вход"
    private By loginButton = xpath(".//a[@class = 'Auth_link__1fOlj']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
    }

    @Step("Ввожу значение \"{name}\" в поле \"Имя\"")
    public void setNameField(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввожу значение \"{email}\" в поле \"Email\"")
    public void setEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввожу значение \"{password}\" в поле \"Пароль\"")
    public void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаю кнопку \"Зарегистрироваться\"")
    public void clickRegistrationButton(){
        driver.findElement(registrationButton).click();
    }

    @Step("Заполняю форму регистрации")
    public void setRegistrationFormFields(String name, String email, String password){
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Нажимаю на кнопку \"Войти\"")
    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

}
