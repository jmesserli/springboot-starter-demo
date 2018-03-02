package nu.peg.springboot.stringgen.internal;

import nu.peg.springboot.stringgen.StringGenerator;

import java.util.Random;

public class CharacterArrayBasedStringGenerator implements StringGenerator {
    private final Random random = new Random();

    private char[] availableCharacters;

    public CharacterArrayBasedStringGenerator(char[] availableCharacters) {
        if (availableCharacters == null || availableCharacters.length == 0) {
            throw new IllegalArgumentException("Available characters must be non-null and contain more than 0 entries");
        }
        this.availableCharacters = availableCharacters;
    }

    @Override
    public String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(availableCharacters[random.nextInt(availableCharacters.length)]);
        }
        return sb.toString();
    }
}
