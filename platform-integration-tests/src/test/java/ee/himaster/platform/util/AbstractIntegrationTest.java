package ee.himaster.platform.util;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;
import ee.himaster.platform.web.application.configuration.PlatformApplicationConfiguration;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest(
        classes = {
                PlatformIntegrationTestConfig.class,
                PlatformApplicationConfiguration.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("it")
@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {
}
