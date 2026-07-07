package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SaucedemoLoginTest extends BaseTest{

    @Test
    public void successfulLogin() {
        login("standard_user");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Login başarısız!"
        );
        driver.quit();
    }
}