public class Main {
    public static void main(String[] args) {
        String plainText = "Cryptography is important in bitcoin and other crypto currencies";
        RandomGenerator randomGenerator = new RandomGenerator();
        int[] key = randomGenerator.generate(plainText.length()); // random numbers from 0 to ALPHABET.length() // 9 2 17 19 0 12 ....
        OneTimePad oneTimePad = new OneTimePad();
        String cipherText = oneTimePad.encrypt(plainText, key);
        String decryptedText = oneTimePad.decrypt(cipherText, key);
        System.out.println("cipherText: " + cipherText);
        System.out.println("decryptedText: " + decryptedText);
    }
}