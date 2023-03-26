package com.microservice.validator.validator.Service;

import com.microservice.validator.validator.Data.fileXSLX;
import com.microservice.validator.validator.Data.fileCSV;
import com.microservice.validator.validator.Data.FileName;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


@Service
public class ValidationService {

    public boolean validateFile(@RequestBody FileName fileName) {
        fileName.setFileType(fileName.getFileType().toUpperCase());
        String[] line;
        if (fileName.getFileType().equalsIgnoreCase("CSV")) {
            try (InputStream inputStream = fileName.getFile().getInputStream();
                 InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                line = reader.readLine().split(",");
                fileCSV fileCSV = new fileCSV(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                ValidationCSV validationCSV = new ValidationCSV(fileCSV);
                return validationCSV.validation();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else if (fileName.getFileType().equalsIgnoreCase("XLSX")) {
            try (InputStream inputStream = fileName.getFile().getInputStream();
                 InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                line = reader.readLine().split(",");
                fileXSLX fileXSLX = new fileXSLX(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13]);
                ValidationXSLX validationXSLX = new ValidationXSLX(fileXSLX);
                return validationXSLX.validation();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}


