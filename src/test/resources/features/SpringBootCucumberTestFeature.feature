Feature: Adopt Me Rest API functionalities


    # Unregistered User user stories
  Scenario: An unregistered user is able to register
    Given A email is not registered
    When A user registers with unique email and a password
    Then A new user account is created and returned

  # All user login user stories
  Scenario: A user is able to log in
    Given A list of registered users
    When A registered user enters email and password
    Then The user is logged into the account and provided a token

  Scenario: Any user is able to view  all animals
    Given A list of animals are available
    When A user searches for all animals
    Then A list of all animals is returned

  Scenario: Any logged-in user can add or remove an animal to their like list
    Given a list of animals exists
    When user adds an animal to like list
    Then the animal is added to user like list

  Scenario: A liked animal should return adoption agency information
    Given a list of liked animals exists
    When the animal is added to the users liked list
    Then that animals adoption information is shown
