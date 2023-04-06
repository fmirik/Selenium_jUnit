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

public class AE02 {
    /*
    https://automationexercise.com/test_cases
    Test Case 2: Login User with correct email and password
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click on 'Signup / Login' button
    5. Verify 'Login to your account' is visible
    6. Enter correct email address and password
    7. Click 'login' button
    8. Verify that 'Logged in as username' is visible
    9. Click 'Delete Account' button
    10. Verify that 'ACCOUNT DELETED!' is visible
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
        //driver.close();
    }

    @Test
    public void test02() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        Thread.sleep(2000);
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Signup / Login' button
        WebElement singUp = driver.findElement(By.xpath("//a[@href='/login']"));
        singUp.click();
        //5. Verify 'Login to your account' is visible
        String actualLogin = driver.findElement(By.xpath("//h2[.='Login to your account']")).getText();
        String expectedLogin = "Login to your account";
        Assert.assertTrue(actualLogin.contains(expectedLogin));
        //6. Enter correct email address and password
        String email = "abc5@sample.com";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abc123456");
        //7. Click 'login' button
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        //8. Verify that 'Logged in as username' is visible
        String actualInfo = driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText();
        String username = email.substring(0, email.indexOf("@"));
        String expectedInfo = "Logged in as " + username;
        Assert.assertTrue(actualInfo.contains(expectedInfo));
        //9. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[contains(.,'Delete Account')]")).click();
        //10. Verify that 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//b[.='Account Deleted!']")).isDisplayed());
    }
}
