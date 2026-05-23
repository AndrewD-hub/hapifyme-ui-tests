package com.hapifyme.api.tests;

import com.hapifyme.api.models.LoginRequest;
import com.hapifyme.api.models.RegisterRequest;
import com.hapifyme.api.models.UpdateProfileRequest;
import com.hapifyme.api.utils.ApiPoller;
import com.hapifyme.api.utils.DataGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;


public class UserLifecycleTest {

    private static final String BASE_URI = "https://apps.qualiadept.eu/hapifyme/api";

    @Test
    public void testUserLifecycleEndToEnd() {
        RestAssured.baseURI = BASE_URI;

        String email = DataGenerator.generateUniqueEmail();
        String password = DataGenerator.generatePassword();
        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();

        RegisterRequest registerRequest = new RegisterRequest(
                firstName,
                lastName,
                email,
                password
        );

        Response registerResponse = given()
                .contentType("application/json")
                .body(registerRequest)
                .when()
                .post("/user/register.php")
                .then()
                .extract()
                .response();

        System.out.println("Register status: " + registerResponse.statusCode());

        registerResponse.then().statusCode(201);

        String apiKey = registerResponse.jsonPath().getString("api_key");
        int userId = registerResponse.jsonPath().getInt("user_id");
        String username = registerResponse.jsonPath().getString("username");

        System.out.println("Register body: " + registerResponse.asString());
        System.out.println("Generated username from API: " + username);

        System.out.println("Created user id: " + userId);
        System.out.println("Created email: " + email);

        String confirmationToken = ApiPoller.waitForConfirmationToken(BASE_URI, apiKey, email);

        System.out.println("Confirmation token: " + confirmationToken);

        given()
                .queryParam("token", confirmationToken)
                .when()
                .get("/user/confirm_email.php")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));

        LoginRequest loginRequest = new LoginRequest(username, password);

        Response loginResponse = given()
                .contentType("application/json")
                .body(loginRequest)
                .when()
                .post("/user/login.php")
                .then()
                .extract()
                .response();

        System.out.println("Login status: " + loginResponse.statusCode());
        System.out.println("Login body: " + loginResponse.asString());

        loginResponse.then().statusCode(200);

        String bearerToken = loginResponse.jsonPath().getString("token");

        System.out.println("Bearer token received.");

         given()
                .header("Authorization", apiKey)
                .queryParam("user_id", userId)
                .when()
                .get("/user/get_profile.php")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("user.email", equalTo(email))
                .body("user.first_name", equalTo(firstName))
                .body("user.last_name", equalTo(lastName))
                .extract()
                .response();

        String updatedFirstName = "UpdatedDumi";
        String updatedLastName = "UpdatedTester";

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                userId,
                updatedFirstName,
                updatedLastName,
                email
        );

        Response updateResponse = given()
                .contentType("application/json")
                .header("Authorization", apiKey)
                .body(updateProfileRequest)
                .when()
                .put("/user/update_profile.php")
                .then()
                .extract()
                .response();

        System.out.println("Update status: " + updateResponse.statusCode());
        System.out.println("Update body: " + updateResponse.asString());

        updateResponse.then()
                .statusCode(200)
                .body("status", equalTo("success"));

        given()
                .header("Authorization", apiKey)
                .queryParam("user_id", userId)
                .when()
                .get("/user/get_profile.php")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("user.first_name", equalTo(updatedFirstName))
                .body("user.last_name", equalTo(updatedLastName))
                .body("user.email", equalTo(email));

        Response deleteResponse = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + bearerToken)
                .body("{\"user_id\":" + userId + "}")
                .when()
                .delete("/user/delete_profile.php")
                .then()
                .extract()
                .response();

        System.out.println("Delete status: " + deleteResponse.statusCode());
        System.out.println("Delete body: " + deleteResponse.asString());

        deleteResponse.then()
                .statusCode(200)
                .body("status", equalTo("success"));

        Response negativeProfileResponse = given()
                .header("Authorization", apiKey)
                .queryParam("user_id", userId)
                .when()
                .get("/user/get_profile.php")
                .then()
                .extract()
                .response();

        System.out.println("Negative profile status: " + negativeProfileResponse.statusCode());
        System.out.println("Negative profile body: " + negativeProfileResponse.asString());

        negativeProfileResponse.then()
                .statusCode(200)
                .body("status", equalTo("error"))
                .body("message", equalTo("User not found."));
    }
}