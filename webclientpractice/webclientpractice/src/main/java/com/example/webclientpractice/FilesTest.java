package com.example.webclientpractice;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FilesTest {

    private MultipartFile multipartFile;


    public FilesTest() {
    }

    public FilesTest(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
