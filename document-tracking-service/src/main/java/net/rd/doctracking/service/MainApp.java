package net.rd.doctracking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service entry point
 */
@SpringBootApplication
public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    static void main(final String[] args) {
        log.info("Backend MainApp starting");

        SpringApplication.run(MainApp.class, args);
    }
}
