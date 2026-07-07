package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class SaucedemoProductsTest extends BaseTest{

    // Uyarı: Thread.sleep kullandığımız için metodun yanına 'throws InterruptedException' ekledik
    @Test
    public void sortProductsByPriceLowToHighTest() throws InterruptedException {

        login("standard_user");

        // Login olduktan sonra 2 saniye bekle (Gözlemlemek için)
        Thread.sleep(2000);

        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");

        // Sıralama yaptıktan sonra 2 saniye bekle (Gözlemlemek için)
        Thread.sleep(2000);

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1), "HATA: Fiyatlar doğru sıralanmamış!");
        }

        driver.quit();
    }
}