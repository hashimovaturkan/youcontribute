package com.example.youcontribute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = RepositoryServiceTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
public class RepositoryServiceTestIT {

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_57_IMAGE);

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url="+ mysql.getJdbcUrl(),
                    "spring.datasource.username="+mysql.getUsername(),
                    "spring.datasource.password="+mysql.getPassword())
                    .applyTo(applicationContext.getEnvironment());


        }
    }

    @Test
    public void it_should_find_all_repositories() throws Exception{

    }

}