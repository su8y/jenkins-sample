package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;

public interface FileDownloadService {
    void downloadFile(String fileUrl, final OutputStream outputStream) throws IOException, InterruptedException;
}
