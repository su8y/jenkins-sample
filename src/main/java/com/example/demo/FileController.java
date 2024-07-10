package com.example.demo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileDownloadService fileDownloadService;
    private final String mockFileUrl = "C:\\Users\\user\\Downloads\\test.mp4";

    public FileController(FileDownloadService outputStreamFileDownloadService) {
        this.fileDownloadService = outputStreamFileDownloadService;
    }

    @GetMapping
    public void writeOutputStreamDownload(
            HttpServletResponse response) throws IOException {
        ContentDisposition attachment = ContentDisposition.builder("attachment")
                .filename("video.mp4", Charset.forName("UTF-8"))
                .build();
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, attachment.toString());
        response.setHeader(HttpHeaders.TRANSFER_ENCODING, "chunked");

        ServletOutputStream outputStream = response.getOutputStream();

        try {
            fileDownloadService.downloadFile(mockFileUrl, outputStream);

        } catch (AsyncRequestNotUsableException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/2")
    public ResponseEntity ResponseEntityDownload() throws IOException {
        // find File
        Resource resource = new FileSystemResource(mockFileUrl);
        if (!resource.exists()) return ResponseEntity.noContent().build();

        FileInputStream fileInputStream = new FileInputStream(resource.getFile());
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);

        StreamingResponseBody responseBody = outputStream -> {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.flush();
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition attachment = ContentDisposition.builder("attachment")
                .filename("video.mp4", Charset.forName("UTF-8"))
                .build();
        headers.setContentDisposition(attachment);
        headers.setContentLength(resource.contentLength());

        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
//                .body(new InputStreamResource(resource.getInputStream()));
    }
}
