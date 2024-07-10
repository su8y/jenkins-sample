package com.example.demo;

import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class OutputStreamFileDownloadService implements FileDownloadService {
    @Override
    public void downloadFile(String fileUrl, OutputStream outputStream) throws IOException, InterruptedException {
        Optional<File> optionalFile = validFile(fileUrl);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException();
        }
        File file = optionalFile.get();

        writeFile(file, outputStream);
    }


    private void writeFile(File file, OutputStream outputStream) throws IOException, InterruptedException {
        byte[] b = new byte[1024];

        var fileInputStream = new FileInputStream(file);
        var inputStream = new BufferedInputStream(fileInputStream);

        int len;
        while ((len = inputStream.read(b, 0, 1024)) != -1) {
            outputStream.write(b, 0, len);
            outputStream.flush();
            Thread.sleep(100);
        }

        inputStream.close();
        fileInputStream.close();
        outputStream.close();
    }

    private Optional<File> validFile(String fileUrl) {
        Path path = Path.of(fileUrl);
        File file = path.toFile();

        if (file.exists() && file.isFile()) {
            return Optional.of(file);
        }
        return Optional.empty();
    }
}
