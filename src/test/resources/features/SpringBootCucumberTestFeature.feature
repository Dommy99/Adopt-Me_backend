Feature: Adopt Me Rest API functionalities


    # Unregistered User user stories
  Scenario: An unregistered user is able to register
    Given A email is not registered
    When A user registers with unique email and a password
    Then A new user account is created and returned


  Scenario: Any user is able to view  all animals
    Given A list of animals are available


  Scenario: Any logged-in user can add or remove an animal to their like list
    Given a list of animals exists
    When user adds an animal to like list
    Then the animal is added to user like list
    When user removes an anime from their like list
    Then the animal is removed from the user like list

  Scenario: A registered user is able to register
    Given A user registers
    When A user logs in
    Then A user should be able to log in
