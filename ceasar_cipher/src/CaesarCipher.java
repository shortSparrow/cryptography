

interface Cryptography {
    String encrypt(String plainText, int key);

    String decrypt(String cipherText, int key);
}

public class CaesarCipher implements Cryptography {
    // 26 characters + 1 (empty space for spacing between words)
    private final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String encrypt(String plainText, int key) {
        String cipherText = "";
        plainText = plainText.toUpperCase();

        for (var i = 0; i < plainText.length(); i++) {
            char character = plainText.charAt(i);
            int charIndex = ALPHABET.indexOf(character);
            int encryptedIndex = Math.floorMod((charIndex + key), ALPHABET.length());
            cipherText += ALPHABET.charAt(encryptedIndex);
        }

        return cipherText;
    }

    @Override
    public String decrypt(String cipherText, int key) {
        String painText = "";

        for (var i = 0; i < cipherText.length(); i++) {
            char character = cipherText.charAt(i);
            int charIndex = ALPHABET.indexOf(character);
            /**
             * use Math.floorMod because (charIndex - key) % ALPHABET.length() this has a bug. If I want to encrypt 'x' it goes to ' ', and if
             * I decrypt it I will get -3 % ALPHABET.length() = -3. So  ALPHABET.charAt(-3) - error. Index must be 24 not -3
             */

            int decryptedIndex = Math.floorMod(charIndex - key, ALPHABET.length());
            painText += ALPHABET.charAt(decryptedIndex);
        }
        return painText;
    }
}
