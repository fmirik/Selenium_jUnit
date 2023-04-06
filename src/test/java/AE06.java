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

public class AE06 {
    //Test Case 6: Contact Us Form
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
    public void test01() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        Thread.sleep(2000);
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Contact Us' button
        driver.findElement(By.xpath("//a[@href='/contact_us']")).click();
        //5. Verify 'GET IN TOUCH' is visible
        String actualInfo = driver.findElement(By.xpath("(//h2[@class='title text-center'])[2]")).getText();
        String expectedInfo = "GET IN TOUCH";
        Assert.assertTrue(actualInfo.equalsIgnoreCase(expectedInfo));
        //6. Enter name, email, subject and message
        String name = "Abc";
        String email = "abc5@sample.com";
        String subject = "Sample";
        String message = "Sample messages";
        driver.findElement(By.xpath("//input[@data-qa='name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@data-qa='subject']")).sendKeys(subject);
        driver.findElement(By.xpath("//textarea[@data-qa='message']")).sendKeys(message);
        //7. Upload file
        driver.findElement(By.xpath("//input[@name='upload_file']")).sendKeys("/Users/fatih/Desktop/sample.txt");
        Thread.sleep(2000);
        //8. Click 'Submit' button
        driver.findElement(By.xpath("//input[@data-qa='submit-button']")).click();
        Thread.sleep(2000);
        //9. Click OK button
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        //10. Verify success message 'Success! Your details have been submitted successfully.' is visible
        String actualMessage = driver.findElement(By.xpath("//div[@class='status alert alert-success']")).getText();
        String expectedMessage = "Success! Your details have been submitted successfully.";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        //11. Click 'Home' button and verify that landed to home page successfully
        driver.findElement(By.xpath("(//a[contains(.,'Home')])[2]")).click();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://automationexercise.com";
        Assert.assertTrue(actualURL.contains(expectedURL));

    }

}
