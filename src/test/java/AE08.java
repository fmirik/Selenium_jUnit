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

public class AE08 {
    /*
    https://automationexercise.com/test_cases
    Test Case 8: Verify All Products and product detail page
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click on 'Products' button
    5. Verify user is navigated to ALL PRODUCTS page successfully
    6. The products list is visible
    7. Click on 'View Product' of first product
    8. User is landed to product detail page
    9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
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
    public void test08() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(2000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        String allProducts = driver.findElement(By.xpath("//h2[.='All Products']")).getText();
        Assert.assertEquals("ALL PRODUCTS", allProducts);
        //6. The products list is visible
        //7. Click on 'View Product' of first product
        driver.findElement(By.xpath("//a[@href='/product_details/1']")).click();
        //8. User is landed to product detail page
        //9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
        Assert.assertEquals("Blue Top", driver.findElement(By.xpath("//h2[.='Blue Top']")).getText());
        Assert.assertEquals("Category: Women > Tops", driver.findElement(By.xpath("//p[.='Category: Women > Tops']")).getText());
        Assert.assertEquals("Rs. 500", driver.findElement(By.xpath("//span[.='Rs. 500']")).getText());
        Assert.assertEquals("Availability:", driver.findElement(By.xpath("//b[.='Availability:']")).getText());
        Assert.assertEquals("Condition:", driver.findElement(By.xpath("//b[.='Condition:']")).getText());
        Assert.assertEquals("Brand:1", driver.findElement(By.xpath("//b[.='Brand:']")).getText());
    }
}
