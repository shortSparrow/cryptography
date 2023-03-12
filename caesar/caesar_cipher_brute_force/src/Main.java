public class Main {
    public static void main(String[] args) {
        CaesarCipherBruteForce cracker = new CaesarCipherBruteForce();
        String cipherText = "KHOORCZRUOG"; // HELLO WORLD
        cracker.brute(cipherText); // print all possible variants
    }
}