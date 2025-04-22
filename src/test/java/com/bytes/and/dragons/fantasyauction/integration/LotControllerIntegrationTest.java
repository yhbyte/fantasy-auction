package com.bytes.and.dragons.fantasyauction.integration;

import static com.bytes.and.dragons.fantasyauction.util.TestData.getCreateLotRequest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bytes.and.dragons.fantasyauction.config.PostgresTestContainerConfig;
import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import com.bytes.and.dragons.fantasyauction.model.enums.ItemType;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.repository.LotRepository;
import com.bytes.and.dragons.fantasyauction.security.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = PostgresTestContainerConfig.class)
class LotControllerIntegrationTest {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DataSet(executeScriptsBefore =
            {"scripts/populate_users.sql", "scripts/populate_items.sql", "scripts/populate_lots.sql"},
            executeScriptsAfter = "scripts/clear_schema.sql")
    void getLots_shouldReturnAllLots_whenRequestIsValid() {
        // given
        String token = jwtService.generateToken("bytesanddragonsua", 1L);

        // when
        LotResponse response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", "Bearer " + token)
                        .when()
                        .get("/lots")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(new TypeRef<>() {
                        });

        // then
        assertEquals(1, response.getLots().size());
        assertEquals(1, response.getLots().getFirst().getId());
        assertEquals("test_item", response.getLots().getFirst().getItemName());
        assertEquals(ItemType.OTHER, response.getLots().getFirst().getItemType());
    }

    @Test
    @DataSet(executeScriptsBefore = {"scripts/populate_users.sql",
            "scripts/populate_items.sql"}, executeScriptsAfter = "scripts/clear_schema.sql")
    void createLot_shouldCreateLot_whenRequestIsValid() throws JsonProcessingException {
        // given
        CreateLotRequest createLotRequest = getCreateLotRequest();
        var payload = objectMapper.writeValueAsString(createLotRequest);
        String token = jwtService.generateToken("bytesanddragonsua", 1L);

        // when
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .headers("Authorization", "Bearer " + token)
                .when()
                .post("/lots")
                .then()
                .statusCode(201);

        // then
        Lot lot = lotRepository.findByIdWithItem(1L).get();
        assertEquals(createLotRequest.getItemId(), lot.getItem().getId());
    }
}
