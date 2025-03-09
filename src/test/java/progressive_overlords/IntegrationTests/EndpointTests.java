package progressive_overlords.IntegrationTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import progressive_overlords.entities.responses.GenericResponse;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndpointTests {

    @LocalServerPort
    private int port = 8081;

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    void setupUser() {
        String url = "http://localhost:" + port + "/api/v1/auth/sign-up?username=testuser&password=Test1234";
        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url, null, GenericResponse.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "User should be created once before all tests");
    }

    @Test
    void testCreateUser() {
        String url = "http://localhost:" + port +"/api/v1/auth/sign-up?username=perepalacin&password=Test1234";

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url, null, GenericResponse.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Successful register", response.getBody().getMessage());
    }

    @Test
    void testSignIn() {
        String url = "http://localhost:" + port + "/api/v1/auth/sign-in?username=perepalacin&password=Test1234";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, request, Void.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        Assertions.assertNotNull(cookies, "No cookies found in response headers!");
        Assertions.assertTrue(cookies.stream().anyMatch(cookie -> cookie.startsWith("SESSION_ID=")), "SESSION_ID cookie missing!");
        String location = response.getHeaders().getLocation() != null ? response.getHeaders().getLocation().toString() : null;
        Assertions.assertNull(location, "Location should be null since we are not redirecting.");
    }

    @Test
    void TestCreateTemplate() {
        String url = "http://localhost:" + port + "/api/v1/workouts/templates?name=Test+Template&description=Full+body+workout&color=red&bodyPart=arms&unparsedTags=strength%2C+endurance&exercisesId%5B%5D=1&exercisesId%5B%5D=2&exercisesId%5B%5D=3&sets%5B%5D=3&sets%5B%5D=4&sets%5B%5D=3&reps%5B%5D=10&reps%5B%5D=15&reps%5B%5D=10";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, null, Void.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void TestEditTemplate() {
        String url = "http://localhost:" + port + "/api/v1/workouts/templates";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "id=1&name=Suahili+GuineaL+Template&description=Full+body+workout&color=red"
                + "&bodyPart=arms&tags=strength,endurance"
                + "&exercisesId=1&exercisesId=2&exercisesId=3"
                + "&sets=3&sets=4&sets=3"
                + "&reps=10&reps=15&reps=10";
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PATCH, request, Void.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    void TestEditTemplate() {
//        String url = "http://localhost:" + port + "/api/v1/workouts/templates?id=1&name=Suahili+GuineaL+Template&description=Full+body+workout&color=red&bodyPart=arms&tags=strength%2C+endurance&exercisesId%5B%5D=1&exercisesId%5B%5D=2&exercisesId%5B%5D=3&sets%5B%5D=3&sets%5B%5D=4&sets%5B%5D=3&reps%5B%5D=10&reps%5B%5D=15&reps%5B%5D=10";
//        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PATCH, null, Void.class);
//        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
//    }

    @Test
    void TestDeleteTemplate() {
        String url = "http://localhost:" + port + "/api/v1/workouts/1";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
