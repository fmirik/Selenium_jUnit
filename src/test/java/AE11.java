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

import java.time.Duration;

public class AE11 {
    //Test Case 11: Verify Subscription in Cart page
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void test11(){
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click 'Cart' button
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        //5. Scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //6. Verify text 'SUBSCRIPTION'
        String pageBottom = driver.findElement(By.xpath("//h2[.='Subscription']")).getText();
        Assert.assertEquals("SUBSCRIPTION", pageBottom);
        //7. Enter email address in input and click arrow button
        driver.findElement(By.xpath("//input[@id='susbscribe_email']")).sendKeys("abc5@sample.com");
        driver.findElement(By.xpath("//button[@id='subscribe']")).click();
        //8. Verify success message 'You have been successfully subscribed!' is visible
        WebElement infoSubribed = driver.findElement(By.xpath("//div[@class='alert-success alert']"));
        infoSubribed.isDisplayed();

    }
}
