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

public class AddToCartTest {
    private static WebDriver driver;
    static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\testing\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void addToCartTest() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[contains(text(),'Обувь')]")).click();
        driver.findElement(By.xpath("(//div[contains(@class,'products-catalog__list')]//descendant::a)[1]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Выберите размер')]")).click();
        driver.findElement(By.xpath("(//div[contains(text(),'RUS')])[1]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Добавить в корзину')]//parent::button")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Товар добавлен в корзину')]")).isDisplayed());
    }
}
