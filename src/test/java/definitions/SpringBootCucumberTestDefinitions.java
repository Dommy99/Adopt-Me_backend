package definitions;

import com.api.adoptme.AdoptMeApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AdoptMeApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    @Given("A email is not registered")
    public void aEmailIsNotRegistered() {
    }

    @When("A user registers with unique email and a password")
    public void aUserRegistersWithUniqueEmailAndAPassword() {
        
    }

    @Then("A new user account is created and returned")
    public void aNewUserAccountIsCreatedAndReturned() {
        
    }

    @Given("A list of registered users")
    public void aListOfRegisteredUsers() {
        
    }

    @When("A registered user enters email and password")
    public void aRegisteredUserEntersEmailAndPassword() {
        
    }

    @Then("The user is logged into the account and provided a token")
    public void theUserIsLoggedIntoTheAccountAndProvidedAToken() {
        
    }

    @Given("A list of animals are available")
    public void aListOfAnimalsAreAvailable() {
        
    }

    @When("A user searches for all animals")
    public void aUserSearchesForAllAnimals() {
        
    }

    @Then("A list of all animals is returned")
    public void aListOfAllAnimalsIsReturned() {
        
    }

    @Given("a list of animals exists")
    public void aListOfAnimalsExists() {
        
    }

    @When("user adds an animal to like list")
    public void userAddsAnAnimalToLikeList() {
        
    }

    @Then("the animal is added to user like list")
    public void theAnimalIsAddedToUserLikeList() {
        
    }

    @Given("a list of liked animals exists")
    public void aListOfLikedAnimalsExists() {
        
    }

    @When("the animal is added to the users liked list")
    public void theAnimalIsAddedToTheUsersLikedList() {
        
    }

    @Then("that animals adoption information is shown")
    public void thatAnimalsAdoptionInformationIsShown() {
    }
}
