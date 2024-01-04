package io.audit.audit_sec.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import io.audit.audit_sec.domain.GenericEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/SCF")
public class FrameworkController {

    private final MongoTemplate mongoTemplate;

    Map<String, Integer> keyValues = new HashMap<String, Integer>() {{
        put("PCIDSS", 77);
        put("COSO", 23);
        put("COBIT", 22);
        put("ISO 22301 2019", 29);
        put("ISO 27001 2022", 31);
        put("ISO 27002 2022", 31);
        put("ISO 27701 v2019", 36);
        put("MITRE ATT&CK", 40);
        put("NIST CSF v2.0", 74);
        put("OWASP", 75);
    }};

    private int getValueForKey(String key) {
        return keyValues.getOrDefault(key, -1); // Default value -1 if key is not found
    }

    @Autowired
    public FrameworkController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }



    @GetMapping("/controls")
    public String getAllControlsForStandard(@RequestParam List<String> keysList) {
        // Fetch data from MongoDB collection
        List<GenericEntity> entities = mongoTemplate.findAll(GenericEntity.class, "GenericEntity");

        // Ensure there are at least 3 entities
        if (entities.size() > 2) {
            // Extract "jsonDocument" from the third entity
            org.bson.Document bsonDocument = entities.get(2).getJsonDocument();

            // Check if "jsonDocument" is not null
            if (bsonDocument != null) {
                // Convert "jsonDocument" to a JSON string
                String jsonString = bsonDocument.toJson();

                // Parse the JSON string to a Jackson JsonNode
                JsonNode jsonNode = parseJsonString(jsonString);

                // Get the value for each key dynamically
                List<Map<String, String>> resultList = new ArrayList<>();
                int i = 0;
                for (String key : keysList) {
                    int dynamicValue = getValueForKey(key);
                    if (dynamicValue != -1) {
                        JsonNode dataArray = jsonNode.get("data");
                        String resp = "null";
                        i = 0;
                        if (dataArray != null && dataArray.isArray()) {
                            // Iterate over each element in the "data" array
                            for (JsonNode element : dataArray) {
                                i++;
                                // Check if the position x is not null (replace 75 with the actual position you are interested in)
                                JsonNode positionX = element.get(75);
                                if (!Objects.equals(positionX.toString(), "null") && i != 1) {
                                    resp = resp + "     /n Position x is not null: " + "i = " + i;

                                    // Extract values for row, domain, control, Question, and Standard
                                    String row = Integer.toString(i);
                                    String domain = element.get(0).asText();
                                    String control = element.get(1).asText();
                                    String question = element.get(6).asText();
                                    String standard = key; // Assuming there's a "Standard" field

                                    // Create a map for the current element
                                    Map<String, String> elementMap = new HashMap<>();
                                    elementMap.put("Standard", standard);
                                    elementMap.put("row", row);
                                    elementMap.put("domain", domain);
                                    elementMap.put("control", control);
                                    elementMap.put("Question", question);

                                    // Add the map to the result list
                                    resultList.add(elementMap);
                                }
                            }
                        } else {
                            return "Data array is null or not an array.";
                        }
                    }
                }

                // Return the JSON string for testing purposes
                return resultList.toString();
            }
        }
        return "UNKNOWN STANDARD";
    }

    private JsonNode parseJsonString(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception as needed
            return NullNode.getInstance(); // Return an empty node in case of an error
        }
    }
}
