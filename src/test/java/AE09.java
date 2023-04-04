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
import java.util.List;

public class AE09 {
    //Test Case 9: Search Product
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
    public void test09() {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        String allProducts = driver.findElement(By.xpath("//h2[.='All Products']")).getText();
        Assert.assertEquals("ALL PRODUCTS", allProducts);
        //6. Enter product name in search input and click search button
        String searchProduct = "T-Shirt";
        driver.findElement(By.xpath("//input[@id='search_product']")).sendKeys(searchProduct);
        driver.findElement(By.xpath("//button[@id='submit_search']")).click();
        //7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertEquals("SEARCHED PRODUCTS", driver.findElement(By.xpath("//h2[@class='title text-center']")).getText());
        //8. Verify all the products related to search are search
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        for (WebElement w : productList) {
            productList.get(productList.size() - 1).getText();
            Assert.assertTrue(w.getText().contains(searchProduct));
        }

    }

}
