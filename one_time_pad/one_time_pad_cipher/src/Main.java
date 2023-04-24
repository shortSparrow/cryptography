import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;


public class Main {
    public static void main(String[] args) {
        // TEXT EXAMPLE
//        textExample();

        // IMAGE EXAMPLES
//        imageExampleV1();
        imageExampleV2();

    }

    public static void textExample() {
        String plainText = "Cryptography is important in bitcoin and other crypto currencies";
        RandomGenerator randomGenerator = new RandomGenerator();
        int[] key = randomGenerator.generate(plainText.length()); // random numbers from 0 to ALPHABET.length() // 9 2 17 19 0 12 ....
        OneTimePad oneTimePad = new OneTimePad();
        String cipherText = oneTimePad.encrypt(plainText, key);
        String decryptedText = oneTimePad.decrypt(cipherText, key);
        System.out.println("cipherText: " + cipherText);
        System.out.println("decryptedText: " + decryptedText);
    }

    //    Variant 1 - cipher text is not an image, vut after decrypting file being an image
    public static void imageExampleV1() {
        RandomGenerator randomGenerator = new RandomGenerator();
        OneTimePad oneTimePad = new OneTimePad();
        URL res = Main.class.getClassLoader().getResource("1.jpg");
        File fi = new File(res.getFile());
        try {
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            int[] key = randomGenerator.generateForImageV1(fileContent.length);
            byte[] cipherText = oneTimePad.encryptImageV1(fileContent, key);
            System.out.println(Base64.getEncoder().encodeToString(cipherText)); // BASE64

            byte[] decryptedText = oneTimePad.decryptImageV1(cipherText, key);
            System.out.println(Base64.getEncoder().encodeToString(decryptedText)); // BASE64
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    Variant 2 - cypher file also is an image. just full of noise
    public static void imageExampleV2() {
        try {
            RandomGenerator randomGenerator = new RandomGenerator();
            OneTimePad oneTimePad = new OneTimePad();

            BufferedImage image = ImageIO.read(new File(Main.class.getClassLoader().getResource("1.jpg").getFile()));
            int[][] key2 = randomGenerator.generateForImageV2(image.getHeight() * image.getWidth());
            BufferedImage cipherImage = oneTimePad.encryptImageV2(image, key2, true);
            System.out.println(bufferedImageIntoBase64(cipherImage));

            BufferedImage decryptedImage = oneTimePad.decryptImageV2(cipherImage, key2, true);
            System.out.println(bufferedImageIntoBase64(decryptedImage));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static String bufferedImageIntoBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            return Base64.getEncoder().encodeToString(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}