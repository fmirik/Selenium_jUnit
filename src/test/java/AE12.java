import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class AE12 {
    //Test Case 12: Add Products in Cart
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
        //driver.close();
    }

    @Test
    public void test12(){
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //5. Hover over first product and click 'Add to cart'
        driver.findElement(By.xpath("//div[@class='features_items']/div[2]//div[@class='productinfo text-center']/a[.='Add to cart']")).click();
        String itemOne = driver.findElement(By.xpath("(//div[@class='single-products']//p)[1]")).getText();
        //6. Click 'Continue Shopping' button
        driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']")).click();
        //7. Hover over second product and click 'Add to cart'
        driver.findElement(By.xpath("//div[@class='features_items']/div[3]//div[@class='productinfo text-center']/a[.='Add to cart']")).click();
        String itemTwo = driver.findElement(By.xpath("(//div[@class='single-products']//p)[3]")).getText();
        //8. Click 'View Cart' button
        driver.findElement(By.xpath("//u[.='View Cart']")).click();
        //9. Verify both products are added to Cart
        String cartOne = driver.findElement(By.xpath("//a[@href='/product_details/1']")).getText();
        String cartTwo = driver.findElement(By.xpath("//a[@href='/product_details/2']")).getText();
        Assert.assertEquals(cartOne,itemOne);
        Assert.assertEquals(cartTwo,itemTwo);
        //10. Verify their prices, quantity and total price


    }

}
