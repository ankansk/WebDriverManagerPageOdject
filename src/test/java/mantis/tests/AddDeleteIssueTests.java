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
        SoftAssertions softAssert = new SoftAssertions();

        mantisSite.login("admin", "admin20");

        //mantisSite.getMainPage().goToReportIssuePage();

        String issueSummary = "New Issue";
        String issueDescription = "New description";

        mantisSite.getReportIssuePage().createIssue(issueSummary, issueDescription);

        waitUntilVisible(mantisSite.getViewIssuesPage().getFirstIssueSummary());

        String actualIssueText = mantisSite.getViewIssuesPage().getFirstIssueSummary().getText();
        String actualTimeStamp = mantisSite.getReportIssuePage().getTimeStamp();

        softAssert.assertThat(actualIssueText).contains(issueSummary);
        softAssert.assertThat(actualIssueText).contains(actualTimeStamp);

        mantisSite.getViewIssuesPage().deleteFirstIssue();

        waitUntilVisible(mantisSite.getViewIssuesPage().getSelectedIssue());

        String actualDeletedIssueText = mantisSite.getViewIssuesPage().getSelectedIssue().getText();
        softAssert.assertThat(actualDeletedIssueText).contains(issueSummary);

        mantisSite.getViewIssuesPage().deleteIssueButtonClick();
        waitUntilVisible(mantisSite.getViewIssuesPage().getFirstIssueSummary());

        softAssert.assertThat(mantisSite.getViewIssuesPage().getFirstIssueSummary().getText()).doesNotContain(issueSummary);
    }
}