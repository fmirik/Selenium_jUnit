import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

public class AE01 {
    /*
    https://automationexercise.com/test_cases
    Test Case 1: Register User
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click on 'Signup / Login' button
    5. Verify 'New User Signup!' is visible
    6. Enter name and email address
    7. Click 'Signup' button
    8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
    9. Fill details: Title, Name, Email, Password, Date of birth
    10. Select checkbox 'Sign up for our newsletter!'
    11. Select checkbox 'Receive special offers from our partners!'
    12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
    13. Click 'Create Account button'
    14. Verify that 'ACCOUNT CREATED!' is visible
    15. Click 'Continue' button
    16. Verify that 'Logged in as username' is visible
    17. Click 'Delete Account' button
    18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
     */
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addExtensions(new File("./extension.crx"));//uBlock Origin Extension
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        //driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("https://automationexercise.com");
        Thread.sleep(2000);
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath(" //a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Signup / Login' button
        WebElement singUp = driver.findElement(By.xpath("//a[@href='/login']"));
        singUp.click();
        //5. Verify 'New User Signup!' is visible
        String actual = driver.findElement(By.xpath("//h2[.='New User Signup!']")).getText();
        String expected = "New User Signup!";
        Assert.assertEquals(actual, expected);
        //6. Enter name and email address
        String username = "abc5";
        String email = "abc5@sample.com";
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(username);
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']")).sendKeys(email);
        //7. Click 'Signup' button
        driver.findElement(By.xpath("//button[.='Signup']")).click();
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        String actualInfo = driver.findElement(By.xpath("//b[.='Enter Account Information']")).getText();
        String expectedInfo = "ENTER ACCOUNT INFORMATION";
        Assert.assertTrue(actualInfo.equalsIgnoreCase(expectedInfo));
        //9. Fill details: Title, Name, Email, Password, Date of birth
        driver.findElement(By.xpath("//input[@value='Mr']")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Abc123456");
        WebElement day = driver.findElement(By.xpath("//select[@id='days']"));
        WebElement month = driver.findElement(By.xpath("//select[@id='months']"));
        WebElement year = driver.findElement(By.xpath("//select[@id='years']"));
        Select dayDropDown = new Select(day);
        Select monthDropDown = new Select(month);
        Select yearDropDown = new Select(year);
        dayDropDown.selectByValue("10");
        monthDropDown.selectByValue("3");
        yearDropDown.selectByValue("1990");
        //10. Select checkbox 'Sign up for our newsletter!'
        driver.findElement(By.xpath("//input[@id='newsletter']\n")).click();
        //11. Select checkbox 'Receive special offers from our partners!'
        driver.findElement(By.xpath("//input[@id='optin']")).click();
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Ali");
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Veli");
        driver.findElement(By.xpath("//input[@id='company']")).sendKeys("No Company");
        driver.findElement(By.xpath("//p[4]/input[@class='form-control']")).sendKeys("Adres");
        driver.findElement(By.xpath("//p[5]/input[@class='form-control']")).sendKeys("Adres2");
        driver.findElement(By.xpath("//option[.='Canada']")).click();
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Toronto");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Clairlea");
        driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("12300");
        driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("1231112233");
        //13. Click 'Create Account button'
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        //14. Verify that 'ACCOUNT CREATED!' is visible
        String actualAdress = driver.findElement(By.xpath("//b[.='Account Created!']")).getText();
        String expectedAdress = "ACCOUNT CREATED!";
        Assert.assertTrue(actualAdress.equalsIgnoreCase(expectedAdress));
        //15. Click 'Continue' button
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        //16. Verify that 'Logged in as username' is visible
        String actualLogin = driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText();
        String expectedLogin = "Logged in as " + username;
        Assert.assertTrue(actualLogin.equalsIgnoreCase(expectedLogin));
        //17. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[contains(.,'Delete Account')]")).click();
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        driver.findElement(By.xpath("//b[.='Account Deleted!']")).isDisplayed();
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
    }

}
