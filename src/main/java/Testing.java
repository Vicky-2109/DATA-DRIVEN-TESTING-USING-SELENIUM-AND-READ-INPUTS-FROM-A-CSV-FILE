import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Testing {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testSignupLogin() {
        driver.get("http://automationexercise.com");
        wait.until(ExpectedConditions.titleContains("Automation Exercise"));

        String pageTitle = driver.getTitle();
        // Verify the title
        Assert.assertEquals(pageTitle, "Automation Exercise");
        System.out.println("Test Passed: Page title is correct.");

        // Find and click the "Signup / Login" link
        WebElement signUpLoginLink = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
        signUpLoginLink.click();

        // Wait until the "New User Signup!" element is visible
        WebElement newUserSignupText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'New User Signup!')]")));
        // Verify the "New User Signup!" text is displayed
        Assert.assertTrue(newUserSignupText.isDisplayed());
        System.out.println("Test Passed: 'New User Signup!' is displayed.");

        // Enter the name and email for new user signup
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        nameInput.sendKeys("Vicky");

        WebElement emailInput = driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']"));
        emailInput.sendKeys("xasipew771@mcatag.com");

        // Find and click the "Signup" button
        WebElement signupButton = driver.findElement(By.xpath("//button[.='Signup']"));
        signupButton.click();

        // Wait until the next page is loaded and a specific element is visible
        WebElement accountInfoText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Enter Account Information')]")));

        // Verify the presence of the "Enter Account Information" text
        Assert.assertTrue(accountInfoText.isDisplayed());
        System.out.println("Test Passed: 'Enter Account Information' is displayed.");

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
