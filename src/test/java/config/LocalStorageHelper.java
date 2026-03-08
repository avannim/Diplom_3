package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorageHelper {
    private final JavascriptExecutor js;

    public LocalStorageHelper(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void setItem(String key, String value) {
        js.executeScript(String.format("localStorage.setItem('%s', '%s');", key, value));
    }

    public String getItem(String key) {
        return (String) js.executeScript("return localStorage.getItem(arguments[0]);", key);
    }

    public void removeItem(String key) {
        js.executeScript("localStorage.removeItem(arguments[0]);", key);
    }

    public void clear() {
        js.executeScript("localStorage.clear();");
    }
}
