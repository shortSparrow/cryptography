import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// SHA256 - 256 bits output - 64 hexa characters
// This is algorithms for Bitcoin miners generating in order to find valid hash for the given block in the blockchain
public class SHA256 {
    public String digest(String message) {
        MessageDigest messageDigest = null;
        byte[] messageDigestByte;
        String hexaHash = "";

        try {
            // we are using SHA256 hash algorithm
            messageDigest = MessageDigest.getInstance("SHA256");
            messageDigestByte = messageDigest.digest(message.getBytes());
            // convert the message hash into hexadecimal (16 characters). Problem is that may omit leading zeros
            hexaHash = new BigInteger(1, messageDigestByte).toString(16);
            // add zeros if they were omitted
            while (hexaHash.length() < 64) {
                hexaHash = "0" + hexaHash;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hexaHash;
    }
}
