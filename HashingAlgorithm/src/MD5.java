import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// MD5 - 128 bits output - 32 hexa characters
public class MD5 {
    public String digest(String message) {
        MessageDigest messageDigest = null;
        byte[] messageDigestByte;
        String hexaHash = "";

        try {
            // we are using MD5 hash algorithm
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigestByte = messageDigest.digest(message.getBytes());
            // 1 hexa character = 4 bits
            // convert the message has into hexadecimal (16 characters). Problem is that may omit leading zeros
            hexaHash = new BigInteger(1, messageDigestByte).toString(16);
            // add zeros if they were omitted
            while (hexaHash.length() < 32) {
                hexaHash = "0" + hexaHash;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hexaHash;
    }
}
