package ru.itmo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.itmo.PropertyNames.*;

public class DriverConfig {
    static {
        String chrome = ConfProperties.getProperty(CHROME_DRIVER);
        String firefox = ConfProperties.getProperty(FIREFOX_DRIVER);
        String currentDriver = ConfProperties.getProperty(CURRENT_DRIVER);
        switch (currentDriver) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", firefox);
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", chrome);
        }
    }

    public static WebDriver getCurrentDriver() {
        String currentDriver = ConfProperties.getProperty(CURRENT_DRIVER);
        switch (currentDriver) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
            default:
                return new ChromeDriver();
        }
    }
}
