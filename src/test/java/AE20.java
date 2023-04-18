import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AE20 {
    /*
    Test Case 20: Search Products and Verify Cart After Login
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Click on 'Products' button
    4. Verify user is navigated to ALL PRODUCTS page successfully
    5. Enter product name in search input and click search button
    6. Verify 'SEARCHED PRODUCTS' is visible
    7. Verify all the products related to search are visible
    8. Add those products to cart
    9. Click 'Cart' button and verify that products are visible in cart
    10. Click 'Signup / Login' button and submit login details
    11. Again, go to Cart page
    12. Verify that those products are visible in cart after login as well
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
    public void test20() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //4. Verify user is navigated to ALL PRODUCTS page successfully
        String allProducts = driver.findElement(By.xpath("//div[@class='features_items']/h2")).getText();
        Assert.assertTrue(allProducts.contains("ALL PRODUCTS"));
        //5. Enter product name in search input and click search button
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Dress");
        driver.findElement(By.xpath("//button[@id='submit_search']")).click();
        //6. Verify 'SEARCHED PRODUCTS' is visible
        String result = driver.findElement(By.xpath("//div[@class='features_items']/h2")).getText();
        Assert.assertTrue(result.contains("SEARCHED PRODUCTS"));
        //7. Verify all the products related to search are visible
        List<WebElement> search = driver.findElements(By.xpath("//div[@class='features_items']/div[@class='col-sm-4']"));
        for (WebElement w:search) {
            Assert.assertTrue(w.isDisplayed());
        }
        //8. Add those products to cart
        List<WebElement> addCart = driver.findElements(By.xpath("//div[@class='features_items']/div//div[@class='productinfo text-center']/a[.='Add to cart']"));

        for (int i = 0; i < addCart.size(); i++) {
            addCart.get(i).click();
            Thread.sleep(5);
            driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']")).click();
        }

        //9. Click 'Cart' button and verify that products are visible in cart
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        List<WebElement> cart = driver.findElements(By.xpath("//tbody/tr"));
        for(WebElement w: cart){
            Assert.assertTrue(w.isDisplayed());
        }
        //10. Click 'Signup / Login' button and submit login details
        WebElement singUp = driver.findElement(By.xpath("//a[@href='/login']"));
        singUp.click();
        String username = "abc5";
        String email = "abc5@sample.com";
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(username);
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[.='Signup']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Mr']")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Abc123456");
        WebElement day = driver.findElement(By.xpath("//select[@id='days']"));
        WebElement month = driver.findElement(By.xpath("//select[@id='months']"));
        WebElement year = driver.findElement(By.xpath("//select[@id='years']"));
        Select dayDropDown = new Select(day);
        Select monthDropDown = new Select(month);
        Select yearDropDown = new Select(year);
        dayDropDown.selectByValue("10");
        monthDropDown.selectByValue("3");
        yearDropDown.selectByValue("1990");
        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Ali");
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Veli");
        driver.findElement(By.xpath("//input[@id='company']")).sendKeys("No Company");
        driver.findElement(By.xpath("//p[4]/input[@class='form-control']")).sendKeys("Adres");
        driver.findElement(By.xpath("//p[5]/input[@class='form-control']")).sendKeys("Adres2");
        driver.findElement(By.xpath("//option[.='Canada']")).click();
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Toronto");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Clairlea");
        driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("12300");
        driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("1231112233");
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        //11. Again, go to Cart page
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        //12. Verify that those products are visible in cart after login as well
        List<WebElement> cartFinal = driver.findElements(By.xpath("//tbody/tr"));
        for(WebElement w: cartFinal){
            Assert.assertTrue(w.isDisplayed());
        }

    }
}
