package com.microservice.validator.validator.Service;

import com.microservice.validator.validator.Data.fileXSLX;

public class ValidationXSLX implements ValidatorStrategy {
    private fileXSLX fileXSLX;
    private int lineValid = 0;
    private int lineInvalid = 0;

    public ValidationXSLX(fileXSLX fileXSLX) {
        this.fileXSLX = fileXSLX;
    }

    @Override
    public boolean validation() {
        // Validar que el Injury Location sea diferente de "N/A" y el Report Type sea uno de los valores aceptados
        if (!fileXSLX.getInjuryLocation().equals("N/A") &&
                (fileXSLX.getReportType().equals("Near Miss") ||
                        fileXSLX.getReportType().equals("Lost Time") ||
                        fileXSLX.getReportType().equals("First Aid"))) {
            lineValid++;
            return true; // la validación es exitosa
        } else {
            lineInvalid++;
            return false; // la validación falla
        }
    }

    public int getLineValid() {
        return lineValid;
    }

    public int getLineInvalid() {
        return lineInvalid;
    }
}
