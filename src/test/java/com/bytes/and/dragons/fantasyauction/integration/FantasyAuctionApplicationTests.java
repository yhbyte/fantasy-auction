package com.bytes.and.dragons.fantasyauction.integration;

import com.bytes.and.dragons.fantasyauction.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class FantasyAuctionApplicationTests {

    @Test
    void contextLoads() {
    }

}
