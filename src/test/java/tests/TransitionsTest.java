package tests;

import config.Config;
import dto.request.CreateUserRequest;
import dto.response.CreateAndLoginUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import pages.ConstructorPage;
import pages.PersonalAccountPage;
import steps.CheckSteps;
import steps.RestSteps;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransitionsTest {

    public WebDriver driver;
    private final RestSteps rest = new RestSteps();
    private CreateAndLoginUserResponse user;
    private ConstructorPage page;

    @BeforeEach
    public void setUp() {
        driver = Config.setupBrowser("chrome");
        user = rest.createUserByRest(new CreateUserRequest("MauMen15@nation.org", "miey32gaui", "Мау"));
        driver.get(Config.baseUri);
        rest.setLocalStorage(user, driver);
        page = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Тест перехода в Личный кабинет со страницы Конструктора")
    public void checkPersonalAccountTransitionTest() {
        PersonalAccountPage accountPage = page.clickPersonalAccountButton();
        accountPage.waitPersonalAccountPageToLoad();
        assertTrue(driver.findElement(By.xpath(".//input[@name='Name' and @value='Мау']")).isDisplayed());
    }

    @Test
    @DisplayName("Тест перехода на страницу Конструктора из Личного кабинета")
    public void checkPersonalAccountToConstructorTransitionTest() {
        PersonalAccountPage accountPage = page.clickPersonalAccountButton();
        accountPage.clickConstructorButton();
        page.waitConstructorPageToLoad();
        assertTrue(driver.findElement(By.xpath(".//h1[text()='Соберите бургер']")).isDisplayed());
    }

    @ParameterizedTest
    @MethodSource("menuHeaderSource")
    @DisplayName("Тест по проверке переходов между разделами меню на странице Конструктора")
    public void checkConstructorSectionsTransitionTest(String menuButtonName, boolean firstChildChek, boolean secondChildChek, boolean thirdChildChek) {
        page.waitConstructorPageToLoad();
        page.clickMenuButton(menuButtonName);
        WebElement parent = page.getMenuContainer();
        WebElement firstSubHeder = driver.findElement(By.xpath(String.format(page.menuSubHeader,"Булки")));
        WebElement secondSubHeder = driver.findElement(By.xpath(String.format(page.menuSubHeader,"Соусы")));
        WebElement thirdSubHeder = driver.findElement(By.xpath(String.format(page.menuSubHeader,"Начинки")));
        assertEquals(firstChildChek, CheckSteps.isChildInsideParent(parent.getRect(),firstSubHeder.getRect()));
        assertEquals(secondChildChek, CheckSteps.isChildInsideParent(parent.getRect(), secondSubHeder.getRect()));
        assertEquals(thirdChildChek, CheckSteps.isChildInsideParent(parent.getRect(), thirdSubHeder.getRect()));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        rest.deleteUserRest(user.getAccessToken());
    }

    static Stream<Arguments> menuHeaderSource() {
        return Stream.of(
                Arguments.of("Булки", true, true, false),
                Arguments.of("Соусы", false, true, true),
                Arguments.of("Начинки", false, false, true)
        );
    }
}