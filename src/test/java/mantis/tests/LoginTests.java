package mantis.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void loginUrlTest() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://academ-it.ru/mantisbt/login_page.php", currentUrl);
    }

    @Test
    public void loginTitleTest() {
        String currentTitle = driver.getTitle();
        Assertions.assertEquals("MantisBT", currentTitle);
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        String currentUserName = mantisSite.getMainPage().getUserName();
        Assertions.assertEquals("admin", currentUserName);
        Thread.sleep(1000);
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "blabla");

        String actualErrorMessage = mantisSite.getPasswordPage().getErrorMessage();

        Assertions.assertEquals("Возможно, ваша учетная запись заблокирована, или введенное регистрационное имя/пароль неправильны.", actualErrorMessage);
        Thread.sleep(2000);
    }

    @Test
    public void checkMainPageBlocksLoadingTest() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(mantisSite.getMainPage().isAssignedToMeBlockDisplayed()).isEqualTo(true);
        softAssert.assertThat(mantisSite.getMainPage().isUnassignedToMeBlockDisplayed()).isEqualTo(true);

        softAssert.assertAll();
    }
}