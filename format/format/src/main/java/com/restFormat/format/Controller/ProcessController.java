package com.restFormat.format.Controller;

//import com.restFormat.format.Service.File;

import com.restFormat.format.Module.LineResponse;
import com.restFormat.format.Service.FileName;
import com.restFormat.format.Service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @PostMapping("/file/process")
    public ResponseEntity<LineResponse> ProcessFile(@RequestBody FileName filePath) {
        LineResponse response = processService.typeFormat(filePath);
        return ResponseEntity.ok().body(response);
    }
}
