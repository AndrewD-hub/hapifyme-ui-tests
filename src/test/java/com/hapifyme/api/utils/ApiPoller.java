package com.hapifyme.api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class ApiPoller {

    public static String waitForConfirmationToken(String baseUri, String apiKey, String email) {
        final String[] confirmationToken = new String[1];

        await()
                .alias("Wait for confirmation token")
                .atMost(20, SECONDS)
                .pollDelay(2, SECONDS)
                .pollInterval(2, SECONDS)
                .ignoreExceptions()
                .until(() -> {
                    Response response = given()
                            .baseUri(baseUri)
                            .header("Authorization", apiKey)
                            .queryParam("username_or_email", email)
                            .when()
                            .get("/user/retrieve_token.php");

                    if (response.statusCode() != 200) {
                        return false;
                    }

                    String token = response.jsonPath().getString("confirmation_token");

                    if (token != null && !token.isBlank()) {
                        confirmationToken[0] = token;
                        return true;
                    }

                    return false;
                });

        return confirmationToken[0];
    }
}