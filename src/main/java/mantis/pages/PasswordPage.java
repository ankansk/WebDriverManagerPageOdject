package mantis.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = ".alert-danger p")
    private WebElement errorMessage;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30, 500));

        PageFactory.initElements(driver, this);
    }

    public void login(String password) {
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
