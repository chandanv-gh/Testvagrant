package com.testvagrant.appGenericFunction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFunc {

    public String jsonFile = System.getProperty("user.dir")+"/src/main/resources/Data/testData.json";
    public ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode rootNode;

    public DataFunc() {
        try {
            // Load JSON from file
            rootNode = objectMapper.readTree(new File(jsonFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getXpath(String key) {
        String x = rootNode.path("xpath").path(key).asText();
        return x;
    }

    public String getTestData(String key) {
        return rootNode.path("registration").path(key).asText();
    }

    public String getAppUrl() {
        return rootNode.path("common_properties").path("url").asText();
    }

    public List<String> getList(String key) {
        List<String> node = new ArrayList<>();
        for(JsonNode i:rootNode.path(key)) {
            node.add(i.asText());
        }
        return node;
    }

    public String generateRandomText(int size) {
        StringBuilder sb = new StringBuilder(size);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public void updateJsonFile(String object, String key, String value) {
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(jsonFile))) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject registrationObject = jsonObject.getAsJsonObject(object);

            // Add a new key-value pair
            registrationObject.addProperty(key, value);

            // Write the updated JSON back to the file
            try (FileWriter writer = new FileWriter(jsonFile)) {
                gson.toJson(jsonObject, writer);
                System.out.println("Key-value pair added successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
