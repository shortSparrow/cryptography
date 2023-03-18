
interface Cipher {
    String encrypt(String plainText, int[] key);

    String decrypt(String cipherText, int[] key);
}

public class OneTimePad implements Cipher {

    @Override
    public String encrypt(String plainText, int[] key) {
        plainText = plainText.toUpperCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            int keyIndex = key[i];
            int characterIndex = Constants.ALPHABET.indexOf(plainText.charAt(i));

//            encryptedLetter = (characterIndex + randomShiftIndex) mod 27
//             Ei(Xi) = (Xi + OTPi) mod 26
            cipherText += Constants.ALPHABET.charAt(Math.floorMod(characterIndex + keyIndex, Constants.ALPHABET.length()));
        }
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText, int[] key) {
        cipherText = cipherText.toUpperCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int keyIndex = key[i];
            int characterIndex = Constants.ALPHABET.indexOf(cipherText.charAt(i));

//            encryptedLetter = (characterIndex - randomShiftIndex) mod 27
//             Ei(Xi) = (Xi - OTPi) mod 26
            plainText += Constants.ALPHABET.charAt(Math.floorMod(characterIndex - keyIndex, Constants.ALPHABET.length()));
        }
        return plainText;
    }
}
