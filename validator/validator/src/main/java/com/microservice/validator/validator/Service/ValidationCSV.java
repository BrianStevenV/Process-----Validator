package com.microservice.validator.validator.Service;

import com.microservice.validator.validator.Data.fileCSV;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCSV implements ValidatorStrategy{
    private fileCSV fileCSV;
    public ValidationCSV(fileCSV fileCSV) {
        this.fileCSV = fileCSV;
    }
//    @Override
//    public boolean validation() {
//        if(validationEmail(fileCSV.getEmail()) || validationJob(fileCSV.getJobTitle()) || validateDateOfBirth(fileCSV.getDateOfBirth())){
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean validation() {
        if(validationEmail(fileCSV.getEmail()) || validationJob(fileCSV.getJobTitle()) || validateDateOfBirth(fileCSV.getDateOfBirth())){
            return true;
        }
        return false;
    }

    public boolean validationEmail(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
//    public boolean validationJob(String job) {
//        switch (fileCSV.getJobTitle()) {
//            case "Haematologist":
//            case "Phytotherapist":
//            case "Building surveyor":
//            case "Insurance account manager":
//            case "Educational psychologist":
//                return true;
//            default:
//                System.out.println("Error switch validation job");
//                return false;
//        }
//    }

    public boolean validationJob(String job) {
        switch (job) {
            case "Haematologist":
            case "Phytotherapist":
            case "Building surveyor":
            case "Insurance account manager":
            case "Educational psychologist":
                return true;
            default:
                System.out.println("Error switch validation job");
                return false;
        }
    }

    public boolean validateDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        LocalDate referenceDate = LocalDate.of(1980, 1, 1);
        return dob.isAfter(referenceDate);
    }
}
