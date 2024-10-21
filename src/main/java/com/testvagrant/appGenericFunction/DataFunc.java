package com.testvagrant.appGenericFunction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataFunc {

    Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Data", "testData.json");
    public String jsonFile = path.toString();
    public ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode rootNode;

    public DataFunc() {
        try {
            // Load JSON from file
            rootNode = objectMapper.readTree(new File(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public By getXpath(String key) {
        return By.xpath(rootNode.path("xpath").path(key).asText());
    }

    public String getTestData(String key) {
        return rootNode.path("registration").path(key).asText();
    }

    public String getAppUrl(String app) {
        return rootNode.path("common_properties").path(app).asText();
    }

    public List<String> getList(String key) {
        List<String> node = new ArrayList<>();
        for(JsonNode i:rootNode.path(key)) {
            node.add(i.asText());
        }
        return node;
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
