package com.restFormat.format.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import java.util.Map;

public class ProcessCSV implements ProcessStrategy{
    private FileName filePath;
    private String delimeter = ",";
    private int validLines = 0;
    private int invalidLines = 0;
    public ProcessCSV(FileName filePath){
        this.filePath = filePath;
    }

    @Override
    public void process() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("fileType", filePath.getFileType());
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, delimeter);
                String[] tokens = new String[tokenizer.countTokens()];
                int i = 0;
                while (tokenizer.hasMoreTokens()) {
                    String field = tokenizer.nextToken();
                    tokens[i++] = field;
                    System.out.print(field + "\t");
                }
                lineValidate(tokens, filePath.getFileType(), requestBody);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lineValidate(String[] tokens, String fileType, Map<String, String> requestBody){
        requestBody.put("tokens", Arrays.toString(tokens));
        requestBody.put("fileType", fileType);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        boolean response = restTemplate.postForObject("http://localhost:8070/validator/csv", request, Boolean.class);
        if (response) {
            validLines++;
        } else {
            invalidLines++;
        }
    }


    public int getValidLines() {
        return validLines;
    }

    public void setValidLines(int validLines) {
        this.validLines = validLines;
    }

    public int getInvalidLines() {
        return invalidLines;
    }

    public void setInvalidLines(int invalidLines) {
        this.invalidLines = invalidLines;
    }
}


