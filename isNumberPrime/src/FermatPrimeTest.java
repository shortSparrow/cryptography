import java.math.BigInteger;
import java.util.Random;

public class FermatPrimeTest {
    private Random random;

    public FermatPrimeTest() {
        random = new Random();
    }

    /**
     * Мала теорема Ферма говрить що Нехай p — просте, a — ціле, що не ділиться на p тоді:
     *  aˆp-1 % p == 1
     *  Але якщо p не просте, то овно теж може спрацювати, бо є цичла Крамайкла (псевдопрості числа)
     *  кармайклове число це додатне складене число n, що задовольняє умову  aˆp-1 % p == 1 для всіх цілих a, взаємно простих з p.
     *  Наприкоад для основи 2 число 561.
     *  Отож якщо ми шукаємо чи просте число 561 і рандом видасть те яке взаємно просте з ним, або складається з взоаємно простого то результат буде не вірний
     *  2 - coPrime (взаємно просте)
     *  3 - not coPrime
     *  4 - coPrime
     *  5 - coPrime
     *  6 - 2 * 3 (not coPrime)
     *  Тож для основи 2, 4, 5 - результат був не вірним, показало, що число просте, а воно не просте
     */
    public boolean isPrime(BigInteger number, int k) {
        if (number.intValue() <= 1) return false;
        if (number.intValue() == 2) return false;

        for (int i = 0; i < k; i++) {
            // generate new random number [2, N-1]
            BigInteger a = BigInteger.valueOf(random.nextInt((number.intValue() - 2) + 2));

            // if aˆp-1%p != 1 then p is not a prime
            if (!a.modPow(number.subtract(BigInteger.ONE), number).equals(BigInteger.ONE)) {
                return false;
            }
        }

        return true;
    }
}
