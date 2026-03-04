package steps;

import com.google.gson.Gson;
import config.RequestConfig;
import dto.request.CreateUserRequest;
import dto.response.CreateAndLoginUserResponse;
import enums.APIEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSteps {

    private Gson gson = new Gson();

    @Step("Создаю пользователя рестом")
    public String createUserByRest(CreateUserRequest newUserRequest){
        Response response = given()
                .spec(RequestConfig.getRequestSpec())
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(newUserRequest))
                .when()
                .post(APIEndpoints.CREATE_USER.getPath());
        response.then().statusCode(200);
        CreateAndLoginUserResponse user = gson.fromJson(response.body().asString(), CreateAndLoginUserResponse.class);
        return user.getAccessToken();
    }

    @Step("Удаляю пользователя рестом")
    void deleteUserRest(String token) {
        Response response = given().spec(RequestConfig.getRequestSpec()).header("Authorization", token).delete(APIEndpoints.ACTIONS_USER.getPath());
        response.then().statusCode(200);
    }
}
