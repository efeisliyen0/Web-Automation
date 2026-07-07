package tests;

import java.time.Duration;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import Pages.LoginMethod;

public class SaucedemoRemoveProductTest extends BaseTest {
    @Test
        public void succesfulRemove() {
        LoginMethod loginMethods = new LoginMethod(driver);
        loginMethods.login("standard_user");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        int before = driver.findElements(By.className("cart_item")).size();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        int after = driver.findElements(By.className("cart_item")).size();
        Assert.assertEquals(after,before -1);
    }
}