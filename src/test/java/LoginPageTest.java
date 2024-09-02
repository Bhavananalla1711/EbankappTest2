import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
public class LoginPageTest {
    public WebDriver driver;
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver" , "C:\\Users\\BHAVANA\\Downloads\\chromedriver-win32 (1)\\chromedriver-win32");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }
    @Test(priority = 1)
    public void testLoginWithEmptyInputs(){
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
        //Wait and verify the error message
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String expectedMsg = "Invalid user ID";
        WebElement actualMsg = driver.findElement(By.className("error-message-text"));
        String actualMsgText = actualMsg.getText();
        Assert.assertEquals(actualMsgText, expectedMsg, "Error text with empty input fields does not match");
    }
    @Test(priority = 2)
    public void testLoginWithEmptyUserId(){
        // Enter the text 231225 in the "PIN" field.
        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("231225");
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
        //Wait and verify the error message
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String expectedMsg = "Invalid user ID";
        WebElement actualMsg = driver.findElement(By.className("error-message-text"));
        String actualMsgText = actualMsg.getText();
        Assert.assertEquals(actualMsgText, expectedMsg, "Error text with empty input field do not match");
    }
    @Test(priority = 3)
    public void estLoginWithEmptyPin(){
        // Enter the text 142420 in the "User ID" field.
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
        //Wait and verify the error message
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String expectedMsg = "Invalid PIN";
        WebElement actualMsg = driver.findElement(By.className("error-message-text"));
        String actualMsgText = actualMsg.getText();
        Assert.assertEquals(actualMsgText, expectedMsg, "Error text with empty input field do not match");
    }
    @Test(priority = 4)
    public void testLoginWithInvalidCreds(){
        // Enter the text 142420 in the "User ID" field.
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        // Enter the text 123456 in the "PIN" field.
        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("123456");
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
        //Wait and verify the error message
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
        String expectedMsg = "User ID and PIN didn't match";
        WebElement actualMsg = driver.findElement(By.className("error-message-text"));
        String actualMsgText = actualMsg.getText();
        Assert.assertEquals(actualMsgText, expectedMsg, "Error text with invalid pin do not match");
    }
    @Test(priority = 5)
    public void testLoginWithValidCreds(){
        // Enter the text 142420 in the "User ID" field.
        WebElement userId = driver.findElement(By.id("userIdInput"));
        userId.sendKeys("142420");
        // Enter the text 231225 in the "PIN" field.
        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("231225");
        //Click the "Login" button.
        WebElement loginBtnEl = driver.findElement(By.className("login-button"));
        loginBtnEl.click();
        //Wait and verify the navigation to the home page
        String expectedUrl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
