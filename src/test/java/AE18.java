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

public class AE18 {
    /*
    Test Case 18: View Category Products
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that categories are visible on left side bar
    4. Click on 'Women' category
    5. Click on any category link under 'Women' category, for example: Dress
    6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
    7. On left side bar, click on any sub-category link of 'Men' category
    8. Verify that user is navigated to that category page
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
    public void test18() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that categories are visible on left side bar
        WebElement categories = driver.findElement(By.xpath("//h2[.='Category']"));
        Assert.assertTrue(categories.isDisplayed());
        //4. Click on 'Women' category
        driver.findElement(By.xpath("//a[contains(.,'Women')]")).click();
        //5. Click on any category link under 'Women' category, for example: Dress
        Thread.sleep(200);
        driver.findElement(By.xpath("//a[@href='/category_products/1']")).click();
        //6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
        WebElement womenDress = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(womenDress.isDisplayed());
        //7. On left side bar, click on any sub-category link of 'Men' category
        driver.findElement(By.xpath("//a[contains(.,'Men')]")).click();
        //8. Verify that user is navigated to that category page


    }
}
