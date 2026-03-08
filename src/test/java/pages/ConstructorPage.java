package pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class ConstructorPage extends BasePage{

    private final String pageUrl = Config.baseUri;

    //Заголовок секции "Собрать заказ"
    private By consructorHeader = xpath(".//h1[text()='Соберите бургер']");
    //Кнопка "Войти в аккаунт"
    private By loginButton = xpath(".//section[@class = 'BurgerConstructor_basket__29Cd7 mt-25 ']//button");
    //Кнопка Меню
    private String menuButton = ".//div[contains(@class,'tab_tab__1SPyG') and span[text()='%s']]";
    //Контейнер Меню
    public By menuContainer = xpath(".//div[@class= 'BurgerIngredients_ingredients__menuContainer__Xu3Mo']");
    //Подзаголовок Меню
    public String menuSubHeader = ".//h2[text()='%s']";


    public ConstructorPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
    }

    @Step("Ожидаю пока загрузится страница авторизации")
    public void waitConstructorPageToLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@draggable='true' and p[text()='Флюоресцентная булка R2-D3']]")));
    }

    @Step("Нажимаю на кнопку \"Войти в аккаунт\"")
    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажимаю на кнопку меню {menuButtonName}")
    public void clickMenuButton(String menuButtonName){
        WebElement element;
        switch(menuButtonName){
            case("Соусы"):
                element = driver.findElement(By.xpath(String.format(menuButton, menuButtonName)));
                System.out.println(element.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
                if(!element.getAttribute("class").contains("tab_tab_type_current__2BEPc")) {
                    element.click();
                    new WebDriverWait(driver, Duration.ofSeconds(3))
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@draggable='true' and p[text()='Соус Spicy-X']]")));
                }
                break;
            case("Начинки"):
                element = driver.findElement(By.xpath(String.format(menuButton, menuButtonName)));
                System.out.println(element.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
                if(!element.getAttribute("class").contains("tab_tab_type_current__2BEPc")) {
                    element.click();
                    new WebDriverWait(driver, Duration.ofSeconds(3))
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@draggable='true' and p[text()='Мясо бессмертных моллюсков Protostomia']]")));
                }
                break;
            case("Булки"):
                element = driver.findElement(By.xpath(String.format(menuButton, menuButtonName)));
                System.out.println(element.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
                if(!element.getAttribute("class").contains("tab_tab_type_current__2BEPc")){
                    element.click();
                    new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@draggable='true' and p[text()='Флюоресцентная булка R2-D3']]")));
                }
                break;
            default:
                System.out.println(String.format("Пункт меню \"%s\" отсутсnвует", menuButtonName));
        }
    }

    @Step("Получаю элемент \"контейнер Меню\"")
    public WebElement getMenuContainer(){
        return driver.findElement(menuContainer);
    }

    @Step("Получаю элемент заголовка {menuHeaderName}")
    public WebElement getMenuSubHeader(String menuSubHeaderName){
        return driver.findElement(By.xpath(String.format(menuSubHeader, menuSubHeaderName)));
    }
}
