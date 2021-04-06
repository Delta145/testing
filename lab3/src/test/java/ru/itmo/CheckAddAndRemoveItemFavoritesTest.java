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

public class CheckAddAndRemoveItemFavoritesTest {
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
    public void checkAddItemToFavoritesTest() {
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
        driver.get("https://www.lamoda.ru/");
        driver.findElement(By.xpath("//a[contains(text(),'Обувь')]")).click();
        driver.findElement(By.xpath("(//div[contains(@class,'products-catalog__list')]//descendant::a)[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'icon_heart-product')]")).click();
        driver.get("https://www.lamoda.ru/wishlist/");
        driver.findElement(By.xpath("//div[contains(@class, 'to-favorites')]//parent::a")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'icon_heart-product')]")).click();
        driver.get("https://www.lamoda.ru/wishlist/");
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'В избранном нет товаров')]")).isDisplayed());
    }
}
