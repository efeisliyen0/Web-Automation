package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SaucedemoAddToCartTest extends BaseTest {

    @Test
    public void verifyAddToCartFunctionality() {


        login("standard_user");


        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();


        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertTrue(removeButton.isDisplayed(), "Remove butonu görünür değil!");
        Assert.assertEquals(removeButton.getText(), "Remove", "Buton metni 'Remove' olarak güncellenmedi!");


        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadge.isDisplayed(), "Sepet üzerindeki sayı (badge) görünmüyor!");
        Assert.assertEquals(cartBadge.getText(), "1", "Sepetteki ürün sayısı doğru değil!");
    }
}