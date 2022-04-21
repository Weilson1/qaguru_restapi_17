package homework;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests {

    @Test
    void singleUser() {
        get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void createUser() {
        String user = "{\"name\": \"Lambert\", \"job\": \"Witcher\"}";
        given()
                .contentType(JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    void updateUser() {
        String userUpdate = "{\"name\": \"Geralt\", \"job\": \"Witcher\"}";
        given()
                .contentType(JSON)
                .body(userUpdate)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("Geralt"))
                .body("job", is("Witcher"));
    }

    @Test
    void resourceNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }
}
