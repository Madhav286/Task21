package Windowsandframes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nestedframes {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Initialize a new instance of the Chrome browser
        WebDriver driver = new ChromeDriver();
        
        try {
            // Navigate to the URL
            driver.get("http://the-internet.herokuapp.com/nested_frames");

            // Switch to the top frame
            driver.switchTo().frame("frame-top");

            // Verify that there are three frames on the page
            WebElement leftFrame = driver.findElement(By.name("frame-left"));
            WebElement middleFrame = driver.findElement(By.name("frame-middle"));
            WebElement rightFrame = driver.findElement(By.name("frame-right"));
            if (leftFrame != null && middleFrame != null && rightFrame != null) {
                System.out.println("Three frames are present in the top frame.");
            }

            // Switch to the left frame
            driver.switchTo().frame(leftFrame);
            // Verify the left frame has text "LEFT"
            WebElement leftBody = driver.findElement(By.tagName("body"));
            if (leftBody.getText().equals("LEFT")) {
                System.out.println("Left frame has text 'LEFT'.");
            }

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Switch to the middle frame
            driver.switchTo().frame(middleFrame);
            // Verify the middle frame has text "MIDDLE"
            WebElement middleBody = driver.findElement(By.tagName("body"));
            if (middleBody.getText().equals("MIDDLE")) {
                System.out.println("Middle frame has text 'MIDDLE'.");
            }

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Switch to the right frame
            driver.switchTo().frame(rightFrame);
            // Verify the right frame has text "RIGHT"
            WebElement rightBody = driver.findElement(By.tagName("body"));
            if (rightBody.getText().equals("RIGHT")) {
                System.out.println("Right frame has text 'RIGHT'.");
            }

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Switch to the bottom frame
            driver.switchTo().defaultContent();
            driver.switchTo().frame("frame-bottom");
            // Verify the bottom frame has text "BOTTOM"
            WebElement bottomBody = driver.findElement(By.tagName("body"));
            if (bottomBody.getText().equals("BOTTOM")) {
                System.out.println("Bottom frame has text 'BOTTOM'.");
            }

            // Switch back to the top frame
            driver.switchTo().defaultContent();

            // Verify that the page title is "Frames"
            if (driver.getTitle().equals("Frames")) {
                System.out.println("Page title is 'Frames'.");
            }
        } finally {
            // Close the browser instance
            driver.quit();
        }
    }
}


