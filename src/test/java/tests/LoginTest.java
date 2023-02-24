package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
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

    @Test(description = "Login users with valid data",retryAnalyzer = ReTry.class)
    @Description("Login users with valid data")
    public void positiveLogin() {
        boolean isLoggedIn = loginPage.open().login( USERNAME, PASSWORD).isPageOpened();
        assertTrue(isLoggedIn);
    }

    @Test(description = "logout user")
    @Description("logout user")
    public void logoutUser() {
        boolean isLoggedIn = loginPage.open().login(USERNAME, PASSWORD).isPageOpened();
        assertTrue(isLoggedIn);
        profilePage.clickLogoutButton();
        assertTrue(loginPage.isPageOpened());
    }

    @Test(description = "Checked ERROR message ",dataProvider = "Negative Test Login Data")
    @Description("login with negative data")
    public void loginNegativeTest(String username, String password, String expectedErrorMessage) {
        loginPage.login(username, password);
        AllureUtils.attachScreenshot(driver);
        String actualErrorMessageText = loginPage.getErrorMessageText();
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
