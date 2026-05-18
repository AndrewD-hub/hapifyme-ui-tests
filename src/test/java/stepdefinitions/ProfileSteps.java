package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

import java.util.List;
import java.util.Map;

public class ProfileSteps {
    private final SearchPage searchPage = new SearchPage();
    private String expectedProfileName;

    @When("utilizatorul caută următorul profil:")
    public void userSearchesProfile(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String searchTerm = rows.get(0).get("searchTerm");
        expectedProfileName = rows.get(0).get("expectedName");

        searchPage.searchForUser(searchTerm);
        searchPage.verifyLiveResultsContain(expectedProfileName);
    }

    @When("utilizatorul deschide profilul găsit")
    public void userOpensFoundProfile() {
        searchPage.openSearchPage(expectedProfileName.replace(" ", "+"));
        searchPage.openProfileFromResults(expectedProfileName);
    }

    @Then("pagina profilului utilizatorului este afișată")
    public void profilePageShouldBeDisplayed() {
        searchPage.verifyProfilePageIsOpened();
    }
}
