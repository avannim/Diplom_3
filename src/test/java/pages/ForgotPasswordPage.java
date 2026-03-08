package pages;

import config.Config;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage{

    private String pageUrl = Config.baseUri+"/forgot-password";

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
    }
}
