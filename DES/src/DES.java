import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class DES {
    private SecretKey secretKey;
    private SecureRandom random;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParams;

    public DES() {
        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
            random = new SecureRandom(); // avoid use Random, for cryptography SecureRandom is better
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // DES - algorithm name
            // CBC (Cipher Block Chaining) name of the method (we use CBC implementation. Means that current block is depended on previous block)
            // PKCS5Padding - padding method

            // CBC means current block depends on previous, so for creating first block we use initializationVector (IV)
            byte[] initializationVector = new byte[encryptCipher.getBlockSize()]; // no matter use encryptCipher or decryptCipher, because they have the same length. the same amount of bytes
            random.nextBytes(initializationVector); // put the next byte in the array
            ivParams = new IvParameterSpec(initializationVector);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) {
        byte[] bytes = plainText.getBytes();
        byte[] cipherText = null;
        try {
            cipherText = encryptCipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public String decrypt(String cipherText) {
        byte[] bytes = cipherText.getBytes();
        byte[] plainText;
        try {
            plainText = decryptCipher.doFinal(Base64.getDecoder().decode(bytes));
            return new String(plainText, StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
