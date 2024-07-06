package com.example.be.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
class HelloController {
    @GetMapping("/{username}")
    public ResponseEntity<Message> getHello(@PathVariable String username) {
        Message body = new Message("안녕하십니까? " + username);

        return ResponseEntity.ok(body);
    }
}