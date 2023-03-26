package com.restFormat.format.Service;

import java.util.Map;

public interface ProcessStrategy {

    void process();
    void lineValidate(String[] tokens, String fileType, Map<String, String> requestBody);
}
