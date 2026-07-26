package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataDriven {

    public static JsonNode jsonReader() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/testData/testData.json");

        return objectMapper.readTree(file);
    }
}