package ru.itmo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.itmo.DriverConfig.getCurrentDriver;
import static ru.itmo.PropertyNames.CHROME_DRIVER;

public class CheckQAListTest {
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
    public void checkQAListTest() {
        driver.get("https://www.lamoda.ru/");
        driver.manage().window().maximize();
        // accept cookies
        driver.findElement(By.xpath("//button[contains(text(),'Хорошо')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'email')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'email')]")).sendKeys("sawin.georgij@yandex.ru");
        driver.findElement(By.xpath("//input[contains(@placeholder,'пароль')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'пароль')]")).sendKeys("Test1234");
        driver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        {
            // Ввод капчи, АААААА
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//iframe[contains(@title, 'captcha')]"), 0));
        }
        Assertions.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Отзывы и вопросы')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Вопросы')]")).isDisplayed());
    }
}
