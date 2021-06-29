import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite()
    public static void init()
    {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.filters(new AllureRestAssured());
        RestAssured.requestSpecification = new RequestSpecBuilder().
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }
}
