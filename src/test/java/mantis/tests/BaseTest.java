package mantis.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public SoftAssertions softAssertions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        softAssertions = new SoftAssertions();

        driver.get("https://academ-it.ru/mantisbt/login_page.php");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        softAssertions.assertAll();
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}