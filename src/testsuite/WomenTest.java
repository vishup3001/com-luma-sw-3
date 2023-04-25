package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        // Open browser and launch Url
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        // Close all open tabs
        closeBrowser();
    }

    @Test
    public void verifyTheSortByProductNameFilter() throws InterruptedException {
        // Mouse Hover on Women Menu
        mouseHover(By.xpath("//span[normalize-space()='Women']"));
        // Mouse Hover on Tops
        mouseHover(By.xpath("//a[@id='ui-id-9']//span[contains(text(),'Tops')]"));
        // Click on Jackets
        mouseHoverAndClick(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        Thread.sleep(1000);
        // Storing jackets names in list for shorting
        List<WebElement> beforeShortValue = driver.findElements(By.xpath("//strong[@class='product name product-item-name']//a"));
        List<String> beforeShortJacketValue = new ArrayList<>();
        for (WebElement value : beforeShortValue) {
            beforeShortJacketValue.add(value.getText());
        }
        // Select Sort By filter “Product Name”
        selectByVisibleTextFromDropDown(By.xpath("(//select[@id='sorter'])[1]"), "Product Name");

        Thread.sleep(1000);

        // After shorting value
        List<WebElement> afterShortValue = driver.findElements(By.xpath("//strong[@class='product name product-item-name']//a"));
        List<String> afterShortJackettopValue = new ArrayList<>();
        Thread.sleep(1000);
        for (WebElement value1 : afterShortValue) {
            afterShortJackettopValue.add(value1.getText());
        }
        // Short value for comparison
        Collections.sort(beforeShortJacketValue, String.CASE_INSENSITIVE_ORDER);// Ascending order


        // Verify the products name display in alphabetical order
        Assert.assertEquals(beforeShortJacketValue, afterShortJackettopValue);


    }

    @Test
    public void verifyTheSortByPriceFilter() throws InterruptedException {
        // Mouse Hover on Women Menu
        mouseHover(By.xpath("//span[normalize-space()='Women']"));
        // Mouse Hover on Tops
        mouseHover(By.xpath("//a[@id='ui-id-9']//span[contains(text(),'Tops')]"));
        //Click on Jackets
        mouseHoverAndClick(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        Thread.sleep(1000);
        // Storing jackets names in list for shorting
        List<WebElement> beforeShortValue = driver.findElements(By.xpath("//span[@class='price-wrapper ']//span"));
        List<Double> beforeShortJacketValue = new ArrayList<>();
        for (WebElement value : beforeShortValue) {
            beforeShortJacketValue.add(Double.valueOf(value.getText().replace("$", "")));
        }
        // Select Sort By filter “Price”
        selectByVisibleTextFromDropDown(By.xpath("(//select[@id='sorter'])[1]"), "Price");

        Thread.sleep(1000);
        // After shorting value
        List<WebElement> afterShortValue = driver.findElements(By.xpath("//span[@class='price-wrapper ']//span"));
        List<Double> afterShortJackettopValue = new ArrayList<>();
        Thread.sleep(1000);
        for (WebElement value1 : afterShortValue) {
            afterShortJackettopValue.add(Double.valueOf(value1.getText().replace("$", "")));
        }

        Collections.sort(beforeShortJacketValue);// Ascending order


        // Verify the products price display in Low to High
        Assert.assertEquals(beforeShortJacketValue, afterShortJackettopValue);
    }

}

