import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {

    // public key (x,y) and private key (256 bits)
    public static KeyPair generateKeys() {
        try {
            // ECDSA - means Elliptic Curve Digital Signature
            // BC - means BouncyCastle
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            // SHA1 - hashing algorithm
            // PRNG - pseudo random number generator
            // random numbers 160 bits
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec parameterSpec = new ECGenParameterSpec("prime256v1");
            keyPairGenerator.initialize(parameterSpec, secureRandom);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    // private key - signature generation
    // public key - verifying
    public static byte[] sign(PrivateKey privateKey, String message) {
        Signature signature;
        byte[] output = new byte[0];

        try {
            signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            output = signature.sign();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException ex) {
            throw new RuntimeException(ex);
        }

        return output;
    }

    public static  boolean verify(PublicKey publicKey, String message, byte[] signature) {
        try {
            Signature algorithm = Signature.getInstance("ECDSA", "BC");
            algorithm.initVerify(publicKey);
            algorithm.update(message.getBytes());
            return  algorithm.verify(signature);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }
}
