package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static org.testng.Assert.assertTrue;

@Log4j2
public class LoginTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginPage.open().isPageOpened();
    }
    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test(description = "Login users with valid data",retryAnalyzer = ReTry.class)
    @Description("Login users with valid data")
    public void positiveLogin() {
        log.info("checking that the page is opened");
        boolean isLoggedIn = loginPage.open().login( USERNAME, PASSWORD).isPageOpened();
        log.info("checking that user is logged");
        assertTrue( isLoggedIn);
    }

    @Test(description = "logout user")
    @Description("logout user")
    public void logoutUser() {
        log.info("checking that the page is opened");
        boolean isLoggedIn = loginPage.open().login(USERNAME, PASSWORD).isPageOpened();
        assertTrue(isLoggedIn);
        log.info("on profile page click on button 'logout'");
        profilePage.clickLogoutButton();
        log.info("checking that user on login page");
        assertTrue(loginPage.isPageOpened());
    }

    @Test(description = "Checked ERROR message ",dataProvider = "Negative Test Login Data")
    @Description("login with negative data")
    public void loginNegativeTest(String username, String password, String expectedErrorMessage) {
        log.info("input on field username and password");
        loginPage.login(username, password);
        log.info("take screenshot");
        AllureUtils.attachScreenshot(driver);
        String actualErrorMessageText = loginPage.getErrorMessageText();
        log.info("checking actualErrorMessageText with expectedErrorMessage");
        Assert.assertEquals(actualErrorMessageText, expectedErrorMessage);

    }

    @DataProvider(name = "Negative Test Login Data")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"uguygu", "sdgrsg", "Invalid username or password!" },
                {"", "Valmochka99*", ""},
                {"Valmochka", "", "" }

        };
    }
}
