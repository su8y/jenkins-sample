package com.example.be.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello/{username}")
	public Message getHello(@PathVariable(name = "username") String username) {
		return new Message("안녕하십니까? " + username);
	}

}
