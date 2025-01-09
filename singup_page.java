import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignupTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
       
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("URL_OF_SIGNUP_PAGE"); 
    }

    @Test
    public void testValidSignup() {
       
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys("abcd");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("abcd@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("abcd_123");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("abcd_123");

       
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
        Assert.assertTrue(successMessage.isDisplayed(), "Signup failed, success message not displayed.");
    }

    @Test
    public void testInvalidSignup_MissingName() {
       
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("abcd@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("abcd_123");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("abcd_123");

       
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

       
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for missing name not displayed.");
    }

    @Test
    public void testInvalidSignup_InvalidEmailFormat() {
      
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("abcd");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("invalid-email-format");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("abcd_123");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("abcd_123");

       
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for invalid email format not displayed.");
    }

    @Test
    public void testInvalidSignup_PasswordMismatch() {
       
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("abcd");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("abcd@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("abcd_123");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("DifferentPassword123!");

        // Submitting the form
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verifing error message for password mismatch
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for password mismatch not displayed.");
    }

    @Test
    public void testInvalidSignup_SpecialCharactersInName() {
        // Special characters in name
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("ab$cd");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("abcd@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("abcd_123");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("abcd_123");

        // Submitting the form
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verifying error message for special characters in name
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for special characters in name not displayed.");
    }

    @Test
    public void testInvalidSignup_LongInput() {
        // Long input for name, email, and password
        String longName = "a".repeat(200);
        String longEmail = "a".repeat(250) + "@gmail.com";
        String longPassword = "a".repeat(255);

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(longName);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(longEmail);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(longPassword);

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys(longPassword);

        // Submitting the form
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verifying error message for long input
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for long input not displayed.");
    }

    @AfterClass
    public void tearDown() {
        // Closing the browser
        driver.quit();
    }
}