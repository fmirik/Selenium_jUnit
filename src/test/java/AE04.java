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

public class AE04 {
    //https://automationexercise.com/test_cases Test Case 4: Logout User
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public  void tearDown(){
        driver.close();
    }

    @Test
    public void test01(){
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        //5. Verify 'Login to your account' is visible
        String actualInfo = driver.findElement(By.xpath("//h2[.='Login to your account']")).getText();
        String expectedInfo = "Login to your account";
        Assert.assertTrue(actualInfo.contains(expectedInfo));
        //6. Enter correct email address and password
        String email = "abc5@sample.com";
        String password = "Abc123456";
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        //7. Click 'login' button
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        //8. Verify that 'Logged in as username' is visible
        String actualLogin = driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText();
        String username = email.substring(0, email.indexOf("@"));
        String expectedLogin = "Logged in as " + username;
        //9. Click 'Logout' button
        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        //10. Verify that user is navigated to login page
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://automationexercise.com/login";
        Assert.assertTrue(actualURL.contains(expectedURL));

    }

}