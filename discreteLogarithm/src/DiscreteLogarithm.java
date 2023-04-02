import java.math.BigInteger;

public class DiscreteLogarithm {

    // THIS IS SLOW
    // discrete logarithm is inverse operation modularExponential
    // we know b and m and result of bˆc mod m. And we want to calculate c
    public BigInteger discreteLogarithm(BigInteger a, BigInteger b, BigInteger m) {
        // we try find all the values of the exponent one b one until e find it
        BigInteger c = new BigInteger("1");

        // if bˆc mod m = a it means we find right x exponent
        while (b.modPow(c, m).compareTo(a) != 0) {
            c = c.add(BigInteger.ONE);
        }

        return c;
    }

    // IT IS FAST
    public BigInteger modularExponential(BigInteger b, BigInteger c, BigInteger m) {
        return b.modPow(c, m); // bˆc mod m
    }
}
