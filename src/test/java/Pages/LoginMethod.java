package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginMethod {

    private WebDriver driver;

    public LoginMethod(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}
