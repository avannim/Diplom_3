package pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class PersonalAccountPage extends BasePage{

    private final String pageUrl = Config.baseUri+"/account/profile";

    //Кнопка "Профиль"
    private By profileButton = xpath(".//a[text() = 'Профиль']");
    //Кнопка "Выход"
    private By logoutButton = xpath(".//ul[@class = 'Account_list__3KQQf mb-20']//button");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаю на кнопку \"Выход\"")
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидаю пока загрузится страница личного кабинета")
    public void waitPersonalAccountPageToLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }
}
