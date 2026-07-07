package tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginMethod;

public class SaucedemoViewProductDetailsTest extends BaseTest {

    @Test
        public void viewdetails() {
        LoginMethod loginMethods = new LoginMethod(driver);
        loginMethods.login("standard_user");
            driver.findElement(By.className("inventory_item_name")).click();
            String url = driver.getCurrentUrl();
            Assert.assertTrue(
                    url.contains("item.html?id="),
                    "Görüntüleme Başarısız"
            );

    }

}
