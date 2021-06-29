import io.qameta.allure.Description;
import methods.Methods;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pojo_models.requests.UserBodyRequest;
import pojo_models.responses.CreateUserResponce;
import pojo_models.responses.ErrorBodyResponce;
import pojo_models.responses.UserBodyResponce;

import java.util.concurrent.ThreadLocalRandom;

public class UserPetstore extends BaseTest {
    private Integer id = ThreadLocalRandom.current().nextInt(100, 90000 + 1);
    private String username = "Test";
    private String firstname = "Test";
    private String lastname = "Test";
    private String email = "Test";
    private String password = "Test";
    private String phone = "Test";
    private Integer userStatus = 2;
    private Integer status = 200;


    @Description("Check response after User is created")
    @Test
    public void createUser() {
        CreateUserResponce createUserResponce = Methods.createUser(id, username, firstname, lastname, email, password,
                phone, userStatus).statusCode(200).extract().as(CreateUserResponce.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(createUserResponce.getCode()).as("code").isEqualTo(status);
            softly.assertThat(createUserResponce.getType()).as("type").isEqualTo("unknown");
        });
    }

    @Description("Check response after multiple Users are created")
    @Test
    public void createUsers() {
        CreateUserResponce createUserResponce = Methods.createUsers(id, username, firstname, lastname, email, password,
                phone, userStatus).statusCode(200).extract().as(CreateUserResponce.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(createUserResponce.getCode()).as("code").isEqualTo(status);
            softly.assertThat(createUserResponce.getType()).as("type").isEqualTo("unknown");
        });
    }

    @Description("Get User data by username")
    @Test
    public void getUser() {
        UserBodyResponce userBodyResponce = Methods.getUserByUserName(username).statusCode(200).extract().as(UserBodyResponce.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userBodyResponce.getUsername()).as("username").isEqualTo(username);
        });
    }

    @Description("Run GET: request for not existing username")
    @Test
    public void getDefunctUser() {
        username = "-";
        ErrorBodyResponce errorBodyResponce = Methods.getUserByUserName(username).statusCode(404).extract().as(ErrorBodyResponce.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(errorBodyResponce.getCode()).as("code").isEqualTo(1);
            softly.assertThat(errorBodyResponce.getType()).as("type").isEqualTo("error");
            softly.assertThat(errorBodyResponce.getMessage()).as("message").isEqualTo("User not found");
        });
    }
}
