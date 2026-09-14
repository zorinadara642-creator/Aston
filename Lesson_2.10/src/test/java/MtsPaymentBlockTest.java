import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentBlockTest {

    private WebDriver driver;
    private MtsPaymentPage page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        page = new MtsPaymentPage(driver);
        page.open();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(screenshot.toPath(),
                        Paths.get("target/" + testInfo.getDisplayName() + ".png"),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ignored) {}
            driver.quit();
        }
    }

    @Test
    void shouldDisplayCorrectBlockTitle() {
        assertTrue(page.getBlockTitleText().toLowerCase().contains("онлайн пополнение"));
    }

    @Test
    void shouldDisplaySystemLogos() {
        List<WebElement> logos = page.getPaymentLogos();

        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены");
        assertTrue(logos.size() >= 3, "Ожидалось минимум 3 логотипа (Visa, MasterCard, Белкарт");
    }

    @Test
    void shouldOpenServiceDetailsPage() {
        String expectedUrl = page.clickDetailsLinkAndGetExpectedUrl();
        assertEquals(expectedUrl, page.getCurrentUrl());
    }

    @Test
    void shouldShowCorrectDataInModalAfterContinue() {
        page.fillConnectionForm("297777777", "100");
        page.clickContinue();
    }

    @Test
    void shouldDisplayCorrectPlaceHoldersForeachTab() {
        List<String> connectionPlaceHolders = page.getCurrentTabPlaceHolder();
        assertTrue(connectionPlaceHolders.contains("Номер телефона"));
        assertTrue(connectionPlaceHolders.contains("Сумма"));

        page.selectPaymentTab("Домашний интернет");
        List<String> internetPlaceHolders = page.getCurrentTabPlaceHolder();
        assertTrue(internetPlaceHolders.contains("Номер абонента"));
        assertTrue(internetPlaceHolders.contains("Сумма"));
        assertTrue(internetPlaceHolders.contains("E-mail для отправки чека"));

        page.selectPaymentTab("Рассрочка");
        List<String> installmentPlaceHolders = page.getCurrentTabPlaceHolder();
        assertTrue(installmentPlaceHolders.contains("Номер счета на 44"));
        assertTrue(installmentPlaceHolders.contains("Сумма"));
        assertTrue(installmentPlaceHolders.contains("E-mail для отправки чека"));

        page.selectPaymentTab("Задолженность");
        List<String> debtPlaceHolders = page.getCurrentTabPlaceHolder();
        assertTrue(debtPlaceHolders.contains("Номер счета на 2073"));
        assertTrue(debtPlaceHolders.contains("Сумма"));
        assertTrue(debtPlaceHolders.contains("E-mail для отправки чека"));
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

        List<String> cardFieldPlaceHolders = page.getModalCardFieldsPlaceHolder();
        assertFalse(cardFieldPlaceHolders.isEmpty(), "Поля для ввода реквизитов карты не найдены");

        List<WebElement> paymentIcons = page.getModalPaymentIcons();
        assertFalse(paymentIcons.isEmpty(), "Иконки платежных систем в модальном окне не найдены");
    }
}
