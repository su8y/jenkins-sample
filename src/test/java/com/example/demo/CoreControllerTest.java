package com.example.demo;

import com.example.demo.validator.NameValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoreController.class)
class CoreControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ResourceLoader resourceLoader;


    @Test
    void imageTrans() throws Exception {

        File file = ResourceUtils.getFile("classpath:img.png");
        MultipartFile multipartFile = new MockMultipartFile(
                "img.png",
                new FileInputStream(file)
        );
        mvc.perform(MockMvcRequestBuilders.multipart("/trans")
                        .file("image", multipartFile.getBytes()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("SUCC")));
    }

    @Test
    @DisplayName("실패테스트")
    void imageTrans2() throws Exception {
        String name = "mastera1231230";

        mvc.perform(MockMvcRequestBuilders.post(String.format("/error/%s", name))
                ).andDo(MockMvcResultHandlers.print());

    }
}