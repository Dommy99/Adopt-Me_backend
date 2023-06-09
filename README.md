# Adopt-Me

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Intellij](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

## Table of Contents

- [Description](#description)
- [Approach](#approach)
- [Other Tools And Tech](#other-tools-and-tech)
- [Planning Board](#planning-board)
- [User-Stories](#user-stories)
- [Endpoints](#endpoints-for-all-users)
- [Features](#features)
- [Installation](#installation)
- [Dependencies Used](#dependencies-used)
- [Documentation Used](#documentation-used)
- [Credit](#Credit)

## Description

- Pet adoption social media for the modern age 
- [Frontend](https://github.com/Dommy99/Adopt-Me-frontend) 

## Approach
 -   After designing the frontend mockup in Figma I started drawing out the ERD for the project making sure I thoroughly mapped out all the tables and the relationships.
I then listed and researched all the technologies I thought I would need to make this project a reality. I then got a second set of eyes to help me review the plans and iron out any
mistakes or inefficient ideas. 
 -   I then broke up the project onto smaller more digestible pieces and make a planning board with these pieces. The backend was the main focus in the beginning using TDD and postman to make 
sure everything was working as intended. I started with public endpoints first then moved on into the private endpoints and security. Once all the tests passed and the postman returned all the correct data
I moved onto the frontend and called the api from there.


## Other Tools And Tech

- JPA
- Cucumber
- H2 Database
- Postman
- Kanban board
- JUnit

## [Planning Board](https://github.com/users/tanyigbo/projects/2/views/1)

![Board.](/documentation/adopt-me-planningboard.JPG)

## User-Stories

- As a user, I should be able to register.
- As a user, I should be able to log in.
- As a user, I should be able to log out.
- As a logged-in user, I should be able to see a carousel of animals.
- As a logged-in user, I should be able to like the animals in the carousel.
- As a logged-in user, I should be able to see a list of liked animals (these liked animals show contact information for adoption).
- As a logged-in user, I should be able to remove animals from the animal liked list.

![ERD.](/documentation/adopt-me-ERD.JPG)
![Postman.](/documentation/adopt-me-postman.JPG)

# Endpoints for all users
| Request Type | URL                   | Functionality                     | Access |
|--------------|-----------------------|-----------------------------------|--------|
| POST         | /auth/users/register/ | Request to create a new user      | Public |
| POST         | /auth/users/login/    | Responsible for logging in a user | Public |
| GET          | /api/animal/          | Returns all animals               | Public |

# Endpoints for registered users
| Request Type | URL                      | Functionality                     | Access  |
|--------------|--------------------------|-----------------------------------|---------|
| POST         | /api/like/{animalId}     | Add animal to user like list      | Private |
| DELETE       | /api/like/{animalId}     | Delete animal from user like list | Private |
| GET          | /api/user/{userId}/likes | Get liked animals by user         | Private |



## Features
- User login
- User register
- Private security endpoints


### Installation
To set up the app locally on your own system:
1. Fork this [repository](https://github.com/Dommy99/Adopt-Me_backend)
2. Clone the repo to your local machine using the link from the green <>Code combo button:
```
git clone <https://github.com/{GitHub UserName}/Adopt-Me_backend>

```
3. Then open the repo in IntelliJ or your IDE of choice
4. All required dependencies are included

### Dependencies Used
- [Spring Jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.0.6)

- [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)

- [Spring Boot Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter/3.0.6)

- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.0.5)

- [Postgres Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0)

- [Cucumber](https://cucumber.io/docs/installation/java/)

- [JUnit](https://junit.org/junit5/)

- [H2 Database](https://mvnrepository.com/artifact/com.h2database/h2)

- [JWT](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api)

## Documentation Used

- [Baeldung](https://www.baeldung.com/jpa-many-to-many)
- [vladmihalcea](https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/)
- [Stackoverflow](https://stackoverflow.com/)
- [Cucumber](https://cucumber.io/docs/installation/java/#maven)
- [Java Spring Boot Lecture](https://git.generalassemb.ly/sureshmelvinsigera/Java-Spring-Boot-lecture/blob/main/README.md)

## Credit
- Shout out to [Leonardo Rodriguez](https://github.com/LRodriguez92), [Kevin Barrios](https://github.com/dayjyun), [Jay Padilla](https://github.com/Jaypad07) for help with bug fixes and code reviews.