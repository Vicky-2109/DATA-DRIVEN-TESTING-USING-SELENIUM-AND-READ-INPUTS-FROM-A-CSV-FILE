import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Newtest {

    private WebDriver driver;

    // Read data from CSV
    public static List<String[]> readTestData(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            return csvReader.readAll();
        }
    }

    @BeforeClass
    public void setUp() {
        //chrome open setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/signup");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        boolean loginSuccess = driver.getTitle().contains("Automation Exercise");

        try {
            WebElement txtbox = driver.findElement(By.id("email"));
            WebElement pass = driver.findElement(By.name("password"));
            WebElement button = driver.findElement(By.xpath("//button[.='Login']"));
            txtbox.clear();
            txtbox.sendKeys(username);
            pass.clear();
            pass.sendKeys(password);
            button.click();



            // Log  result
            if (loginSuccess) {
                System.out.println("Login successful for user: " + username);
            }
        } catch (Exception e) {
            System.out.println("Login failed for user: " + username);
        }
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException, CsvException {
        String csvFilePath = "src/main/java/newtest.csv"; // Adjust based on your file location
        List<String[]> testData = readTestData(csvFilePath);

        Object[][] data = new Object[testData.size() - 1][2];
        for (int i = 1; i < testData.size(); i++) {
            data[i - 1] = testData.get(i);
        }
        return data;
    }

    @AfterClass
    public void tearDown() {
    // close the driver
        if (driver != null) {
            driver.quit();
        }
    }
}