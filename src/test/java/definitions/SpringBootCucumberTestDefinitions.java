package definitions;

import com.api.adoptme.AdoptMeApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AdoptMeApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";

    private static final RequestSpecification request = RestAssured.given();

    private static Response response;
    @LocalServerPort
    String port;

//    public SpringBootTestDefinitions() {
//        RestAssured.baseURI = BASE_URL;
//    }

    // Unregistered User
    // Scenario: An unregistered user is able to register
    @Given("A email is not registered")
    public void aEmailIsNotRegistered() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email@email.com");
        requestBody.put("password", "123");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");
        Assert.assertEquals(401, response.getStatusCode());
    }


    @When("A user registers with unique email and a password")
    public void aUserRegistersWithUniqueEmailAndAPassword() {
        
    }

    @Then("A new user account is created and returned")
    public void aNewUserAccountIsCreatedAndReturned() {
        
    }

    // Login user
    // Scenario: A user is able to log in
    @Given("A list of registered users")
    public void aListOfRegisteredUsers() {
        
    }

    @When("A registered user enters email and password")
    public void aRegisteredUserEntersEmailAndPassword() {
        
    }

    @Then("The user is logged into the account and provided a token")
    public void theUserIsLoggedIntoTheAccountAndProvidedAToken() {
        
    }

    // Scenario: Any user is able to view  all animals
    @Given("A list of animals are available")
    public void aListOfAnimalsAreAvailable() {
//        response = request.get(BASE_URL + port + "/api/animal");
//        String message = response.jsonPath().getString("message");
//        List<Map<String, String>> animal = response.jsonPath().get("data");
//        Assert.assertEquals("success", message);
//        Assert.assertTrue(animal.size() > 0);

    }

    @When("A user searches for all animals")
    public void aUserSearchesForAllAnimals() {
        
    }

    @Then("A list of all animals is returned")
    public void aListOfAllAnimalsIsReturned() {
        
    }

    // Scenario: Any logged-in user can add or remove an animal to their like list
    @Given("a list of animals exists")
    public void aListOfAnimalsExists() {

    }

    @When("user adds an animal to like list")
    public void userAddsAnAnimalToLikeList() {
        
    }

    @Then("the animal is added to user like list")
    public void theAnimalIsAddedToUserLikeList() {
        
    }

    // Scenario: A liked animal should return adoption agency information
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
