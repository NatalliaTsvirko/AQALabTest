package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;

@Log4j2
public class LoginPage extends BasePage {

    private static final By INPUT_USERNAME = By.xpath("//input[@id = 'userName']");
    private static final By INPUT_PASSWORD = By.xpath("//input[@id = 'password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@id = 'login']");
    private static final By ERROR_MESSAGE = By.xpath("//p[text() = 'Invalid username or password!']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isElementPresent(LOGIN_BUTTON);
    }

    @Override
    public LoginPage open() {
        driver.get(BASE_URL_LOGIN);
        return this;
    }

    @Step("Login to Salesforce.com with username and password")
    public ProfilePage login(String username, String password) {
        log.info("open home page");
        driver.findElement(INPUT_USERNAME).sendKeys(username);
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        AllureUtils.attachScreenshot(driver);
        return new ProfilePage(driver);
    }

    @Step("Take error message")
    public String getErrorMessageText() {
        log.info("Expected error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
