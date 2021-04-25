package ru.itmo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static ru.itmo.DriverConfig.getCurrentDriver;
import static ru.itmo.PropertyNames.CHROME_DRIVER;

public class AddAndRemoveFromCartTest {
    private static WebDriver driver;
    static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        driver = getCurrentDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void addRemoveFromCartTest() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[contains(text(),'Одежда')]")).click();
        driver.findElement(By.xpath("(//div[contains(@class,'products-catalog__list')]//descendant::a)[1]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Выберите размер')]")).click();
        driver.findElement(By.xpath("(//div[contains(text(),'RUS')])[1]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Добавить в корзину')]//parent::button")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Товар добавлен в корзину')]")).isDisplayed());
        driver.get("https://www.lamoda.ru/checkout/cart/");
        driver.findElement(By.xpath("//div[@class='cpi']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Удалить')]")).click();
        driver.navigate().refresh();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'В корзине нет товаров')]")).isDisplayed());
    }
}
