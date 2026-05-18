package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("utilizatorul este pe pagina de login")
    public void userIsOnLoginPage() {
        loginPage.openPage();
    }

    @When("utilizatorul se autentifică folosind emailul {string} și parola {string}")
    public void userLogsIn(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("utilizatorul ajunge pe pagina de feed")
    public void userShouldBeOnFeed() {
        loginPage.verifyUserIsOnFeed();
    }

    @Then("utilizatorul vede mesajul de eroare {string}")
    public void userSeesErrorMessage(String expectedMessage) {
        loginPage.verifyErrorMessage(expectedMessage);
    }
}
