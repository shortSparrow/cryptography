import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ExtendedEuclideanAlgorithm extendedEuclideanAlgorithm = new ExtendedEuclideanAlgorithm();
        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(15, 56))); // [1, 15, -4] => a*15 + b(-4) = 1
        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(7, 9))); // [1, 4, -3] => a*4 + b(-3) = 1
    }
}