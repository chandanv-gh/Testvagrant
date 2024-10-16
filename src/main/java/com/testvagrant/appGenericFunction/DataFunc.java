package com.testvagrant.appGenericFunction;

import com.testvagrant.json.JsonReader;

import java.util.HashMap;
import java.util.Map;

public class DataFunc {

    public String testDataFile = "Data/testData.json";
    JsonReader json = new JsonReader(testDataFile);
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
}
