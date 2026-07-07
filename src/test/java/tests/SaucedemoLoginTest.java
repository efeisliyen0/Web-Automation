package tests;

import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginMethod;

public class SaucedemoLoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        LoginMethod loginMethods = new LoginMethod(driver);
        loginMethods.login("standard_user");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Login başarısız!"
        );
        driver.quit();
    }
}