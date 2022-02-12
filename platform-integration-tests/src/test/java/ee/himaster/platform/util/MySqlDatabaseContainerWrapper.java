package ee.himaster.platform.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.MySQLContainer;

@Slf4j
public class MySqlDatabaseContainerWrapper {
    private static final String DOCKER_IMAGE = "mysql:8.0.27";

    @Getter
    private final MySQLContainer jdbcDatabaseContainer = new MySQLContainer(DOCKER_IMAGE);

    @PostConstruct
    void start() {
        log.info("Starting MYSQL Docker test container");
        jdbcDatabaseContainer.start();
        log.info("Started MYSQL Docker test container");
    }

    @PreDestroy
    void stop() {
        log.info("Stopping MYSQL Docker test container");
        jdbcDatabaseContainer.stop();
        log.info("Stopped MYSQL Docker test container");
    }
}
