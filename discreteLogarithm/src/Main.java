import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        DiscreteLogarithm discreteLogarithm = new DiscreteLogarithm();

        BigInteger b = new BigInteger("5");
        BigInteger c = new BigInteger("948603");
        BigInteger m = new BigInteger("9048610007");

        //  bˆc mod m
        System.out.println(discreteLogarithm.modularExponential(b,c,m)); // 3668993056

        BigInteger a = new BigInteger("3668993056");
        System.out.println(discreteLogarithm.discreteLogarithm(a, b,m)); // 948603
    }
}