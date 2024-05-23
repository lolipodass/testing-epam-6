package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DromHomePage {
    private WebDriver driver;

    public DromHomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://drom.ru/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public void clickAutoButton() {
        WebElement autoButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> webDriver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/a[5]")));
        autoButton.click();
    }

    public void selectToyota() {
        WebElement toyotaButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/div[4]/div[2]/div/a")));
        toyotaButton.click();
    }

    public boolean isOnToyotaCatalogPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("drom.ru/catalog/toyota/");
    }

    public void quit() {
        driver.quit();
    }
}

