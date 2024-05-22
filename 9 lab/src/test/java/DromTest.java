import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DromTest {
    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver=new FirefoxDriver();
        WebDriver driver=new ChromeDriver();

        driver.get("https://drom.ru");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Найти поле поиска марки автомобиля и ввести "Toyota"
        WebElement Autobutton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver
                -> webDriver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/a[5]")));
//        WebElement Autobutton = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/a[5]"));
        Autobutton.click();


        WebElement toyotaButton=new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver->
                webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/div[4]/div[2]/div/a")));
//        WebElement toyotaButton=driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/div[4]/div[2]/div/a"));
        toyotaButton.click();

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("drom.ru/catalog/toyota/"));

        driver.quit();
    }
}