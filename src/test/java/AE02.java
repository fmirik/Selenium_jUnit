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

public class AE02 {
    //https://automationexercise.com/test_cases Test Case 2

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test02(){
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//i[@class='fa fa-home']"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Signup / Login' button
        WebElement singUp = driver.findElement(By.xpath("//a[@href='/login']"));
        singUp.click();
        //5. Verify 'Login to your account' is visible
        String actualLogin = driver.findElement(By.xpath("//h2[.='Login to your account']")).getText();
        String expectedLogin = "Login to your account";
        Assert.assertTrue(actualLogin.contains(expectedLogin));
        //6. Enter correct email address and password
        String email = "abc@deneme.com";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
        //7. Click 'login' button
        driver.findElement(By.xpath("//button[@data-qa='login-button']"));
        //8. Verify that 'Logged in as username' is visible
        driver.findElement(By.xpath(""))
        //9. Click 'Delete Account' button
        //10. Verify that 'ACCOUNT DELETED!' is visible
    }
}
