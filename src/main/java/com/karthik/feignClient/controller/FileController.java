package com.karthik.feignClient.controller;

import com.karthik.feignClient.entity.FileData;
import com.karthik.feignClient.service.FeignServiceFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feignFile")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FeignServiceFile serviceFile;

    @PostMapping(value = "/storeFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> storeFile(@RequestPart("file") MultipartFile file, @RequestParam("comment") String comment) throws IOException {
        FileData fileStatus;
        try {
            fileStatus = serviceFile.storeFile(file, comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("File saved successfully :" + fileStatus.getFileName());
    }

    @GetMapping("/viewFile/{id}")
    public ResponseEntity<?> openFileContent(@PathVariable("id") Long id) throws Exception {
        logger.info("--> openFileContent with id : -->" + id);

            byte[] fileContent = serviceFile.viewFile(id);

            if(fileContent == null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("application/pdf"))
                        .body("No File to view for ID : "+id);
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("application/pdf"))
                        .body(fileContent);
            }
    }

    @GetMapping("/findFile/{id}")
    public ResponseEntity<?> findFileById(@PathVariable Long id) {
        logger.info("--> findFile with id : -->" + id);

        FileData fileReceived = serviceFile.findFileById(id).get();

        if (fileReceived == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No File with ID :" + id);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fileReceived);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<?> getAllFiles() {
        logger.info("--> getALl Files  -->");
        List<FileData> fileList = serviceFile.getAllFile();

        if (fileList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No Files Stored to view!!");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fileList);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        FileData deleteResponse = serviceFile.deleteFile(id);

        if (deleteResponse == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No data found !!");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("File Delete Successfully..");
        }
    }
}
