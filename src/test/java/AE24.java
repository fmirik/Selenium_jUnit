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
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AE24 {
    /*
    Test Case 24: Download Invoice after purchase order
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Add products to cart
    5. Click 'Cart' button
    6. Verify that cart page is displayed
    7. Click Proceed To Checkout
    8. Click 'Register / Login' button
    9. Fill all details in Signup and create account
    10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
    11. Verify ' Logged in as username' at top
    12.Click 'Cart' button
    13. Click 'Proceed To Checkout' button
    14. Verify Address Details and Review Your Order
    15. Enter description in comment text area and click 'Place Order'
    16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
    17. Click 'Pay and Confirm Order' button
    18. Verify success message 'Your order has been placed successfully!'
    19. Click 'Download Invoice' button and verify invoice is downloaded successfully.
    20. Click 'Continue' button
    21. Click 'Delete Account' button
    22. Verify 'ACCOUNT DELETED!' and click 'Continue' button
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
    public void test24() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Add products to cart
        driver.findElement(By.xpath("//div[@class='features_items']/div[2]//div[@class='productinfo text-center']/a[.='Add to cart']")).click();
        //5. Click 'Cart' button
        driver.findElement(By.xpath("//u[.='View Cart']")).click();
        //6. Verify that cart page is displayed
        Assert.assertTrue(driver.getTitle().contains("Checkout"));
        //7. Click Proceed To Checkout
        driver.findElement(By.xpath("//a[.='Proceed To Checkout']")).click();
        //8. Click 'Register / Login' button
        driver.findElement(By.xpath("//a[@href='/login']/u")).click();
        //9. Fill all details in Signup and create account
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
        //10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        String verify = driver.findElement(By.xpath("//h2[@data-qa='account-created']")).getText();
        Assert.assertEquals("ACCOUNT CREATED!", verify);
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        //11. Verify ' Logged in as username' at top
        String actualLogin = driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText();
        String expectedLogin = "Logged in as " + username;
        Assert.assertTrue(actualLogin.equalsIgnoreCase(expectedLogin));
        //12.Click 'Cart' button
        driver.findElement(By.xpath("//a[@href='/view_cart']/i")).click();
        //13. Click 'Proceed To Checkout' button
        driver.findElement(By.xpath("//a[.='Proceed To Checkout']")).click();
        //14. Verify Address Details and Review Your Order
        WebElement adress = driver.findElement(By.xpath("(//h3[@class='page-subheading'])[1]"));
        adress.isDisplayed();
        WebElement reviewOrder = driver.findElement(By.xpath("(//h2[@class='heading'])[2]"));
        reviewOrder.isDisplayed();
        //15. Enter description in comment text area and click 'Place Order'
        driver.findElement(By.xpath("//textarea")).sendKeys("Sample message");
        driver.findElement(By.xpath("//a[@href='/payment']")).click();
        //16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-qa='card-number']")).sendKeys("1111111111111111");
        driver.findElement(By.xpath("//input[@data-qa='cvc']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@data-qa='expiry-month']")).sendKeys("01");
        driver.findElement(By.xpath("//input[@data-qa='expiry-year']")).sendKeys("2025");
        //17. Click 'Pay and Confirm Order' button
        driver.findElement(By.xpath("//button[@data-qa='pay-button']")).click();
        //18. Verify success message 'Your order has been placed successfully!'
        //19. Click 'Download Invoice' button and verify invoice is downloaded successfully.
        driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();
        Thread.sleep(2000);
        String invoice = System.getProperty("user.home")+"/Downloads/invoice.txt";
        boolean isExist =  Files.exists(Path.of(invoice));
        Assert.assertTrue(isExist);
        //20. Click 'Continue' button
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        //21. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
        //22. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        driver.findElement(By.xpath("//b[.='Account Deleted!']")).isDisplayed();
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

    }
}
