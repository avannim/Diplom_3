package tests;

import config.Config;
import dto.response.CreateAndLoginUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import steps.RestSteps;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    private final RestSteps rest = new RestSteps();
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Config.setupBrowser("chrome");
    }

    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    public void checkSuccessfulRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setRegistrationFormFields("Мау","MauMen15@nation.org","miey32gaui");
        registrationPage.clickRegistrationButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/login"));
        assertTrue(driver.findElement(By.xpath(".//h2[text()='Вход']")).isDisplayed());
        CreateAndLoginUserResponse user = rest.loginUserRest("MauMen15@nation.org","miey32gaui");
        rest.deleteUserRest(user.getAccessToken());
    }

    @Test
    @DisplayName("Тест ошибки ввода некорректного пароля при регистрации пользователя")
    public void checkErrorRegistrationWithWrongPasswordTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setNameField("Мау");
        registrationPage.setEmailField("MauMen15@nation.org");
        registrationPage.setPasswordField("V23f");
        registrationPage.clickRegistrationButton();
        assertTrue(driver.findElement(By.xpath(".//div[@class = 'input__container']/p[text() = 'Некорректный пароль']")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
