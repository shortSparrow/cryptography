public class Main {
    public static void main(String[] args) {
        DES des = new DES();
        String text = "Hello world!";
        String cipherText = des.encrypt(text);
        String plainText = des.decrypt(cipherText);

        System.out.println("cipherText: " + cipherText);
        System.out.println("plainText: " + plainText);
    }
}