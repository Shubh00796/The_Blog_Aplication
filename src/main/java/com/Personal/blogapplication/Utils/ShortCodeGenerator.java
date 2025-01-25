package com.Personal.blogapplication.Utils;

import java.security.SecureRandom;

public class ShortCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8; // Length of the short code
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateShortCode() {
        StringBuilder shortCode = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            shortCode.append(CHARACTERS.charAt(index));
        }
        return shortCode.toString();
    }
}