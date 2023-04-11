import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman {
    private final Random random;

    public DiffieHellman() {
        random = new Random();
    }

//    n це велике просте число (быльше 1024 біт), g - це первісний корінь від n (primitive root modulo n).
//    g це первісний корінь n, якщо g mod n...gˆn-1 генерує всі можливі значення в межах [1, n-1]
    public void generatePrivateKeys(BigInteger n, BigInteger g) {
//        Згенеруємо рандомний x, який < n-1 для Alice
        int rand1 = random.nextInt(0, n.intValue() - 2) + 1; // n.intValue()-2 + 1 - тому що ми хочемо отримати x в межах від 1 і до n-1
        BigInteger x = new BigInteger(Integer.toString(rand1));

//        Згенеруємо рандомний x, який < n-1 для Bob
        int rand2 = random.nextInt(0, n.intValue() - 2) + 1; // n.intValue()-2 + 1 - тому що ми хочемо отримати x в межах від 1 і до n-1
        BigInteger y = new BigInteger(Integer.toString(rand2));

        // calculating k for ALice K1 = gˆx mod n
        BigInteger k1 = g.modPow(x, n);

        // calculating k for Bob K2 = gˆy mod n
        BigInteger k2 = g.modPow(y, n);


        // Alice private key
        BigInteger key1 = k2.modPow(x, n);

        // Bon private key
        BigInteger key2 = k1.modPow(y, n);

        System.out.println("ALICE private key is: " + key1.intValue());
        System.out.println("BOB private key is: " + key2.intValue());
    }

}
