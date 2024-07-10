package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(FileController.class)
@AutoConfigureMockMvc(addFilters = false)
class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OutputStreamFileDownloadService outputStreamFileDownloadService;

    @Test
    void fileDownloadTest() throws Exception {
        Resource resource = new ClassPathResource("/static/file.mp4");
        MvcResult mvcResult = mockMvc.perform(get("/file/2")
                .contentType("application/octet-stream")
        ).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        byte[] bytes = response.getContentAsString().getBytes();

//        Assertions.assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        Assertions.assertThat(resource.getContentAsByteArray()).isEqualTo(bytes);
    }

}