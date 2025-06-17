import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.*;

import java.time.Duration;


public class Practice {
        public static WebDriver driver;


        @BeforeClass
        public void Setup() {
            WebDriverManager.chromiumdriver().setup();
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            }



        @Test
        public void testWebpage() {
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys("student");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("Password123");
            WebElement button = driver.findElement(By.id("submit"));
            button.sendKeys(Keys.ENTER);
        }

        @AfterClass
        public void tearDown() {
            // close the driver
            if (driver != null) {
                driver.quit();
            }
        }
    }


