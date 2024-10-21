package com.testvagrant.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.testvagrant.payload.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataFunc {
    public ObjectMapper objectMapper = new ObjectMapper();
    Path path = Paths.get(System.getProperty("user.dir"), "testData", "testData.json");
    private JsonNode rootNode;

    public JsonNode getJsonNode(Path path) throws IOException {
        return objectMapper.readTree(new File(path.toString()));
    }

    public String[] getAllData() throws IOException {
        JsonNode rootNode = getJsonNode(path);
        String[] arr = new String[rootNode.size()];
        for(int i=0; i<rootNode.size(); i++) {
            arr[i] = rootNode.get(i).path("username").asText();
        }
        return arr;
    }

    public List<User> getUserObject() throws IOException {
        rootNode = getJsonNode(path);
        List<User> user = new ArrayList<>();
        User userPayload;
        for(int i=0; i<rootNode.size(); i++) {
            userPayload = new User();
            userPayload.setId(Integer.parseInt(rootNode.get(i).get("id").asText()));
            userPayload.setUsername(rootNode.get(i).get("username").asText());
            userPayload.setFirstName(rootNode.get(i).get("firstName").asText());
            userPayload.setLastName(rootNode.get(i).get("lastName").asText());
            userPayload.setEmail(rootNode.get(i).get("email").asText());
            userPayload.setPassword(rootNode.get(i).get("password").asText());
            userPayload.setPhone(rootNode.get(i).get("phone").asText());
            user.add(userPayload);
        }
        return user;
    }

    public User getDataByUserName(String userName) throws IOException {
        List<User> user = getUserObject();
        for(User u:user) {
            if(u.getUsername().equals(userName)) {
                return u;
            }
        }
        return null;
    }
}
