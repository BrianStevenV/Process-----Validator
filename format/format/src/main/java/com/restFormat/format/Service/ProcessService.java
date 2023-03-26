package com.restFormat.format.Service;

import com.restFormat.format.Module.LineResponse;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {
    //Missing other type of validation to choose if csv or xlsx.
    private FileName filePath;


    public LineResponse typeFormat(FileName fileName){
        int validLines = 0;
        int invalidLines = 0;
        String fileType = fileName.getFileType();
        if("CSV".equals(fileType)){
            ProcessCSV processCSV = new ProcessCSV(fileName);
            processCSV.process();
            validLines += processCSV.getValidLines();
            invalidLines += processCSV.getInvalidLines();
        } else if("XLSX".equals(fileType)){
            ProcessXSLX processXSLX = new ProcessXSLX(fileName);
            processXSLX.process();
            validLines += processXSLX.getValidLines();
            invalidLines += processXSLX.getInvalidLines();
        } else {
            throw new IllegalArgumentException("Invalid file type.");
        }
        return new LineResponse(validLines, invalidLines);
    }

}
