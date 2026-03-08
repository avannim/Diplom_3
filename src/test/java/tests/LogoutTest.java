package tests;

import config.Config;
import dto.request.CreateUserRequest;
import dto.response.CreateAndLoginUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConstructorPage;
import pages.PersonalAccountPage;
import steps.RestSteps;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {


    private final RestSteps rest = new RestSteps();

    CreateAndLoginUserResponse user;
    private ConstructorPage page;
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Config.setupBrowser("chrome");
        user = rest.createUserByRest(new CreateUserRequest("MauMen15@nation.org", "miey32gaui", "Мау"));
        driver.get(Config.baseUri);
        rest.setLocalStorage(user, driver);
        page = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Тест выхода из личного аккаунта")
    public void checkLogoutTest(){
        PersonalAccountPage accountPage = page.clickPersonalAccountButton();
        accountPage.clickLogoutButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/login"));
        assertTrue(driver.findElement(By.xpath(".//h2[text()='Вход']")).isDisplayed());
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        rest.deleteUserRest(user.getAccessToken());
    }
}
