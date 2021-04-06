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

public class ChangeDeliveryRegionTest {
    private static WebDriver driver;
    static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\testing\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void changeDeliveryRegion() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[contains(text(),'г. ')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Барнаул')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Запомнить выбор')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'г. Барнаул')]")).isDisplayed());
    }
}
