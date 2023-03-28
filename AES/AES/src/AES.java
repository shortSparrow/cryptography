import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    private SecretKey secretKey;
    private SecureRandom secureRandom;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParameterSpec;

    public AES() {
        secureRandom = new SecureRandom();
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
            encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // IV - 1 - 2 - 3 - 4 (XOR)
            byte[] initializationVector = new byte[encryptCipher.getBlockSize()]; // generate empty bytes array with specific length
            secureRandom.nextBytes(initializationVector); // put the next byte in the array
            ivParameterSpec = new IvParameterSpec(initializationVector);

            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String plainText) {
        byte[] bytes = plainText.getBytes();
        byte[] cipherText;

        try {
            cipherText = encryptCipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public String decrypt(String cipherText) {
        byte[] plainText;
        try {
            plainText = decryptCipher.doFinal(Base64.getDecoder().decode(cipherText.getBytes()));
            return new String(plainText, StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
