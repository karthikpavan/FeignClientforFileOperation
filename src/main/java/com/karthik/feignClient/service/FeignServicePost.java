package com.karthik.feignClient.service;

import com.karthik.feignClient.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "feignServicePost", url = "http://localhost:4040/api/post/v1")
public interface FeignServicePost {

    @PostMapping("/savePost")
    public ResponseEntity<?> savePost(@RequestBody Post post);

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody Post post);

    @GetMapping("/posts")
    public List<Post> getAllPost();

    @GetMapping("/findPost/{id}")
    public ResponseEntity<?> findPostById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id);


}
