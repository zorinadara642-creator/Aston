import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentBlockTest {

    private WebDriver driver;
    private MtsPaymentPage page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        page = new MtsPaymentPage(driver);
        page.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldDisplayCorrectBlockTitle() {
        assertTrue(page.getBlockTitleText().toLowerCase().contains("онлайн пополнение"));
    }

    @Test
    void shouldDisplayPaymentSystemLogos() {
        List<WebElement> logos = page.getPaymentLogos();

        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены");
        assertTrue(logos.size() >= 3, "Ожидалось минимум 3 логотипа (Visa, MasterCard, Белкарт)");
    }

    @Test
    void shouldOpenServiceDetailsPage() {
        String expectedUrl = page.clickDetailsLinkAndGetExpectedUrl();
        assertEquals(expectedUrl, page.getCurrentUrl());
    }

    @Test
    void shouldFillFieldsAndClickContinue() {
        page.fillConnectionForm("297777777", "100");
        page.clickContinue();
    }

    @Test
    void shouldDisplayCorrectPlaceholdersForEachTab() {
        List<String> connectionPlaceholders = page.getCurrentTabPlaceHolder();
        assertTrue(connectionPlaceholders.contains("Номер телефона"));
        assertTrue(connectionPlaceholders.contains("Сумма"));

        page.selectPaymentTab("Домашний интернет");
        List<String> internetPlaceholders = page.getCurrentTabPlaceHolder();
        assertFalse(internetPlaceholders.isEmpty());

        page.selectPaymentTab("Рассрочка");
        List<String> installmentPlaceholders = page.getCurrentTabPlaceHolder();
        assertFalse(installmentPlaceholders.isEmpty());

        page.selectPaymentTab("Задолженность");
        List<String> debtPlaceholders = page.getCurrentTabPlaceHolder();
        assertFalse(debtPlaceholders.isEmpty());
    }

    @Test
    void shouldShowCorrectDataModalAfterContinue() {
        String phone = "297777777";
        String amount = "100";

        page.fillConnectionForm(phone, amount);
        page.clickContinue();

        assertTrue(page.getModalAmountText().contains(amount));
        assertTrue(page.getModalButtonAmountText().contains(amount));
        assertTrue(page.getModalPhoneText().contains(phone));

        List<String> cardFieldPlaceholders = page.getModalCardFieldsPlaceHolder();
        assertFalse(cardFieldPlaceholders.isEmpty(), "Поля для ввода реквизитов карты не найдены");

        List<WebElement> paymentIcons = page.getModalPaymentIcons();
        assertFalse(paymentIcons.isEmpty(), "Иконки платежных систем в модальном окне не найдены");
    }
}