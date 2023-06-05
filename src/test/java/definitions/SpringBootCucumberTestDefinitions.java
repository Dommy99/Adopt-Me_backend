package definitions;

import com.api.adoptme.AdoptMeApplication;
import com.api.adoptme.model.Animal;
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
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AdoptMeApplication.class)
public class SpringBootCucumberTestDefinitions {

    private static final String BASE_URL = "http://localhost:";

    private static RequestSpecification request = RestAssured.given();

    private static Response response;

    private static ResponseEntity<String> responseEntity;

    private static List<?> list;

    private Long animalId;

    private Long userId;
    @LocalServerPort
    String port;
    public String getSecurityKey() throws Exception {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "123@email.com");
        requestBody.put("password", "123");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");
        System.out.println(response.jsonPath().getString("data"));
        return response.jsonPath().getString("data");
    }


    // Unregistered User
    // Scenario: An unregistered user is able to register
    @Given("A email is not registered")
    public void aEmailIsNotRegistered() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email@email.com");
        requestBody.put("password", "123");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");
        Assert.assertEquals(201, response.getStatusCode());
    }


    @When("A user registers with unique email and a password")
    public void aUserRegistersWithUniqueEmailAndAPassword() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "test@email.com");
        requestBody.put("password", "123");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");
    }

    @Then("A new user account is created and returned")
    public void aNewUserAccountIsCreatedAndReturned() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");
        Assert.assertEquals("user created", message);
        Assert.assertEquals("test@email.com", user.get("email"));
    }

    // Scenario: Any user is able to view  all animals
    @Given("A list of animals are available")
    public void aListOfAnimalsAreAvailable() {
        response = request.get(BASE_URL + port + "/api/animal/");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animal = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animal.size() > 0);

    }


    // Scenario: Any logged-in user can add or remove an animal to their like list
    @Given("a list of animals exists")
    public void aListOfAnimalsExists() throws Exception {
        response = request.get(BASE_URL + port + "/api/animal/");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animal = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animal.size() > 0);
    }

    @When("user adds an animal to like list")
    public void userAddsAnAnimalToLikeList() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("data", new Animal("Tim","male","brown","2","boxer","dog","https://images.unsplash.com/photo-1592754862816-1a21a4ea2281?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60", null) );
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKey());
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/like/2");
    }



    @Then("the animal is added to user like list")
    public void theAnimalIsAddedToUserLikeList() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, Object> userAnimal = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(userAnimal.get("animal").toString().contains("Tim"));
    }

    @When("user removes an anime from their like list")
    public void userRemovesAnAnimeFromTheirLikeList() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("data", new Animal("Tim","male","brown","2","boxer","dog","https://images.unsplash.com/photo-1592754862816-1a21a4ea2281?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60", null) );
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKey());
        response = request.body(requestBody.toString()).delete(BASE_URL + port + "/api/like/2");
    }

    @Then("the animal is removed from the user like list")
    public void theAnimalIsRemovedFromTheUserLikeList() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, Object> userAnimal = response.jsonPath().get("data");
        Assert.assertEquals("delete success", message);
        Assert.assertTrue(userAnimal.get("animal").toString().contains("Tim"));
    }


    @Given("A user registers")
    public void aUserRegisters() {

    }

    @When("A user logs in")
    public void aUserLogsIn() {

    }

    @Then("A user should be able to log in")
    public void aUserShouldBeAbleToLogIn() {
    }
}
