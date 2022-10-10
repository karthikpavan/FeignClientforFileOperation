package com.karthik.feignClient.entity;

import lombok.Data;

@Data
public class Post {

    private long post_id;

    private String comment;

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
