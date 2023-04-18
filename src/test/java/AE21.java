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

public class AE21 {
    /*
    Test Case 21: Add review on product
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Click on 'Products' button
    4. Verify user is navigated to ALL PRODUCTS page successfully
    5. Click on 'View Product' button
    6. Verify 'Write Your Review' is visible
    7. Enter name, email and review
    8. Click 'Submit' button
    9. Verify success message 'Thank you for your review.'
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
    public void test21() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //4. Verify user is navigated to ALL PRODUCTS page successfully
        String allProducts = driver.findElement(By.xpath("//div[@class='features_items']/h2")).getText();
        Assert.assertTrue(allProducts.contains("ALL PRODUCTS"));
        //5. Click on 'View Product' button
        driver.findElement(By.xpath("//a[@href='/product_details/1']")).click();
        //6. Verify 'Write Your Review' is visible
        String reviewBar = driver.findElement(By.xpath("//a[.='Write Your Review']")).getText();
        System.out.println(reviewBar);
        Assert.assertTrue(reviewBar.equalsIgnoreCase("Write Your Review"));
        //7. Enter name, email and review
        String name = "Abc5";
        String email = "abc5@sample.com";
        String review = "Sample message";
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//textarea[@id='review']")).sendKeys(review);
        //8. Click 'Submit' button
        driver.findElement(By.xpath("//button[@id='button-review']")).click();
        //9. Verify success message 'Thank you for your review.'
        WebElement message = driver.findElement(By.xpath("//span[.='Thank you for your review.']"));
        Assert.assertTrue(message.isDisplayed());

    }

}
