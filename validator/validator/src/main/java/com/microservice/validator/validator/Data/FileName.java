package com.microservice.validator.validator.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URLConnection;

public class FileName {
//    @JsonProperty("filePath")
//    private String filePath;
//    @JsonProperty("fileType")
//    private String fileType;
//
//    public FileName(String filePath, String fileType) {
//        this.filePath = filePath;
//        this.fileType = fileType;
//    }
//
//    public FileName(){};
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public String getFileType(){ return fileType;}
//    public void setFileType(String fileType){ this.fileType = fileType;}

    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("fileType")
    private String fileType;
    private String content; //Esta variable no tiene ningun significado, intente hacer algo y no funciono

    public FileName(String filePath, String fileType, String content) {
        this.filePath = filePath;
        this.fileType = fileType;
        this.content = content;
    }

    public FileName(){};

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType(){ return fileType;}
    public void setFileType(String fileType){ this.fileType = fileType;}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URLConnection getFile() {
        return null;
    }
}
