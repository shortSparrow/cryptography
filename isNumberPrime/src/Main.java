import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        NaivePrimeTest naivePrimeTest = new NaivePrimeTest();
        System.out.println("naivePrimeTest: " + naivePrimeTest.isPrime(2_147_483_647)); // max number int for 32-bit system
        System.out.println("naivePrimeTest: " + naivePrimeTest.isPrime(561));

        FermatPrimeTest fermatPrimeTest = new FermatPrimeTest();
        System.out.println("fermatPrimeTest: " + fermatPrimeTest.isPrime(BigInteger.valueOf(561), 1)); // may be true
        System.out.println("fermatPrimeTest: " + fermatPrimeTest.isPrime(BigInteger.valueOf(561), 10)); // false
    }
}