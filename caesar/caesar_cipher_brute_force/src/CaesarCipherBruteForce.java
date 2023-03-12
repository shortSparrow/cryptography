interface Crack {
    void brute(String cipherText);
}

public class CaesarCipherBruteForce implements Crack {
    private final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void brute(String cipherText) {
        // Consider we are working with english alphabet which contains 26 characters + 1 empty space
        for (int key = 0; key < ALPHABET.length(); key++) {
            String plainText = "";

            // Simple Caesar decrypt method
            for (var i = 0; i < cipherText.length(); i++) {
                char character = cipherText.charAt(i);
                int charIndex = ALPHABET.indexOf(character);

                int decryptedIndex = Math.floorMod(charIndex - key, ALPHABET.length());
                plainText += ALPHABET.charAt(decryptedIndex);
            }

            System.out.format("Cracking Caesar cipher with %s key equals %s%n", key, plainText);
        }
    }
}
