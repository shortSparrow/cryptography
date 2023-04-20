import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ExtendedEuclideanAlgorithm extendedEuclideanAlgorithm = new ExtendedEuclideanAlgorithm();
        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(15, 56))); // [1, 15, -4] => a*15 + b(-4) = 1
        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(7, 9))); // [1, 4, -3] => a*4 + b(-3) = 1


//        Схоже, що якщо ExtendedEuclideanAlgorithm повертає x позитивним, то він і є modular inverse, а якщо негативним,
//        то modular inverse = m-x
//        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(27, 392))); // [1, -29, 2] -> 392 - 29 = 363
//        BigInteger a = new BigInteger(BigInteger.valueOf(27).toString());
//        System.out.println(a.modInverse(BigInteger.valueOf(392))); // 363

//        System.out.println(Arrays.toString(extendedEuclideanAlgorithm.egcd(21, 352))); // [1, -67, 4] -> 352 - 67 = 285
//        BigInteger a = new BigInteger(BigInteger.valueOf(21).toString());
//        System.out.println(a.modInverse(BigInteger.valueOf(352))); // 285
    }
}