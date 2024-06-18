package Windowsandframes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IFrameTest {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testIFrame() {
        // Step 2: Navigate to the URL
        driver.get("https://the-internet.herokuapp.com/iframe");

        // Step 3: Switch to the iframe using css Selector or Xpath
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));

        // Step 4: Locate the "p" tag inside the iframe and write the text "Hello People"
        WebElement pTag = driver.findElement(By.id("tinymce"));
        pTag.clear();
        pTag.sendKeys("Hello People");

        // Step 5: Close the browser instance (Handled in tearDown method)
    }

    public static void main(String[] args) {
        // For standalone execution
        IFrameTest test = new IFrameTest();
        test.setUp("chrome");
        test.testIFrame();
        test.tearDown();
    }
}

