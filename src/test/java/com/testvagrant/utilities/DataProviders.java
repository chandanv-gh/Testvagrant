package com.testvagrant.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    Path path = Paths.get(System.getProperty("user.dir"), "testData", "testData.json");
    DataFunc df = new DataFunc();
    JsonNode rootNode;

    public DataProviders() throws IOException {
        rootNode = df.getJsonNode(path);
    }

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException {
        String[][] arr = new String[rootNode.size()][rootNode.get(0).size()];
        for(int i=0; i<rootNode.size(); i++) {
            int j = 0;
            Iterator<JsonNode> l = rootNode.get(i).elements();
            while ((l.hasNext())) {
                String key = l.next().asText();
                arr[i][j] = key;
                j++;
            }
        }
        return arr;
    }

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException {
        String[] arr = new String[rootNode.size()];
        for(int i=0; i<rootNode.size(); i++) {
            arr[i] = rootNode.get(i).path("username").asText();
        }
        return arr;
    }
}
