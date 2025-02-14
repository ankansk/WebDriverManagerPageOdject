package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "span.user-info")
    private WebElement userName;

    @FindBy(css = "a[href='/mantisbt/view_all_bug_page.php']")
    private WebElement viewIssuesPageButton;

    @FindBy(css = "a[href='/mantisbt/bug_report_page.php']")
    private WebElement reportIssuePageButton;

    @FindBy(xpath = "//*[@id='assigned']")
    private WebElement assignedToMeBlock;

    @FindBy(xpath = "//*[@id='unassigned']")
    private WebElement unassignedBlock;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30, 500));
        PageFactory.initElements(driver, this);
    }

    public String getUserName() {
        return userName.getText();
    }

    public void goToViewIssuesPage() {
        viewIssuesPageButton.click();
    }

    public void goToReportIssuePage() {
        reportIssuePageButton.click();
    }

    public boolean isAssignedToMeBlockDisplayed() {
        System.out.println("isAssignedToMeBlockDisplayed");
        return assignedToMeBlock.isDisplayed();
    }

    public boolean isUnassignedToMeBlockDisplayed() {
        System.out.println("isUnassignedToMeBlockDisplayed");
        return unassignedBlock.isDisplayed();
    }
}
