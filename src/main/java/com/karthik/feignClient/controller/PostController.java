package com.karthik.feignClient.controller;

import com.karthik.feignClient.entity.Post;
import com.karthik.feignClient.service.FeignServicePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feignPost")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private FeignServicePost servicePost;

    @PostMapping("/savePost")
    public ResponseEntity<?> savePost(@RequestBody Post post) throws IOException {

        ResponseEntity<?> postStatus = servicePost.savePost(post);

        return ResponseEntity.status(HttpStatus.OK)
                .body(postStatus);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody Post post) {

        ResponseEntity<?> postDataReceived = servicePost.updateComment(id, post);

        if (postDataReceived == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No data");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(postDataReceived);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllFiles() {
        List<Post> postList;
        try {
            postList = servicePost.getAllPost();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }

    @GetMapping("/findPost/{id}")
    public ResponseEntity<?> findFileById(@PathVariable Long id) {
        ResponseEntity<?> fileReceived = servicePost.findPostById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(fileReceived);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        ResponseEntity<Post> deleteResponse;
        try {
            deleteResponse = servicePost.deletePost(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No Data found for ID :" + id);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Post Delete Successfully..");
    }
}
