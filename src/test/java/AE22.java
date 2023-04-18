import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AE22 {
    /*
    Test Case 22: Add to cart from Recommended items
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Scroll to bottom of page
    4. Verify 'RECOMMENDED ITEMS' are visible
    5. Click on 'Add To Cart' on Recommended product
    6. Click on 'View Cart' button
    7. Verify that product is displayed in cart page
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
    public void test22() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Scroll to bottom of page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //4. Verify 'RECOMMENDED ITEMS' are visible
        WebElement verify = driver.findElement(By.xpath("//h2[.='recommended items']"));
        Assert.assertTrue(verify.isDisplayed());
        //5. Click on 'Add To Cart' on Recommended product
        driver.findElement(By.xpath("//div[@id='recommended-item-carousel']//div[@class='item active']/div[1]//a[.='Add to cart']")).click();
        //6. Click on 'View Cart' button
        driver.findElement(By.xpath("//u[.='View Cart']")).click();
        //7. Verify that product is displayed in cart page
        List<WebElement> cartFinal = driver.findElements(By.xpath("//tbody/tr"));
        for(WebElement w: cartFinal){
            Assert.assertTrue(w.isDisplayed());
        }
    }
}
