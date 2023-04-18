import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

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
        //driver.close();
    }

    @Test
    public void test23() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        //4. Click 'Signup / Login' button
        //5. Fill all details in Signup and create account
        //6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        //7. Verify ' Logged in as username' at top
        //8. Add products to cart
        //9. Click 'Cart' button
        //10. Verify that cart page is displayed
        //11. Click Proceed To Checkout
        //12. Verify that the delivery address is same address filled at the time registration of account
        //13. Verify that the billing address is same address filled at the time registration of account
        //14. Click 'Delete Account' button
        //15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
    }
}
