package steps;

import com.google.gson.Gson;
import config.Config;
import config.LocalStorageHelper;
import dto.request.CreateUserRequest;
import dto.request.LoginUserRequest;
import dto.response.CreateAndLoginUserResponse;
import enums.APIEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public class RestSteps {

    private Gson gson = new Gson();

    @Step("Создаю пользователя рестом")
    public CreateAndLoginUserResponse createUserByRest(CreateUserRequest newUserRequest){
        Response response = given()
                .spec(Config.getRequestSpec())
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(newUserRequest))
                .when()
                .post(APIEndpoints.CREATE_USER.getPath());
        response.then().statusCode(200);
        return gson.fromJson(response.body().asString(), CreateAndLoginUserResponse.class);
    }

    @Step("Удаляю пользователя рестом")
    public void deleteUserRest(String token) {
        Response response = given().spec(Config.getRequestSpec()).header("Authorization", token).delete(APIEndpoints.ACTIONS_USER.getPath());
        response.then().statusCode(202);
    }

    @Step("Авторизуюсь под пользователем с email \"{email}\" и паролем \"{password}\"")
    public CreateAndLoginUserResponse loginUserRest(String email, String password) {
        Response response = given()
                .spec(Config.getRequestSpec())
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(new LoginUserRequest(email, password)))
                .when()
                .post(APIEndpoints.LOGIN_USER.getPath());
        response.then().statusCode(200);
        return gson.fromJson(response.body().asString(), CreateAndLoginUserResponse.class);
    }

    @Step("Добавляю в Local Storage accessToken и refreshToken для авторизации")
    public void setLocalStorage(CreateAndLoginUserResponse user, WebDriver driver) {
        LocalStorageHelper helper = new LocalStorageHelper(driver);
        helper.setItem("accessToken", user.getAccessToken());
        helper.setItem("refreshToken", user.getRefreshToken());
    }
}
