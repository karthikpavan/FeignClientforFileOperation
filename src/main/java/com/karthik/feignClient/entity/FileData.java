package com.karthik.feignClient.entity;

import lombok.Data;

import java.util.Arrays;

@Data
public class FileData {
    private Long file_id;

    private String fileName;

    private String fileType;

    private byte[] fileContent;

    private Post post;

    @Override
    public String toString() {
        return "FileData{" +
                "file_id=" + file_id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileContent=" + Arrays.toString(fileContent) +
                ", post=" + post +
                '}';
    }
}
