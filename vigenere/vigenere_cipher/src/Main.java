public class Main {
    public static void main(String[] args) {
        VigenereCipher vigenereCipher = new VigenereCipher();
        String key = "secret";
        String message = "Hello";
        String cipherText = vigenereCipher.encrypt(message, key);
        System.out.println(cipherText); //  JOCT

        String plainText = vigenereCipher.decrypt(cipherText, key);
        System.out.println(plainText); // HELLO
    }
}