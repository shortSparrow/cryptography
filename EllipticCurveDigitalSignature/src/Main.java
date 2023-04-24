import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        //  define the provider (BC)
        Security.addProvider(new BouncyCastleProvider());

//        KeyPair keys = CryptographyHelper.generateKeys();
//        System.out.println(keys.getPublic());
//        System.out.println(keys.getPrivate());
//
//        System.out.println(Base64.getEncoder().encodeToString(CryptographyHelper.sign(keys.getPrivate(), "This is a message")));


        KeyPair keys1 = CryptographyHelper.generateKeys();
        KeyPair keys2 = CryptographyHelper.generateKeys();
        String message = "This is a message";
        byte[] signature = CryptographyHelper.sign(keys1.getPrivate(), message);
        System.out.println(CryptographyHelper.verify(keys1.getPublic(), message, signature)); // true
        System.out.println(CryptographyHelper.verify(keys2.getPublic(), message, signature)); // false
    }
}