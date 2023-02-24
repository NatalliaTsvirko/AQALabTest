package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.BookPage;
import pages.LoginPage;
import pages.ProfilePage;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected BookPage bookPage;
    protected ProfilePage profilePage;



    String USERNAME = System.getenv().getOrDefault("USER_NAME", PropertyReader.getProperty("books.username"));
    String PASSWORD = System.getenv().getOrDefault("USER_PASSWORD", PropertyReader.getProperty("books.password"));

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    @Step("Open browser")
    public void setUp(ITestContext testContext, @Optional("chrome") String browser) {

        driver = DriverFactory.getDriver(browser);
        testContext.setAttribute("driver", driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        bookPage = new BookPage(driver);
        profilePage = new ProfilePage(driver);


    }

    @AfterClass(alwaysRun = true)
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}