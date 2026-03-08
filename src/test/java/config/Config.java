package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Config {

    public static String baseUri = "https://stellarburgers.education-services.ru";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .build();
    }

    public static WebDriver setupBrowser(String browser){

        ChromeOptions options = new ChromeOptions();

        switch(browser.toLowerCase()) {
            case ("chrome"):
                WebDriverManager.chromedriver().setup();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
            case("yandex"):
                String yandexDriverPath;
                try {
                File driverFile = new File(Config.class.getClassLoader()
                        .getResource("drivers/yandexdriver.exe").getFile());

                yandexDriverPath = URLDecoder.decode(driverFile.getAbsolutePath(), StandardCharsets.UTF_8);
                } catch (Exception e) {
                    System.err.println("Cannot find driver: " + "/drivers/yandexdriver.exe" + " -> " + e.getMessage());
                    return null;
                }
                System.setProperty("webdriver.chrome.driver", yandexDriverPath);
                options.setBinary(System.getProperty("user.home") +
                        "/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
    }
}
