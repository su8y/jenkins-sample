package com.example.be.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
class HelloController {
    @GetMapping("/{username}")
    public Message getHello(@PathVariable String username) {
        return new Message("안녕하십니까? " + username);
    }

}