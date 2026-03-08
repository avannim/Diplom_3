package tests;

import config.Config;
import dto.request.CreateUserRequest;
import dto.response.CreateAndLoginUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.RecoveryPage;
import pages.RegistrationPage;
import steps.RestSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private final RestSteps rest = new RestSteps();
    private final CreateUserRequest newUser = new CreateUserRequest("MauMen15@nation.org", "miey32gaui", "Мау");;
    CreateAndLoginUserResponse user;
    ConstructorPage homePage;
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Config.setupBrowser("chrome");
        user = rest.createUserByRest(newUser);
    }

    @Test
    public void checkLoginfromHomePageTest(){
        driver.get(Config.baseUri);
        homePage = new ConstructorPage(driver);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.waitLoginPageToLoad();
        loginPage.setLoginFormFields("MauMen15@nation.org","miey32gaui");
        loginPage.clickLoginButton();
        assertTrue(driver.findElement(By.xpath(".//h1[text()='Соберите бургер']")).isDisplayed());
    }

    @Test
    public void checkLoginFromPersonalAccountButtonTest(){
        driver.get(Config.baseUri);
        homePage = new ConstructorPage(driver);
        homePage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageToLoad();
        loginPage.setLoginFormFields("MauMen15@nation.org","miey32gaui");
        loginPage.clickLoginButton();
        assertTrue(driver.findElement(By.xpath(".//h1[text()='Соберите бургер']")).isDisplayed());
    }

    @Test
    public void checkLoginFromRegistrationPageTest(){
        RegistrationPage regisrationPage = new RegistrationPage(driver);
        LoginPage loginPage = regisrationPage.clickLoginButton();
        loginPage.waitLoginPageToLoad();
        loginPage.setLoginFormFields("MauMen15@nation.org","miey32gaui");
        loginPage.clickLoginButton();
        assertTrue(driver.findElement(By.xpath(".//h1[text()='Соберите бургер']")).isDisplayed());
    }

    @Test
    public void checkLoginFromRecoveryPasswordPageTest(){
        RecoveryPage recoveryPage = new RecoveryPage(driver);
        LoginPage loginPage= recoveryPage.clickLoginButton();
        loginPage.waitLoginPageToLoad();
        loginPage.setLoginFormFields("MauMen15@nation.org","miey32gaui");
        loginPage.clickLoginButton();
        assertTrue(driver.findElement(By.xpath(".//h1[text()='Соберите бургер']")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        rest.deleteUserRest(user.getAccessToken());
    }
}
