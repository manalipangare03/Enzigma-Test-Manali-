import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NokodrAutomation {
    public static void main(String[] args) {
        //Setting up WebDriver 
        WebDriverManager.chromedriver().setup();

        //Initializing WebDriver instance
        WebDriver driver = new ChromeDriver();
        
        //Opening the URL
        driver.get("https://app-staging.nokodr.com/");

      
        System.out.println("Page Title: " + driver.getTitle());


        //Closing the browser after the action is completed
        driver.quit();
    }
}