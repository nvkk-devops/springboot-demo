package com.venkata.springboot.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/")
public class CommonResource {

    // metrics type will be reported as '# TYPE venkata_hello_'
    @Timed( 
        value = "venkata.hello", // prometheus reports as '# TYPE venkata_hello_'
        histogram = true,
        percentiles = {0.95, 0.99},
        extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        String returnVal = "Hello ! " + System.currentTimeMillis();
        return new ResponseEntity<>(returnVal, HttpStatus.OK);
    }

    // metrics type will be reported as '# TYPE http_server_requests'
    @GetMapping("/welcome")
    public ResponseEntity<Object> welcome() {
        String returnVal = "welcome ! " + System.currentTimeMillis();
        return new ResponseEntity<>(returnVal, HttpStatus.OK);
    }

}
