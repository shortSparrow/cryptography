public class App {
    public static void main(String[] args) {
        final int key = 3;

        String plainText = "Hello world";

        CaesarCipher cipher = new CaesarCipher();
        String cipherText = cipher.encrypt(plainText, key);
        System.out.println("cipherText: " + cipherText);

        String decryptedText = cipher.decrypt(cipherText, key);
        System.out.println("decryptedText: " + decryptedText);
    }
}