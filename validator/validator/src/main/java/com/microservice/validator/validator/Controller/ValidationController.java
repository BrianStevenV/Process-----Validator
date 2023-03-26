package com.microservice.validator.validator.Controller;

import com.microservice.validator.validator.Data.FileName;
import com.microservice.validator.validator.Data.fileXSLX;
import com.microservice.validator.validator.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/validator")
public class ValidationController {
    @Autowired
    private ValidationService validationService;
//    @PostMapping("/csv")
//    public ResponseEntity<String> processValidationArchivesCSV(@RequestBody FileName fileName){
//         return validationService.validateFile(fileName);
//    }
//    @PostMapping("/xlsx")
//    public ResponseEntity<String> processValidationArchivesXSLX(@RequestBody FileName fileName){ return validationService.validateFile(fileName);}

    @PostMapping("/csv")
    public ResponseEntity<String> processValidationArchivesCSV(@RequestBody FileName fileName){
        boolean result = validationService.validateFile(fileName);
        return ResponseEntity.ok().body(String.valueOf(result));
    }

    @PostMapping("/xlsx")
    public ResponseEntity<String> processValidationArchivesXSLX(@RequestBody FileName fileName) {
        boolean result = validationService.validateFile(fileName);
        return ResponseEntity.ok().body(String.valueOf(result));
    }

//    @PostMapping("/csv")
//    public ResponseEntity<String> processValidationArchivesCSV(@RequestBody FileName fileName){
//        boolean result = validationService.validateFile(fileName);
//        return ResponseEntity.ok().body("Valid lines: " + validationService.getLineValid() + ", Invalid lines: " + validationService.getLineInvalid() + ", Result: " + String.valueOf(result));
//    }
//
//    @PostMapping("/xlsx")
//    public ResponseEntity<String> processValidationArchivesXSLX(@RequestBody FileName fileName) {
//        boolean result = validationService.validateFile(fileName);
//        return ResponseEntity.ok().body("Valid lines: " + validationService.getLineValid() + ", Invalid lines: " + validationService.getLineInvalid() + ", Result: " + String.valueOf(result));
//    }

}
