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

public class AE17 {
    /*
    Test Case 17: Remove Products From Cart
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Add products to cart
    5. Click 'Cart' button
    6. Verify that cart page is displayed
    7. Click 'X' button corresponding to particular product
    8. Verify that product is removed from the cart
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
    public void test17() throws InterruptedException {
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
        //7. Click 'X' button corresponding to particular product
        driver.findElement(By.xpath("//i[@class='fa fa-times']")).click();
        //8. Verify that product is removed from the cart
        Thread.sleep(2000);
        WebElement emptyCart = driver.findElement(By.xpath("//span[@id='empty_cart']//p"));
        Assert.assertTrue(emptyCart.isDisplayed());
    }
}
