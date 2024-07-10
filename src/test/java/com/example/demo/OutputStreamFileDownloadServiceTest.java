package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OutputStreamFileDownloadServiceTest {
    private final OutputStreamFileDownloadService service;

    OutputStreamFileDownloadServiceTest() {
        this.service = new OutputStreamFileDownloadService();
    }

    @Test
    void writeFileTest() throws IOException {
        Path path = Path.of("C:\\Users\\user\\workspace\\demo\\src\\main\\resources\\static\\file.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        service.downloadFile("C:\\Users\\user\\Downloads\\test.mp4", fileOutputStream);
    }

    @Test
    void _FindFileTest() {
        Optional<File> file = ReflectionTestUtils.invokeMethod(service, "validFile", "C:\\Users\\user\\Downloads\\test.mp4");
        Assertions.assertThat(file.isEmpty()).isFalse();

    }

    @Test
    void _fileNotFoundTest() {
        Optional<File> file = ReflectionTestUtils.invokeMethod(service, "validFile", "C:\\Users\\user\\Downloads\\error-123asd.mp4");

        Assertions.assertThat(file.isEmpty()).isTrue();

    }

    @Test
    void 리소스테스트() throws IOException {

        Resource resource = new ClassPathResource("static/file.mp4");
        File file = resource.getFile();
        System.out.println("getPath() = " + file.getPath());
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
        System.out.println("file.getParent() = " + file.getParent());
        System.out.println("resource.getURI() = " + resource.getURI());
        System.out.println("resource.getURL() = " + resource.getURL());
    }
}