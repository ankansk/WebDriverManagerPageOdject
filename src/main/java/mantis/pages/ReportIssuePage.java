package mantis.pages;

import mantis.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportIssuePage {

    private final WebDriver driver;

    private final WebDriverWait wait;

    @FindBy(css = "#summary")
    private WebElement summary;

    @FindBy(css = "#description")
    private WebElement description;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    public ReportIssuePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void scrollToSubmitButton() {
        TestUtils.scrollToElement(driver, submitButton);
    }

    public void submitButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public String timeStamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.now().format(dtf);
    }

    public String addIssueSummary() {
        String timestamp = timeStamp();
        String issueSummary = "Issue for test - " + timestamp;
        summary.sendKeys(issueSummary);
        return issueSummary;
    }

    public void addIssueDescription() {
        description.sendKeys("New issue added");
    }
}