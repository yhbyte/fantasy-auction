package com.bytes.and.dragons.fantasyauction.integration;

import static com.bytes.and.dragons.fantasyauction.util.TestData.EMAIL;
import static com.bytes.and.dragons.fantasyauction.util.TestData.PASSWORD;
import static com.bytes.and.dragons.fantasyauction.util.TestData.TEST_NAME;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bytes.and.dragons.fantasyauction.config.PostgresTestContainerConfig;
import com.bytes.and.dragons.fantasyauction.model.request.SignInRequest;
import com.bytes.and.dragons.fantasyauction.model.request.SignUpRequest;
import com.bytes.and.dragons.fantasyauction.model.response.JwtAuthenticationResponse;
import com.bytes.and.dragons.fantasyauction.security.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ContextConfiguration;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = PostgresTestContainerConfig.class)
class AuthenticationControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @LocalServerPort
    private int port;

    @Autowired
    private JwtDecoder jwtDecoder;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void signUp_shouldReturnValidJwtToken_whenRequestIsValid() throws JsonProcessingException {
        // given
        SignUpRequest signUpRequest = new SignUpRequest(TEST_NAME, EMAIL, PASSWORD);
        var payload = objectMapper.writeValueAsString(signUpRequest);

        // when
        JwtAuthenticationResponse response =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("/auth/signup")
                        .then()
                        .statusCode(200)
                        .extract().body().as(JwtAuthenticationResponse.class);

        // then
        assertNotNull(response);
        Jwt jwt = jwtDecoder.decode(response.getToken());

        assertEquals(TEST_NAME, jwt.getSubject());
        assertEquals(1, (Long) jwt.getClaim("userId"));
    }

    @Test
    @DataSet(executeScriptsBefore = "scripts/populate_users.sql", executeScriptsAfter = "scripts/clear_schema.sql")
    void signIn_shouldReturnValidJwtToken_whenRequestIsValid() throws JsonProcessingException {
        // given
        SignInRequest signUpRequest = new SignInRequest("bytesanddragonsua", "bytesanddragonsua1234");
        var payload = objectMapper.writeValueAsString(signUpRequest);

        // when
        JwtAuthenticationResponse response =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("/auth/signin")
                        .then()
                        .statusCode(200)
                        .extract().body().as(JwtAuthenticationResponse.class);

        // then
        assertNotNull(response);
        Jwt jwt = jwtDecoder.decode(response.getToken());

        assertEquals("bytesanddragonsua", jwt.getSubject());
        assertEquals(1, (Long) jwt.getClaim("userId"));
    }
}
