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

public class TestCSV {

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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://facegenie-ams-school.web.app/");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        boolean loginSuccess = false;

        try {
            // Locate input fields and enter credentials
            WebElement txtbox = driver.findElement(By.id("email"));
            WebElement pass = driver.findElement(By.id("password"));
            WebElement button = driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-fullWidth MuiButtonBase-root  css-a5malo']"));

            txtbox.clear();
            txtbox.sendKeys(username);
            pass.clear();
            pass.sendKeys(password);
            button.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            loginSuccess = wait.until(ExpectedConditions.urlContains("https://facegenie-ams-school.web.app/dashboard/home"));

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
        String csvFilePath = "src/main/java/data.csv"; // Adjust based on your file location
        List<String[]> testData = readTestData(csvFilePath);

        Object[][] data = new Object[testData.size() - 2][3];
        for (int i = 2; i < testData.size(); i++) {
            data[i - 2] = testData.get(i);
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