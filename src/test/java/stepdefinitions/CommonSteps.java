package stepdefinitions;

import config.TestConfig;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class CommonSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("utilizatorul este autentificat în aplicație")
    public void userIsLoggedIn() {
        loginPage.openPage();
        loginPage.login(TestConfig.VALID_EMAIL, TestConfig.VALID_PASSWORD);
        loginPage.verifyUserIsOnFeed();
    }
}
