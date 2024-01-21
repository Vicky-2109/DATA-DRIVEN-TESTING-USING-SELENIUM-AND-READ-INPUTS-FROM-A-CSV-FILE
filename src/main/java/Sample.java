import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.className;

public class Sample {

    private static ChromeDriver driver;

    @BeforeClass
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin() {
        driver.manage().deleteAllCookies();
        driver.get("https://facegenie-ams-school.web.app/");

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("testbams@gmail.com");

        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("facegenie");
        pass.sendKeys(Keys.ENTER);
    }



    @Test
    public void addStudent() {
        driver.manage().deleteAllCookies();
        WebElement element;
        element = driver.findElement(By.xpath("//p[.='Add student using Form']"));
        element.click();


    }
    @Test
    public void invalidLogin() {
        // Your invalid login test code here
        // ...
        driver.manage().deleteAllCookies();
        driver.get("https://facegenie-ams-school.web.app/");

        WebElement searchInput = driver.findElement(By.name("email"));
        searchInput.sendKeys("testbams@gmail.com");
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("facegenie");
        pass.sendKeys(Keys.ENTER);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
