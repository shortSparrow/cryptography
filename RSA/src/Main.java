import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String message = "This is a cryptography related message and then we have to instantiate the RSA algorithm.";
        RSA rsa = new RSA();
        rsa.generateKes(1024);

        BigInteger cipherText = rsa.encryptMessage(message);
        System.out.println("cipherText: " + cipherText);

        String plainText = rsa.decryptMessage(cipherText);
        System.out.println("plainText: " + plainText);
    }
}