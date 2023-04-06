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

public class AE13 {
    /*
    https://automationexercise.com/test_cases
    Test Case 13: Verify Product quantity in Cart
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click 'View Product' for any product on home page
    5. Verify product detail is opened
    6. Increase quantity to 4
    7. Click 'Add to cart' button
    8. Click 'View Cart' button
    9. Verify that product is displayed in cart page with exact quantity
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
    public void test13() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click 'View Product' for any product on home page
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //5. Verify product detail is opened
        driver.findElement(By.xpath("//a[@href='/product_details/1']")).click();
        //6. Increase quantity to 4
        driver.findElement(By.xpath("//input[@id='quantity']")).clear();
        String quantity = "4";
        driver.findElement(By.xpath("//input[@id='quantity']")).sendKeys(quantity);
        //7. Click 'Add to cart' button
        driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
        //8. Click 'View Cart' button
        driver.findElement(By.xpath("//u[.='View Cart']")).click();
        //9. Verify that product is displayed in cart page with exact quantity
        WebElement cartItemQuantity = driver.findElement(By.xpath("(//td[@class='cart_quantity'])[1]"));
        Assert.assertEquals(quantity, cartItemQuantity.getText());
    }
}
