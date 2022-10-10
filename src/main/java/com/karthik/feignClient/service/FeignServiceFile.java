package com.karthik.feignClient.service;

import com.karthik.feignClient.entity.FileData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "feignServiceFile",
  url = "http://localhost:4040/api/file/v1")
public interface FeignServiceFile {

    @PostMapping(value = "/storeFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileData storeFile(@RequestPart("file") MultipartFile file, @RequestParam("comment") String comment);

    @GetMapping("/viewFile/{id}")
    public byte[] viewFile(@PathVariable("id") Long id);

    @GetMapping("/findFile/{id}")
    public Optional<FileData> findFileById(@PathVariable Long id);

    @GetMapping("/files")
    public List<FileData> getAllFile();

    @DeleteMapping("/delete/{id}")
    public FileData deleteFile(@PathVariable Long id);

}
