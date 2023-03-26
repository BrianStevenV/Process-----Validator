package com.restFormat.format.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessXSLX implements ProcessStrategy {
    private FileName filePath;
    private int validLines = 0;
    private int invalidLines = 0;

    public ProcessXSLX(FileName filePath) {
        this.filePath = filePath;
    }

    @Override
    public void process() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("fileType", filePath.getFileType());
        try (FileInputStream fis = new FileInputStream(filePath.getFilePath())) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
            ExecutorService executor = Executors.newFixedThreadPool(4);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String[] tokens = new String[]{cell.toString()};
                    executor.execute(() -> lineValidate(tokens, filePath.getFileType(), requestBody));
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lineValidate(String[] tokens, String fileType, Map<String, String> requestBody) {
        requestBody.put("tokens", Arrays.toString(tokens));
        requestBody.put("fileType", fileType);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        boolean response = restTemplate.postForObject("http://localhost:8070/validator/xlsx", request, Boolean.class);
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


