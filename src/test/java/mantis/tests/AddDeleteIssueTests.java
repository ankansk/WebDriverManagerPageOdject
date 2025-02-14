package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddDeleteIssueTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void checkAddedIssue() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToReportIssuePage();

        String expectedIssueText = mantisSite.getReportIssuePage().addIssueSummary();

        mantisSite.getReportIssuePage().addIssueDescription();
        mantisSite.getReportIssuePage().scrollToSubmitButton();
        mantisSite.getReportIssuePage().submitButtonClick();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mantisSite.getViewIssuesPage().getFirstIssueSummary()));

        SoftAssertions softAssert = new SoftAssertions();

        String actualIssueText = mantisSite.getViewIssuesPage().getFirstIssueSummary().getText();
        String actualTimeStamp = mantisSite.getReportIssuePage().timeStamp();

        softAssert.assertThat(actualIssueText).contains(expectedIssueText);
        softAssert.assertThat(actualIssueText).contains(actualTimeStamp);

        mantisSite.getViewIssuesPage().checkFirstIssue();
        mantisSite.getViewIssuesPage().scrollToTableFooter();
        mantisSite.getViewIssuesPage().viewActionList();
        mantisSite.getViewIssuesPage().checkDelete();
        mantisSite.getViewIssuesPage().buttonOkClick();

        wait.until(ExpectedConditions.visibilityOf(mantisSite.getViewIssuesPage().getSelectedIssue()));
        String actualIssueTextDelete = mantisSite.getViewIssuesPage().getSelectedIssue().getText();

        softAssert.assertThat(actualIssueTextDelete).contains(expectedIssueText);

        mantisSite.getViewIssuesPage().deleteIssueButtonClick();
        wait.until(ExpectedConditions.visibilityOf(mantisSite.getViewIssuesPage().getFirstIssueSummary()));

        softAssert.assertThat(actualIssueText).doesNotContain(expectedIssueText);
        softAssert.assertAll();
    }
}