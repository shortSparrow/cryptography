import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        ECC ecc = new ECC(0, 7);
//        Point generator = new Point(1,1);

//        System.out.println(ecc.pointAddition(generator, generator));
//        System.out.println(ecc.doubleAndAdd(100, generator));


        // Diffie Hellman
        ECC ecc = new ECC(3, 7);
        Point generator = new Point(-2,1);
        Random random = new Random();

        // random for Alice
        int a = random.nextInt(10000);
        // random for Bob
        int b = random.nextInt(10000);

        // public keys with the double and add algorithm, there are points on the elliptic curve
        Point alicePublicKey = ecc.doubleAndAdd(a, generator);
        Point bobPublicKey = ecc.doubleAndAdd(b, generator);

        // they can generate the same private key they can use for symmetric encryption
        Point aliceSecretKey = ecc.doubleAndAdd(a, bobPublicKey);
        Point bobSecretKey = ecc.doubleAndAdd(b, alicePublicKey);

        // they are difference, I suppose this is inaccuracy of mathematical operations in programming languages
        System.out.println(aliceSecretKey);
        System.out.println(bobSecretKey);
    }
}