package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SaucedemoCheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        // --- NÜKLEER ÇÖZÜM: Chrome'u tamamen sessizleştiren ayarlar ---
        ChromeOptions options = new ChromeOptions();

        // 1. Gizli Sekme: Chrome'un kayıtlı şifreleri ve güvenlik uyarılarını tetiklemesini engeller
        options.addArguments("--incognito");

        // 2. Sızıntı Korumasını Kapat: Karşılaştığınız o spesifik "Şifrenizi değiştirin" uyarısını yok eder
        options.addArguments("--disable-features=PasswordLeakDetection");

        // 3. Şifre Kaydetme Balonunu Kapat
        options.addArguments("--disable-save-password-bubble");

        // Diğer standart şifre yöneticisi kapatma ayarları
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void completeCheckoutTest() {

        // 1. Siteye git ve Giriş yap
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 2. Ürünü sepete ekle ve Sepete git
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // 3. Checkout'a başla
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        // 4. Formu Doldur
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Bilge");
        driver.findElement(By.id("last-name")).sendKeys("Erol");
        driver.findElement(By.id("postal-code")).sendKeys("34000");

        // 5. Continue Butonuna JS ile KESİN Tıklama
        WebElement continueButton = driver.findElement(By.id("continue"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", continueButton);

        // 6. İkinci sayfaya geçişi onayla
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));

        // 7. Finish Butonuna JS ile KESİN Tıklama
        WebElement finishButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        js.executeScript("arguments[0].click();", finishButton);

        // 8. Başarılı olduğunu doğrula
        WebElement completeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(completeMessage.getText(), "Thank you for your order!", "HATA: Sipariş tamamlanamadı!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}