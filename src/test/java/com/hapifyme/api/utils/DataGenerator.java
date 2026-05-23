package com.hapifyme.api.utils;

public class DataGenerator {

    public static String generateUniqueEmail() {
        return "test_user_" + System.currentTimeMillis() + "@example.com";
    }

    public static String generateFirstName() {
        return "Dumi";
    }

    public static String generateLastName() {
        return "Tester";
    }

    public static String generatePassword() {
        return "Test1234!";
    }
}