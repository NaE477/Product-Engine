package com.portfolio.naeim.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.naeim.dto.UserRegisterRequest;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRegisterRequestJsonMappingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFieldCount() {
        // Arrange
        Class<UserRegisterRequest> userClass = UserRegisterRequest.class;

        // Act
        Field[] fields = userClass.getDeclaredFields();

        // Expected field count
        int expectedFieldCount = 5; // name, userName, email, password, confirmPassword

        // Assert
        assertEquals(expectedFieldCount, fields.length, "The number of fields in the UserRegisterRequest has changed.");
    }

    // Jackson ObjectMapper for JSON serialization and deserialization
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Example UserRegisterRequest object to be used in the tests
    private static final UserRegisterRequest testUser = new UserRegisterRequest(
            "John", "john_doe", "john@example.com", "password123", "password123"
    );

    @Test
    public void serializesToJSON() throws Exception {
        // Read the expected JSON from a fixture file (assuming you have a JSON file with user data)
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("fixtures/userRegisterRequest.json")) {
            assertThat(inputStream).isNotNull();
            String expectedJson = new String(inputStream.readAllBytes());

            // Serialize the testUser object to JSON
            String actualJson = MAPPER.writeValueAsString(testUser);

            // Assert that the serialized JSON matches the expected JSON
            assertThat(actualJson).isEqualToIgnoringWhitespace(expectedJson);
        }
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        // Read the JSON data from a fixture file (userRegisterRequest.json)
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("fixtures/userRegisterRequest.json")) {
            assertThat(inputStream).isNotNull();
            String expectedJson = new String(inputStream.readAllBytes());
            // Deserialize the JSON into a UserRegisterRequest object
            UserRegisterRequest deserializedUser = MAPPER.readValue(expectedJson, UserRegisterRequest.class);

            // Assert that the deserialized object matches the expected testUser object
            assertThat(deserializedUser).isEqualTo(testUser).usingRecursiveComparison();
        }
    }

    @Test
    void testSerializeToJson() throws JsonProcessingException {
        // Create a UserRegisterRequest object
        UserRegisterRequest user = new UserRegisterRequest(
                "John", "john_doe", "john@example.com", "password123", "password123"
        );

        // Serialize the UserRegisterRequest object to JSON
        String json = objectMapper.writeValueAsString(user);

        // Verify the JSON string contains the expected values
        assertTrue(json.contains("\"name\":\"John\""));
        assertTrue(json.contains("\"userName\":\"john_doe\""));
        assertTrue(json.contains("\"email\":\"john@example.com\""));
        assertTrue(json.contains("\"password\":\"password123\""));
        assertTrue(json.contains("\"confirmPassword\":\"password123\""));
    }

    @Test
    void testDeserializeFromJson() throws JsonProcessingException {
        String json = "{\"name\":\"John\",\"userName\":\"john_doe\",\"email\":\"john@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password123\"}";

        UserRegisterRequest user = objectMapper.readValue(json, UserRegisterRequest.class);

        // Verify the UserRegisterRequest object has the expected values
        assertEquals("John", user.getName());
        assertEquals("john_doe", user.getUserName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("password123", user.getConfirmPassword());
    }

    @Test
    void testPartialJsonMapping() throws JsonProcessingException {
        // Create a JSON string without the confirmPassword field
        String json = "{\"name\":\"Jane\",\"userName\":\"jane_doe\",\"email\":\"jane@example.com\",\"password\":\"password123\"}";

        // Deserialize the JSON string
        UserRegisterRequest user = objectMapper.readValue(json, UserRegisterRequest.class);

        // Verify that missing fields are null
        assertEquals("Jane", user.getName());
        assertEquals("jane_doe", user.getUserName());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertNull(user.getConfirmPassword());
    }
}
