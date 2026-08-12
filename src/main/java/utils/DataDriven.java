package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDriven {

    public static JsonNode jsonReader() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/testData/testData.json");

        return objectMapper.readTree(file);
    }

    public static List<String> getCartProducts() {
        try {
            JsonNode root = jsonReader();
            List<String> products = new ArrayList<>();

            for (JsonNode product : root.get("cartProducts")) {
                products.add(product.asText());
            }

            return products;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read cartProducts from testData.json", e);
        }
    }
}