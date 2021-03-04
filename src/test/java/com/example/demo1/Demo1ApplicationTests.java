package com.example.demo1;

import com.example.demo1.repository.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.junit.platform.suite.api.SelectPackages;

@SpringBootTest(classes = {Demo1Application.class, JpaConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@WebAppConfiguration

//@SelectPackages({"com.examples.demo1"})
//@TestPropertySource(locations = {"classpath:test-configuration.properties"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1"})
//@TestPropertySource(locations = {"classpath:com/example/demo1"})


public class Demo1ApplicationTests {


    @Test
    void contextLoads() {
    }

}
