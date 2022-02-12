package ee.himaster.platform.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.JdbcDatabaseContainer;

@Slf4j
@TestConfiguration
@Profile("it")
public class PlatformIntegrationTestConfig {

    @Bean
    public MySqlDatabaseContainerWrapper mySqlDatabaseContainerWrapper() {
        return new MySqlDatabaseContainerWrapper();
    }

    @Bean
    public DataSource dataSource(MySqlDatabaseContainerWrapper jdbcDatabaseContainerWrapper) {

        JdbcDatabaseContainer jdbcDatabaseContainer = jdbcDatabaseContainerWrapper.getJdbcDatabaseContainer();

        log.info("Will create Datasource: JdbcURL: {}, driver class: {}",
                jdbcDatabaseContainer.getJdbcUrl(),
                jdbcDatabaseContainer.getDriverClassName());

        HikariConfig dsConfig = new HikariConfig();

        dsConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
        dsConfig.setUsername(jdbcDatabaseContainer.getUsername());
        dsConfig.setPassword(jdbcDatabaseContainer.getPassword());
        dsConfig.setDriverClassName(jdbcDatabaseContainer.getDriverClassName());

        return new HikariDataSource(dsConfig);
    }
}
