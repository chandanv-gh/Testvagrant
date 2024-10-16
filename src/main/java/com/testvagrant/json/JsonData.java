package com.testvagrant.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class JsonData {
    private Map<String, Map<String, String>> data;
    public JsonData(String filePath) {
        try (Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath))) {
            data = new Gson().fromJson(reader, new TypeToken<Map<String, Map<String, String>>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, String>> getData() {
        return this.data;
    }
}
