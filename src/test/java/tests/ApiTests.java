package tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyReader;
import java.util.List;
import static endpoint.Endpoints.GET_BOOKS;
import static pages.BasePage.webElements;
@Log4j2
public class ApiTests extends BaseTest {

    @BeforeMethod
    public void openUrl() {
        bookPage.open();
    }
    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test
    public void getBodyTitleAllBookso() {
        webElements = bookPage.getAllBook();
        JsonPath books = RestAssured.get(PropertyReader.getUrl() + GET_BOOKS).getBody().jsonPath();
        List<String> tittles = books.get("books.title");
        Assert.assertEquals(tittles, webElements);
    }
}
