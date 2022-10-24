package com.example.demospring.fileupload.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

public class FileUploadResourceTests {

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new FileUploadResource()).build();
    }

    @Test
    void fileUpload() throws Exception {
        var mockMultipartFile = new MockMultipartFile("file", "hello-world.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(multipart("/fileupload").file(mockMultipartFile));
    }
}
