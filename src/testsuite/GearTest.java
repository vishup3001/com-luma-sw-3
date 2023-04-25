package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class GearTest extends Utility {
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
    public void userShouldAddProductSuccessFullyToShoppinCart() throws InterruptedException {
        // Mouse Hover on Gear Menu
        mouseHover(By.xpath("//span[normalize-space()='Gear']"));
        // Click on Bags
        mouseHoverAndClick(By.xpath("//span[normalize-space()='Bags']"));
        // Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));
        // Verify the text ‘Overnight Duffle’
        String expectedMessage = "Overnight Duffle";
        String actualMessage = getTextFromElement(By.xpath("//span[@class='base']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Change Qty 3
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");
        // Click on ‘Add to Cart’ Button.
        clickOnElement(By.xpath("//button[@id='product-addtocart-button']"));
        Thread.sleep(1000);
        // Verify the text ‘You added Overnight Duffle to your shopping cart.’
        expectedMessage = "You added Overnight Duffle to your shopping cart.";
        actualMessage = getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        // Verify the product name ‘Overnight Duffle’
        expectedMessage = "Overnight Duffle";
        actualMessage = getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        //Verify the Qty is ‘3’
        expectedMessage = "3";
        actualMessage = getAttributeValueFromElement(By.xpath("(//input[@class='input-text qty'])[1]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Verify the product price ‘$135.00’
        expectedMessage = "$135.00";
        actualMessage = getTextFromElement(By.xpath("(//span[@class='cart-price']//span)[2]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);
        // Change Qty to ‘5’
        sendTextToElement(By.xpath("(//input[@class='input-text qty'])[1]"), "5");
        // Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));
        Thread.sleep(1000);
        // Verify the product price ‘$225.00’
        expectedMessage = "$225.00";
        actualMessage = getTextFromElement(By.xpath("(//span[@class='cart-price']//span)[2]"));
        verifyTwoTextMessage(expectedMessage, actualMessage);


    }


}
