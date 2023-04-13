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

public class AE19 {
    /*
    Test Case 19: View & Cart Brand Products
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Click on 'Products' button
    4. Verify that Brands are visible on left side bar
    5. Click on any brand name
    6. Verify that user is navigated to brand page and brand products are displayed
    7. On left side bar, click on any other brand link
    8. Verify that user is navigated to that brand page and can see products
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
    public void test19() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //4. Verify that Brands are visible on left side bar
        WebElement brands = driver.findElement(By.xpath("//h2[.='Brands']"));
        Assert.assertTrue(brands.isDisplayed());
        //5. Click on any brand name
        driver.findElement(By.xpath("//a[@href='/brand_products/Polo']")).click();
        //6. Verify that user is navigated to brand page and brand products are displayed
        String brandPolo = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        Assert.assertTrue(brandPolo.contains("POLO"));
        //7. On left side bar, click on any other brand link
        driver.findElement(By.xpath("//a[@href='/brand_products/H&M']")).click();
        //8. Verify that user is navigated to that brand page and can see products
        String brandHM = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        Assert.assertTrue(brandHM.contains("H&M"));
    }
}
