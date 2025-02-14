package mantis.pages;

import mantis.utils.TestUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ViewIssuesPage {

    private final WebDriver driver;

    private final WebDriverWait wait;

    @FindBy(css = "a[href='/mantisbt/bug_report_page.php']")
    private WebElement reportIssuePage;

    @FindBy(css = "#buglist tbody tr")
    private List<WebElement> issues;

    @FindBy(id = "bug_arr_all")
    private WebElement tableFooter;

    @FindBy(xpath = "//*[@id='buglist']//tbody/tr/td[11]")
    private WebElement firstIssueSummary;

    @FindBy(xpath = "//*[@id='buglist']//tbody//span[1]")
    private WebElement firstIssueCheck;

    @FindBy(xpath = "//*[@name='action' and @class='input-sm']")
    private WebElement actionList;

    @FindBy(xpath = "//*[contains(@class, 'btn btn-primary btn-white') and @value='OK']")
    private WebElement OkButton;

    @FindBy(xpath = "//*[@class='table-responsive']//td[2]")
    private WebElement selectedIssue;

    @FindBy(xpath = "//input[@value='Delete Issues']")
    private WebElement deleteIssueButton;

    public ViewIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30, 500));

        PageFactory.initElements(driver, this);
    }

    public int countIssues() {
        return issues.size();
    }

    public void scrollToTableFooter() {
        TestUtils.scrollToElement(driver, tableFooter);
    }

    public WebElement getFirstIssueSummary() {
        return firstIssueSummary;
    }

    public void checkFirstIssue() {
        firstIssueCheck.click();
    }

    public void viewActionList() {
        actionList.click();
    }

    public void checkDelete() {
        driver.switchTo().activeElement().sendKeys("delete");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void buttonOkClick() {
        OkButton.click();
    }

    public WebElement getSelectedIssue() {
        return selectedIssue;
    }

    public void deleteIssueButtonClick() {
        deleteIssueButton.click();
    }
}