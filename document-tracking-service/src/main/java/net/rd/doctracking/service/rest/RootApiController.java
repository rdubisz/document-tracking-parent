package net.rd.doctracking.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String about() {
        log.info("about()");
        return "Example Tasks service";
    }
}
