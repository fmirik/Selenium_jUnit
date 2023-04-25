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

public class AE23 {
    /*
    Test Case 23: Verify address details in checkout page
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click 'Signup / Login' button
    5. Fill all details in Signup and create account
    6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
    7. Verify ' Logged in as username' at top
    8. Add products to cart
    9. Click 'Cart' button
    10. Verify that cart page is displayed
    11. Click Proceed To Checkout
    12. Verify that the delivery address is same address filled at the time registration of account
    13. Verify that the billing address is same address filled at the time registration of account
    14. Click 'Delete Account' button
    15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
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
        driver.close();
    }

    @Test
    public void test23() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click 'Signup / Login' button
        WebElement singUp = driver.findElement(By.xpath("//a[@href='/login']"));
        singUp.click();
        //5. Fill all details in Signup and create account
        String username = "abc5";
        String email = "abc5@sample.com";
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(username);
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[.='Signup']")).click();
        Thread.sleep(2000);
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
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        //6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        String verify = driver.findElement(By.xpath("//h2[@data-qa='account-created']")).getText();
        Assert.assertEquals("ACCOUNT CREATED!", verify);
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        //7. Verify ' Logged in as username' at top
        String actualLogin = driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText();
        String expectedLogin = "Logged in as " + username;
        Assert.assertTrue(actualLogin.equalsIgnoreCase(expectedLogin));
        //8. Add products to cart
        driver.findElement(By.xpath("//div[@class='features_items']/div[2]//div[@class='productinfo text-center']/a[.='Add to cart']")).click();
        //9. Click 'Cart' button
        driver.findElement(By.xpath("//u[.='View Cart']")).click();
        //10. Verify that cart page is displayed
        Assert.assertTrue(driver.getTitle().contains("Checkout"));
        //11. Click Proceed To Checkout
        driver.findElement(By.xpath("//a[.='Proceed To Checkout']")).click();
        //12. Verify that the delivery address is same address filled at the time registration of account
        WebElement adress = driver.findElement(By.xpath("(//h3[@class='page-subheading'])[1]"));
        adress.isDisplayed();
        //13. Verify that the billing address is same address filled at the time registration of account
        //14. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
        //15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        driver.findElement(By.xpath("//b[.='Account Deleted!']")).isDisplayed();
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
    }
}
