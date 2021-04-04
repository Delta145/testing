package ru.itmo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RegistrationTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Study\\testing\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void registration() {
    driver.get("https://www.lamoda.ru/women-home/");
    driver.manage().window().maximize();
    driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Создать аккаунт')]")).click();
    driver.findElement(By.xpath("(//input[contains(@placeholder,'email')])[2]")).click();
    driver.findElement(By.xpath("(//input[contains(@placeholder,'email')])[2]")).sendKeys("dummy.email@domain.ru");
    driver.findElement(By.xpath("//input[contains(@placeholder,'Придумайте пароль')]")).click();
    driver.findElement(By.xpath("//input[contains(@placeholder,'Придумайте пароль')]")).sendKeys("Test1234");
    driver.findElement(By.xpath("//input[contains(@placeholder,'Повторите пароль')]")).click();
    driver.findElement(By.xpath("//input[contains(@placeholder,'Повторите пароль')]")).sendKeys("Test1234");
    driver.findElement(By.xpath("//input[contains(@placeholder,'свое имя')]")).click();
    driver.findElement(By.xpath("//input[contains(@placeholder,'свое имя')]")).sendKeys("Георгий");
    driver.findElement(By.xpath("//button[contains(text(),'Зарегистрироваться')]")).click();
  }
}
