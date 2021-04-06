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

public class CheckSubscribeSalesTest {
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
    public void checkSubscribeAndUnsubscribeTest() {
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
        driver.findElement(By.xpath("//a[contains(text(),'Управление подписками')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Распродажи и акции')]//following::button[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Готово')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Ежедневно на почту')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'По SMS')]")).isDisplayed());
        // отписываемся
        driver.findElement(By.xpath("//a[contains(text(),'Изменить')]")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Ежедневно')]//parent::select[1]")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Никогда')]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'x-checkbox__box')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Готово')]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Распродажи и акции')]//following::button[1][contains(text(), 'Подписаться')]")).isDisplayed());
    }
}
