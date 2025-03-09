import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AutomateCSV {

    private WebDriver driver;

    // Read data from CSV
    public static List<String[]> readTestData(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            return csvReader.readAll();
        }
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        //chrome open setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/signup");
        driver.findElement(By.xpath("//button[.='Signup']")).click();
        driver.findElement(By.name("name")).sendKeys("vicky");
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']")).sendKeys("stev@gmail.com");

        driver.wait();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String User,String email,String pass, String firstnme,
                          String lastname, String adress, String state,String city,String zip, String mobile
                          ) {
        boolean loginSuccess = false;


        try {

            // Locate input fields and enter credentials
            WebElement txtbox = driver.findElement(By.id("name"));
            WebElement email1 = driver.findElement(By.id("email"));
            WebElement  pass1 = driver.findElement(By.id("password"));
            Select drop = new Select(driver.findElement(By.id("days")));
            drop.selectByValue(String.valueOf(21));
            Select drop1 = new Select(driver.findElement(By.id("months")));
            drop1.selectByValue(String.valueOf(9));
            Select drop2 = new Select(driver.findElement(By.id("years")));
            drop2.selectByValue(String.valueOf(2000));
            WebElement  firstname = driver.findElement(By.id("first_name"));
            WebElement  lastname1 = driver.findElement(By.id("last_name"));
            WebElement  address = driver.findElement(By.xpath("//p[4]/input[@class='form-control']"));
            WebElement  state2 = driver.findElement(By.id("state"));
            WebElement  city2 = driver.findElement(By.id("city"));
            WebElement  zipcode = driver.findElement(By.id("zipcode"));
            WebElement  mobilenum = driver.findElement(By.id("mobile_number"));



            txtbox.clear();
            txtbox.sendKeys(User);
            email1.sendKeys(email);
            pass1.sendKeys(pass);
            firstname.sendKeys(firstnme);
            lastname1.sendKeys(lastname);
           address .sendKeys(adress);
            state2.sendKeys(state);
            city2.sendKeys(city);
            zipcode.sendKeys(zip);
            mobilenum.sendKeys(mobile);
            driver.findElement(By.xpath("//button[.='Create Account']")).click();

          ;

            if (loginSuccess) {
                System.out.println("Login successful for user");
            }
        } catch (Exception e) {
            System.out.println("Invalid details ");
        }
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException, CsvException {
        String csvFilePath = "src/main/java/automate.csv"; // Adjust based on your file location
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