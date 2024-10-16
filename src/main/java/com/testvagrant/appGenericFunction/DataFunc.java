package com.testvagrant.appGenericFunction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.testvagrant.json.JsonData;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataFunc {

    public String testDataFile = "Data/testData.json";
    JsonData json = new JsonData(testDataFile);
    Map<String, Map<String, String>> data = json.getData();

    public String getXpath(String key) {
        String x = data.get("xpath").get(key);
        return x;
    }

    public String getTestData(String key) {
        return data.get("registration").get(key);
    }

    public String getAppUrl() {
        return data.get("common_properties").get("url");
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
        String jsonFile = System.getProperty("user.dir")+"/src/main/resources/Data/testData.json";

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
