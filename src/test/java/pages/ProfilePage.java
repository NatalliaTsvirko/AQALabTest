package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2
public class ProfilePage extends BasePage{

    private static final By BUTTON_GO_TO_STORE = By.xpath("//button[@id = 'gotoStore']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[@id = 'submit' and text()='Log out']");
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isElementPresent(BUTTON_GO_TO_STORE);
    }

    @Override
    public BasePage open() {
        driver.get(BASE_URL + "/profile");
        return this;
    }

    @Step("Click on logout button")
    public ProfilePage clickLogoutButton() {
        log.info("click on lout button");
        driver.findElement(LOGOUT_BUTTON).click();
        return this;
    }
}
