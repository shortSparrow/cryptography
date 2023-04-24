import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class OneTimePad {

    public String encrypt(String plainText, int[] key) {
        plainText = plainText.toUpperCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            int keyIndex = key[i];
            int characterIndex = Constants.ALPHABET.indexOf(plainText.charAt(i));

//            encryptedLetter = (characterIndex + randomShiftIndex) mod 27
//             Ei(Xi) = (Xi + OTPi) mod 26
            cipherText += Constants.ALPHABET.charAt(Math.floorMod(characterIndex + keyIndex, Constants.ALPHABET.length()));
        }
        return cipherText;
    }

    public String decrypt(String cipherText, int[] key) {
        cipherText = cipherText.toUpperCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int keyIndex = key[i];
            int characterIndex = Constants.ALPHABET.indexOf(cipherText.charAt(i));

//            encryptedLetter = (characterIndex - randomShiftIndex) mod 27
//             Ei(Xi) = (Xi - OTPi) mod 26
            plainText += Constants.ALPHABET.charAt(Math.floorMod(characterIndex - keyIndex, Constants.ALPHABET.length()));
        }
        return plainText;
    }


    // this is works fine, but encrypted file isn't an image, so it will be just a file, you can not see one as image
    // because we encrypt info about type also (jpeg image encoder maybe)
    public byte[] encryptImageV1(byte[] plainText, int[] key) {
        byte[] cipherText = new byte[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            int keyIndex = key[i];
            int characterIndex = plainText[i];

            cipherText[i] = (byte) (characterIndex ^ keyIndex); // OR (byte) Math.floorMod(characterIndex + keyIndex, 256);
        }

        return cipherText;
    }

    public byte[] decryptImageV1(byte[] plainText, int[] key) {
        byte[] cipherText = new byte[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            int keyIndex = key[i];
            int characterIndex = plainText[i];

            cipherText[i] = (byte) (characterIndex ^ keyIndex); // OR (byte) Math.floorMod(characterIndex - keyIndex, 256);
        }
        return cipherText;
    }


    public BufferedImage encryptImageV2(BufferedImage image, int[][] key, boolean isSaveFile) {
        BufferedImage originalImage = image;
        int iteration = 0;
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {

                int randomRed = key[iteration][0];
                int randomGreen = key[iteration][1];
                int randomBlue = key[iteration][2];
                iteration++;

                // we can use this approach
                Color inputPixel = new Color(originalImage.getRGB(x, y));
                int outputRed = inputPixel.getRed() ^ randomRed;
                int outputGreen = inputPixel.getGreen() ^ randomGreen;
                int outputBlue = inputPixel.getBlue() ^ randomBlue;

                Color outputPixel = new Color(outputRed, outputGreen, outputBlue);
                originalImage.setRGB(x, y, outputPixel.getRGB());

                // Or this. Get and change pixel by bitwise operator. Result the same
//                int originalPixel = originalImage.getRGB(x, y);
//                int red = (originalPixel & 0x00ff0000) >> 16;
//                int green = (originalPixel & 0x0000ff00) >> 8;
//                int blue = originalPixel & 0x000000ff;
//                int outputRed = red ^ randomRed;
//                int outputGreen = green ^ randomGreen;
//                int outputBlue = blue ^ randomBlue;
//                int decryptedPixel = (outputRed << 16) | (outputGreen << 8) | outputBlue;
//                originalImage.setRGB(x, y, decryptedPixel);
            }
        }


        if (isSaveFile) {
            try {
                ImageIO.write(originalImage, "jpg", new File("encrypted.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return originalImage;
    }

    public BufferedImage decryptImageV2(BufferedImage cipherImage, int[][] key, boolean isSaveFile) {
        BufferedImage decryptedImage = cipherImage;
        int ineration = 0;
        for (int y = 0; y < decryptedImage.getHeight(); y++) {
            for (int x = 0; x < decryptedImage.getWidth(); x++) {
                int randomRed = key[ineration][0];
                int randomGreen = key[ineration][1];
                int randomBlue = key[ineration][2];
                ineration++;

                Color inputPixel = new Color(decryptedImage.getRGB(x, y));
                int outputRed = inputPixel.getRed() ^ randomRed;
                int outputGreen = inputPixel.getGreen() ^ randomGreen;
                int outputBlue = inputPixel.getBlue() ^ randomBlue;

                Color outputPixel = new Color(outputRed, outputGreen, outputBlue);
                decryptedImage.setRGB(x, y, outputPixel.getRGB());
            }
        }

        if (isSaveFile) {
            try {
                ImageIO.write(decryptedImage, "jpg", new File("decryptedImage.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return decryptedImage;
    }
}
