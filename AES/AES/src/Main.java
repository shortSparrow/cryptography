public class Main {
    public static void main(String[] args) {
        AES aes = new AES();
        String text = "Hello world!";

        String cipherText = aes.encrypt(text);
        System.out.println(cipherText);

        String plainText = aes.decrypt(cipherText);
        System.out.println(plainText);
    }
}