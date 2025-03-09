import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Automate {

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
    }
    @Test
    public void fillform(){
        driver.findElement(By.name("name")).sendKeys("amy");
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']")).sendKeys("avaadmas@gmail.com");
        driver.findElement(By.xpath("//button[.='Signup']")).click();
    }





    @Test(dataProvider = "loginData")
    public void testLogin(String pass, String firstname,
                          String lastname, String address, String state,String city,String zip, String mobile
    ) {


        try {

            // Locate input fields and enter credentials
            WebElement  pass1 = driver.findElement(By.id("password"));
            WebElement  firstname1 = driver.findElement(By.id("first_name"));
            WebElement  lastname1 = driver.findElement(By.id("last_name"));
            WebElement  address1 = driver.findElement(By.xpath("//p[4]/input[@class='form-control']"));
            WebElement  state2 = driver.findElement(By.id("state"));
            WebElement  city2 = driver.findElement(By.id("city"));
            WebElement  zipcode = driver.findElement(By.id("zipcode"));
            WebElement  mobilenum = driver.findElement(By.id("mobile_number"));
            Select drop = new Select(driver.findElement(By.id("days")));
            drop.selectByValue(String.valueOf(21));
            Select drop1 = new Select(driver.findElement(By.id("months")));
            drop1.selectByValue(String.valueOf(9));
            Select drop2 = new Select(driver.findElement(By.id("years")));
            drop2.selectByValue(String.valueOf(2000));



            pass1.sendKeys(pass);
            firstname1.sendKeys(firstname);
            lastname1.sendKeys(lastname);
            address1 .sendKeys(address);
            state2.sendKeys(state);
            city2.sendKeys(city);
            zipcode.sendKeys(zip);
            mobilenum.sendKeys(mobile);
            driver.findElement(By.xpath("//button[.='Create Account']")).click();


        } catch (Exception e) {
            System.out.println("Account created successfully ");
        }
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException, CsvException {
        String csvFilePath = "src/main/java/automate.csv"; // Ensure this path is correct
        List<String[]> testData = readTestData(csvFilePath);

        Object[][] data = new Object[testData.size() - 1][8]; // Skipping headers
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