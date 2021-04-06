package ru.itmo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static ru.itmo.DriverConfig.getCurrentDriver;
import static ru.itmo.PropertyNames.CHROME_DRIVER;

public class CheckFAQTest {
    private static WebDriver driver;
    static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        driver = getCurrentDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void checkFAQTest() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        // accept cookies
        driver.findElement(By.xpath("//button[contains(text(),'Хорошо')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Часто задаваемые вопросы')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Часто задаваемые вопросы')]")).isDisplayed());
    }
}
