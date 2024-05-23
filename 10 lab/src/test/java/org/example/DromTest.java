package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DromTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Open the drom.ru website
        driver.get("https://drom.ru");
    }

    @Test
    public void searchToyotaWithBlackColor() {
        // Create an instance of the HomePage
        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        // Perform a search for Toyota with a filter for the year range
        SearchResultsPage searchResultsPage = resultsPage.searchForToyotaWithBlackColor();

        // Verify that the search results page contains Toyota and the correct year range
        Assert.assertTrue(searchResultsPage.isColorSelected());
        System.out.println("Toyota and correct year range are displayed in the search results.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        driver.quit();
    }

    @Test
    public void testCatalogDrom() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        DromHomePage homePage = new DromHomePage(driver);
        homePage.clickAutoButton();
        homePage.selectToyota();
        Thread.sleep(2000);

        Assert.assertTrue(homePage.isOnToyotaCatalogPage());
        homePage.quit();
    }

    @Test
    public static void main(String[] args) throws InterruptedException {
    }
}

class SearchResultsPage {
    private WebDriver driver;
    private WebElement resultsContainer;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        // Initialize page elements
        resultsContainer = driver.findElement(By.xpath("/html/body/div[7]/div[5]/div/div[1]/div[2]/div[4]/div[2]"));
        resultsContainer.click();

    }

    public boolean isColorSelected() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("https://auto.drom.ru/toyota/all/?colorid[]=1");
    }

    public SearchResultsPage searchForToyotaWithBlackColor() {
        // Expand the filter
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[2]/form/div/div[4]/div[2]/button"))).click();

        // Click on the black color
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[2]/form/div/div[4]/div[1]/div[2]/div/div/button[3]"))).click();

        // Click on the apply button
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div[2]/form/div/div[5]/div[3]/button"))).click();

        return this;
    }
}