package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:test-configuration.properties"})
//@TestPropertySource(locations = {"classpath:com/example/demo1/test.properties"})
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }

}
