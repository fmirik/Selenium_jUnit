import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.Duration;

public class AE20 {
    /*
    Test Case 20: Search Products and Verify Cart After Login
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Click on 'Products' button
    4. Verify user is navigated to ALL PRODUCTS page successfully
    5. Enter product name in search input and click search button
    6. Verify 'SEARCHED PRODUCTS' is visible
    7. Verify all the products related to search are visible
    8. Add those products to cart
    9. Click 'Cart' button and verify that products are visible in cart
    10. Click 'Signup / Login' button and submit login details
    11. Again, go to Cart page
    12. Verify that those products are visible in cart after login as well
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
    public void test20() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //4. Verify user is navigated to ALL PRODUCTS page successfully
        //5. Enter product name in search input and click search button
        //6. Verify 'SEARCHED PRODUCTS' is visible
        //7. Verify all the products related to search are visible
        //8. Add those products to cart
        //9. Click 'Cart' button and verify that products are visible in cart
        //10. Click 'Signup / Login' button and submit login details
        //11. Again, go to Cart page
        //12. Verify that those products are visible in cart after login as well

    }
}
