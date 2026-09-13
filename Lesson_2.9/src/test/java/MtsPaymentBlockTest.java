import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentBlockTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.mts.by/");

        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("cookie-agree")));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Баннер cookie не найден или уже закрыт");
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldDisplayCorrectBlockTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Онлайн пополнение')]")));

        assertTrue(title.getText().toLowerCase().contains("онлайн пополнение"));
    }

    @Test
    void shouldDisplayPaymentSystemLogos() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[contains(@src, 'visa')]")));

        List<WebElement> logos = driver.findElements(By.xpath(
                "//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]"));

        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены");
        assertTrue(logos.size() >= 3, "Ожидалось минимум 3 логотипа (Visa, MasterCard, Белкарт");
    }

    @Test
    void shouldOpenServiceDetailsPage() {
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Подробнее о сервисе")));

        String expectedUrl = detailsLink.getAttribute("href");
        detailsLink.click();

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost"));

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void shouldFillFieldsAndClickContinue() {
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        phoneInput.sendKeys("297777777");

        WebElement amountInput = driver.findElement(By.id("connection-sum"));
        amountInput.sendKeys("100");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")));
        continueButton.click();
    }
}
