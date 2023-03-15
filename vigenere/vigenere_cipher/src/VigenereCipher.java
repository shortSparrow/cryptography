
interface Cipher {
    String encrypt(String plainText, String key);

    String decrypt(String cipherText, String key);
}

public class VigenereCipher implements Cipher {
    private final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    // EiXj = (Xi+Kj) mod 26
    public String encrypt(String plainText, String key) {
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();
        String cipherText = "";

        // first way (my own)
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            int keyIndex = Math.floorMod(i, key.length()); // get index of key for current iteration
            char currentKeyChar = key.charAt(keyIndex); // get char for current iteration

            int index = Math.floorMod(ALPHABET.indexOf(c) + ALPHABET.indexOf(currentKeyChar), ALPHABET.length()); // index of encrypted character
            cipherText += ALPHABET.charAt(index);
        }

        // first way (as in video)
//        int keyIndex = 0;
//        for (int i = 0; i < plainText.length(); i++) {
//            char c = plainText.charAt(i);
//            int index = Math.floorMod(ALPHABET.indexOf(c) + ALPHABET.indexOf(key.charAt(keyIndex)), ALPHABET.length());
//            cipherText += ALPHABET.charAt(index);
//            keyIndex++;
//            if(keyIndex == key.length()) {
//                keyIndex = 0;
//            }
//        }

        return cipherText;
    }


    @Override
    // EiXj = (Xi-Kj) mod 26
    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();
        String plainText = "";


        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            int keyIndex = Math.floorMod(i, key.length()); // get index of key for current iteration
            char currentKeyChar = key.charAt(keyIndex); // get char for current iteration

            int index = Math.floorMod(ALPHABET.indexOf(c) - ALPHABET.indexOf(currentKeyChar), ALPHABET.length()); // index of encrypted character
            plainText += ALPHABET.charAt(index);
        }

        return plainText;
    }
}
