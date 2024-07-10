package com.example.demo;

import com.example.demo.validator.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class CoreController {
    @PostMapping(value = "/trans",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> imageTrans(@RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/error/{name}")
    public ResponseEntity one(@PathVariable(required = false) String name, BindingResult bindingResult) {

        if (name != null) throw new RuntimeException("undefined.name");
        new NameValidator().validate(name, bindingResult);
        System.out.println(bindingResult.getAllErrors().size());
        if (bindingResult.hasErrors()) {
            ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok("success");
    }

}
