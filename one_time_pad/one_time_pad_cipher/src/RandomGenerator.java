import java.util.Random;

public class RandomGenerator {
    private Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    public int[] generate(int n) {
        int[] randomSequence = new int[n];

        for (int i = 0; i < n; i++) {
            randomSequence[i] = random.nextInt(Constants.ALPHABET.length());
        }

        return randomSequence;
    }


    public int[] generateForImageV1(int n) {
        int[] randomSequence = new int[n];

        for (int i = 0; i < n; i++) {
            randomSequence[i] = random.nextInt(256);

        }

        return randomSequence;
    }

    // every pixel consist of 3 values: red, green, blue. So generate random values for each od them
    public int[][] generateForImageV2(int n) {
        int[][] randomSequence = new int[n][3];

        for (int i = 0; i < n; i++) {
            randomSequence[i] = new int[]{(int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)};
        }

        return randomSequence;
    }

}
