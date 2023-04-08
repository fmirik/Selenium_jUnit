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

import java.io.File;
import java.time.Duration;

public class AE16 {
    /*
    https://automationexercise.com/test_cases
    Test Case 16: Place Order: Login before Checkout
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click 'Signup / Login' button
    5. Fill email, password and click 'Login' button
    6. Verify 'Logged in as username' at top
    7. Add products to cart
    8. Click 'Cart' button
    9. Verify that cart page is displayed
    10. Click Proceed To Checkout
    11. Verify Address Details and Review Your Order
    12. Enter description in comment text area and click 'Place Order'
    13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
    14. Click 'Pay and Confirm Order' button
    15. Verify success message 'Your order has been placed successfully!'
    16. Click 'Delete Account' button
    17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
     */

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addExtensions(new File("./extension.crx"));//uBlock Origin Extension
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        //driver.close();
    }

    @Test
    public void test16(){

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
        //5. Fill email, password and click 'Login' button
        //6. Verify 'Logged in as username' at top
        //7. Add products to cart
        //8. Click 'Cart' button
        //9. Verify that cart page is displayed
        //10. Click Proceed To Checkout
        //11. Verify Address Details and Review Your Order
        //12. Enter description in comment text area and click 'Place Order'
        //13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        //14. Click 'Pay and Confirm Order' button
        //15. Verify success message 'Your order has been placed successfully!'
        //16. Click 'Delete Account' button
        //17. Verify 'ACCOUNT DELETED!' and click 'Continue' button

    }
}
