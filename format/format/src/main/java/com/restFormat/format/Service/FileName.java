package com.restFormat.format.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileName {
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("fileType")
    private String fileType;

    public FileName(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
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
}
