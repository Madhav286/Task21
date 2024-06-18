package Windowsandframes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

public class NewWindowTest {
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
    public void testNewWindow() {
        // Step 2: Navigate to the URL
        driver.get("https://the-internet.herokuapp.com/windows");

        // Step 3: Click the "Click Here" button to open a new window
        WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
        clickHereButton.click();

        // Step 4: Switch to the newly opened window
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Step 5: Verify that the text "New Window" is present on the page
        WebElement newWindowText = driver.findElement(By.xpath("//h3[text()='New Window']"));
        Assert.assertTrue(newWindowText.isDisplayed(), "New Window text is not displayed!");

        // Step 6: Close the new window
        driver.close();

        // Step 7: Verify that the original window is active
        driver.switchTo().window(originalWindow);

        // Step 8: Close the browser instance (Handled in tearDown method)
    }

    public static void main(String[] args) {
        // For standalone execution
        NewWindowTest test = new NewWindowTest();
        test.setUp("chrome");
        test.testNewWindow();
        test.tearDown();
    }
}


