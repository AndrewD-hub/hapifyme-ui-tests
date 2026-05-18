package config;

public class TestConfig {
    public static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");
    public static final String VALID_EMAIL = System.getProperty("testEmail", "dumysipopiytprem@gmail.com");
    public static final String VALID_PASSWORD = System.getProperty("testPassword", "ParolaMeaSecreta123!");
}
