package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;


public class SaucedemoLogoutTest extends BaseTest{
    @Test
            public void logoutTest(){
       login("standard_user");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("logout_sidebar_link")
                ));
        driver.findElement(By.id("logout_sidebar_link")).click();
        String currenturl = driver.getCurrentUrl();
        Assert.assertEquals(
                currenturl,
                "https://www.saucedemo.com/inventory.html",
                "Logout Başarısız"
        );
                driver.quit();
    }

}
