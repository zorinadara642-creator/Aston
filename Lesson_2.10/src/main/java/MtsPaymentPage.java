import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MtsPaymentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieAgreeButton = By.id("cookie-agree");
    private final By blockTitle = By.xpath("//*[contains(text(), 'Онлайн пополнение')]");
    private final By paymentLogos = By.xpath(
            "//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]");
    private final By detailsLink = By.linkText("Подробнее о сервисе");

    private final By phoneInput = By.id("connection-phone");
    private final By amountInput = By.id("connection-sum");
    private final By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");

    private final By tabSelectorHeader = By.cssSelector(".select__header");
    private final By tabOption = By.cssSelector(".select__list li");

    private final By modalAmountText = By.cssSelector(".pay-description__cost");
    private final By modalButtonAmountText = By.xpath("//button[contains(., 'Оплатить')]");
    private final By modalPhoneText = By.cssSelector(".pay-description__text");
    private final By modalCardFields = By.cssSelector(".card-page__card input[autocomplete^='cc-']");
    private final By modalCardLabels = By.cssSelector(".card-page__card label");
    private final By modalPaymentIcons = By.cssSelector(".cards-brands img, .cards-brands svg");

    private final By paymentContainer = By.tagName("app-payment-container");

    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.mts.by/");
        acceptCookiesIfPresent();
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAgreeButton));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Баннер cookie не найден или уже закрыт");
        }
    }

    public String getBlockTitleText() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
        return title.getText();
    }

    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentLogos));
        return driver.findElements(paymentLogos);
    }

    public String clickDetailsLinkAndGetExpectedUrl() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
        String expectedUrl = link.getAttribute("href");
        link.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost"));
        return expectedUrl;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void selectPaymentTab(String tabName) {
        WebElement header = wait.until(ExpectedConditions.elementToBeClickable(tabSelectorHeader));
        header.click();

        List<WebElement> options = driver.findElements(tabOption);
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(tabName)) {
                option.click();
                return;
            }
        }
        throw new IllegalArgumentException("Вариант оплаты не найден: " + tabName);
    }

    public List<String> getCurrentTabPlaceHolder() {
        List<WebElement> inputs = driver.findElements(By.cssSelector("input[placeholder]"));
        return inputs.stream()
                .map(input -> input.getAttribute("placeholder"))
                .collect(Collectors.toList());
    }

    public void fillConnectionForm(String phone, String amount) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        phoneField.sendKeys(phone);
        phoneField.sendKeys(Keys.TAB);

        // диагностика: проверяем, что реально оказалось в поле после ввода и маски
        System.out.println("Реальное значение поля телефона: '" + phoneField.getAttribute("value") + "'");

        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput));
        amountField.sendKeys(amount);
        amountField.sendKeys(Keys.TAB);

        System.out.println("Реальное значение поля суммы: '" + amountField.getAttribute("value") + "'");
    }

    public void clickContinue() {
        List<WebElement> buttons = driver.findElements(continueButton);
        WebElement visibleButton = buttons.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Видимая кнопка 'Продолжить' не найдена"));

        System.out.println("Кнопка 'Продолжить' - enabled: " + visibleButton.isEnabled()
                + ", class: " + visibleButton.getAttribute("class"));

        wait.until(ExpectedConditions.elementToBeClickable(visibleButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", visibleButton);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        takeDebugScreenshot("after-js-click-continue");
    }

    private void takeDebugScreenshot(String name) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetDir = new File("target/debug-screenshots");
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            File destination = new File(targetDir, name + "-" + System.currentTimeMillis() + ".png");
            Files.copy(screenshot.toPath(), destination.toPath());
            System.out.println("Скриншот сохранён: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Не удалось сохранить скриншот: " + e.getMessage());
        }
    }

    public String getModalAmountText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentContainer));
        System.out.println("app-payment-container появился в DOM");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalAmountText));
        return element.getText();
    }

    public String getModalButtonAmountText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalButtonAmountText));
        return element.getText();
    }

    public String getModalPhoneText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalPhoneText));
        return element.getText();
    }

    public List<String> getModalCardFieldsPlaceHolder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(modalCardLabels));
        List<WebElement> labels = driver.findElements(modalCardLabels);
        return labels.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getModalCardInputs() {
        wait.until(ExpectedConditions.presenceOfElementLocated(modalCardFields));
        return driver.findElements(modalCardFields);
    }

    public List<WebElement> getModalPaymentIcons() {
        wait.until(ExpectedConditions.presenceOfElementLocated(modalPaymentIcons));
        return driver.findElements(modalPaymentIcons);
    }
}