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

public class AE12 {
    //Test Case 12: Add Products in Cart
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
    public void test12() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(2000);
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
        Assert.assertEquals(cartOne, itemOne);
        Assert.assertEquals(cartTwo, itemTwo);
        //10. Verify their prices, quantity and total price
        WebElement cartItemOneTag = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
        cartItemOneTag.isDisplayed();
        WebElement cartItemOnePrice = driver.findElement(By.xpath("(//td[@class='cart_price'])[1]"));
        cartItemOnePrice.isDisplayed();
        WebElement cartItemOneQuantity = driver.findElement(By.xpath("(//td[@class='cart_quantity'])[1]"));
        cartItemOneQuantity.isDisplayed();
        WebElement cartItemOneTotal = driver.findElement(By.xpath("(//td[@class='cart_total'])[1]"));
        cartItemOneTotal.isDisplayed();
        WebElement cartItemTwoTag = driver.findElement(By.xpath("//a[@href='/product_details/2']"));
        cartItemTwoTag.isDisplayed();
        WebElement cartItemTwoPrice = driver.findElement(By.xpath("(//td[@class='cart_price'])[2]"));
        cartItemTwoPrice.isDisplayed();
        WebElement cartItemTwoQuantity = driver.findElement(By.xpath("(//td[@class='cart_quantity'])[2]"));
        cartItemTwoQuantity.isDisplayed();
        WebElement cartItemTwoTotal = driver.findElement(By.xpath("(//td[@class='cart_total'])[2]"));
        cartItemTwoTotal.isDisplayed();




    }

}
