import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class HomePageTest {
    public WebDriver driver;
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver" , "C:\\Users\\BHAVANA\\Downloads\\chromedriver-win32 (1)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        // Enter the text 142420 in the "User ID" field.
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        // Enter the text 231225 in the "PIN" field.
        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("231225");
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
    }
    @Test
    public void testHomepageHeading(){
        //Wait and verify the navigation to the home page
        String expectedUrl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
        //Verify the Heading text of the home page
        String expectedHeading = "Your Flexibility, Our Excellence";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading")));
        WebElement actualTextEl = driver.findElement(By.className("heading"));
        String actualText = actualTextEl.getText();
        Assert.assertEquals(actualText, expectedHeading, "Heading text does not match");
    }
    @Test(priority = 1)
    public void testLogoutFunctionality() {
        //Wait and verify the navigation to the home page
        String expectedUrl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
        //Click the "Logout" button.
        WebElement logoutBtnEl = driver.findElement(By.className("logout-button"));
        logoutBtnEl.click();
        // Verify the navigation to the login page
        String expectedUrl1 = "https://qaebank.ccbp.tech/ebank/login/";
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        waits.until(ExpectedConditions.urlToBe(expectedUrl1));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl1, "Login URL do not match");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
