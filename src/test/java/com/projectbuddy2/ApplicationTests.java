package com.projectbuddy2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
class ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired


    @Test
    void contextLoads() {
    }

}
