package com.mikhaildolgopolov.spring.controllers;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("minio/")
public class MinioController {
    @Autowired
    private MinioService minioService;

    @GetMapping
    public List<Item> testMinio() throws MinioException {
        return minioService.list();
    }
}
