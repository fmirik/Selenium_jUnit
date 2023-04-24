import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.Duration;

public class AE25 {
     /*
    Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Scroll down page to bottom
    5. Verify 'SUBSCRIPTION' is visible
    6. Click on arrow at bottom right side to move upward
    7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
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
    public void test25() throws InterruptedException {
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        Thread.sleep(3000);
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        //4. Scroll down page to bottom
        //5. Verify 'SUBSCRIPTION' is visible
        //6. Click on arrow at bottom right side to move upward
        //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
    }
}
