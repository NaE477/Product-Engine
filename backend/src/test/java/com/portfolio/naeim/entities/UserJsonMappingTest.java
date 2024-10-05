package com.portfolio.naeim.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserJsonMappingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFieldCount() {
        // Arrange
        Class<User> userClass = User.class;

        // Act
        Field[] fields = userClass.getDeclaredFields();

        // Expected field count
        int expectedFieldCount = 4; // Current number of User's fields
                                    // name, username, password, email

        // Assert
        assertEquals(expectedFieldCount, fields.length, "The number of fields in the User entity has changed.");
    }

    // Jackson ObjectMapper for JSON serialization and deserialization
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Example User object to be used in the tests
    private static final User testUser = new User("John", "john_doe", "password123", "john@example.com");

    @Test
    public void serializesToJSON() throws Exception {
        // Read the expected JSON from a fixture file (assuming you have a JSON file with user data)
        testUser.setId(1L);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("fixtures/user.json")) {
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
        // Read the JSON data from a fixture file (user.json)
        testUser.setId(1L);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("fixtures/user.json")) {
            assertThat(inputStream).isNotNull();
            String json = new String(inputStream.readAllBytes());

            // Deserialize the JSON into a User object
            User deserializedUser = MAPPER.readValue(json, User.class);

            // Assert that the deserialized object matches the expected testUser object
            assertThat(deserializedUser).isEqualTo(testUser).usingRecursiveComparison();
        }
    }

    @Test
    void testSerializeToJson() throws JsonProcessingException {
        // Create a User object
        User user = new User("John", "john_doe", "password123", "john@example.com");

        // Serialize the User object to JSON
        String json = objectMapper.writeValueAsString(user);

        // Verify the JSON string contains the expected values
        assertTrue(json.contains("\"name\":\"John\""));
        assertTrue(json.contains("\"username\":\"john_doe\""));
        assertTrue(json.contains("\"password\":\"password123\""));
        assertTrue(json.contains("\"email\":\"john@example.com\""));
    }

    @Test
    void testDeserializeFromJson() throws JsonProcessingException {
        String json = "{\"name\":\"John\",\"username\":\"john_doe\",\"password\":\"password123\",\"email\":\"john@example.com\"}";

        User user = objectMapper.readValue(json, User.class);

        // Verify the User object has the expected values
        assertEquals("John", user.getName());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testPartialJsonMapping() throws JsonProcessingException {
        // Create a JSON string without the password field
        String json = "{\"name\":\"Jane\",\"username\":\"jane_doe\",\"email\":\"jane@example.com\"}";

        // Deserialize the JSON string
        User user = objectMapper.readValue(json, User.class);

        // Verify that missing fields are null
        assertEquals("Jane", user.getName());
        assertEquals("jane_doe", user.getUsername());
        assertNull(user.getPassword());
        assertEquals("jane@example.com", user.getEmail());
    }
}
