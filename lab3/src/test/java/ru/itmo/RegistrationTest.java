package ru.itmo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static ru.itmo.DriverConfig.getCurrentDriver;

public class RegistrationTest {
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
    public void registration() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Создать аккаунт')]")).click();
        driver.findElement(By.xpath("(//input[contains(@placeholder,'email')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@placeholder,'email')])[2]")).sendKeys("sawing.georgij@yandex.ru");
        driver.findElement(By.xpath("//input[contains(@placeholder,'Придумайте пароль')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'Придумайте пароль')]")).sendKeys("Test1234");
        driver.findElement(By.xpath("//input[contains(@placeholder,'Повторите пароль')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'Повторите пароль')]")).sendKeys("Test1234");
        driver.findElement(By.xpath("//input[contains(@placeholder,'свое имя')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'свое имя')]")).sendKeys("Георгий");
        driver.findElement(By.xpath("//button[contains(text(),'Зарегистрироваться')]")).click();
    }
}
