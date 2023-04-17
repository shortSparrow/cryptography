import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA algorithm
 *
 * Загальна база
 * Мала теорема Ферма - aˆ(p-1) congruent 1 mod p (якщо p просте, а ціле, що не ділиться на p) (часний випадок теореми Ейлера)
 * Теорема Ейлера - aˆPhi(m) congruent 1 mod m, якщо m взаємно просте з a (a - довільне число) і m>1
 * Взаємно прості числа - це числа для яких gcd(a,b)=1
 * Phi(n) - це фі Ейлера, вона показує кількість взаємно прстих чисел до n. Наприклад:
 * Phi(5) = 1,2,3,4 => Phi(5) = 4
 * Phi(8) = 1,3,5,7 => Phi(8) = 4
 * Phi(7) = 6
 *
 * Цікава особливість Phi(prime) = prime-1. Що логічноЮ адже якщо число просте, то воно ділиться тільки на себе і на 1,
 * отож у нього всі числа до нього самого є взаємно простими
 * В RSA ми використовуємо саме прсоті числа, тож для нас актуально Phi(prime) = prime-1
 *

 * Алгоритм
 * 1) Згенеруємо 2 великих числа p and q (ми сожемо використати Rabin-Miller algorithm)
 * 2) Рахуємо n=q*p, і також Phi(n), оскільки q i p є простими то Phi(n) = (q-1)*(p-1)
 * 3) Далі рахуємо public key - e параметр
 * Ми можемо виразувати e за допомгою gcd(e, Phi(n))=1. (e і Phi(n) є взаємно простими)
 * 4) Вираховуємо private key d параметр: Для цього терба вирахувати модуль оберненого до e (modular inverse of e)
 * (e і Phi(n) є взаємно простими, тож ми можемо використати Розширений алгоритм Евкліда (Euclidean Extended Algorithm)
 * d * e mod Phi(n) = 1
 *
 * PUBLIC_KEY(e,n)      PRIVATE_KEY(e,d)
 *
 * Спочатку ми розбиваємо plaintext на блоки, де кожен блок менший за n
 * Ми можемо використати ASCII таблицю для перетворення тексту у цифри
 * ciphertext_block = plaintext_blockˆ(e) mod n
 * plaintext_block = ciphertext_blockˆ(d) mod n
 *
 *
 * Реальний приклад
 * 1) Згенеруємо великі прості числа (для прикладу будуть маленькі): p=17; q=23
 * 2) Рахуємо n=p*q=17*23=391 -> Тож Phi(n)=Phi(391)=(17-1)(23-1)=352
 * 3) Тепер знайдемо e, оскільки gcd(e, Phi(n))=1 => gcd(e, Phi(391))=1 => gcd(e, 352)=1 тож e=21
 * Ми це вирішили простою підстановкою рандомних числел доки рівняння не зійшлося. Можемо перевірити - 352 і 21 є взаємно простими - тоже все вірно
 * 4) Тепер знайдемо модуль оберненого до а (modular inverse of e)
 * d * e mod Phi(n) = 1 => d * 21 mod 352 = 1 =>  d=285
 * Робимо перевірку 285 * 21 mod 352 = 5985 mod 352 = 1. Тож d=285 це дійсно модуль обереного до цілого 21 mod 352
 *
 * PUBLIC_KEY(21,391)      PRIVATE_KEY(295,391)
 *
 * Для прикладу, ми хочемо зашифрувати а, в ASCII таблиці букві а відповідає чисало 97
 * ciphertext_block = plaintext_blockˆ(21) mod 391 = 97ˆ21 mod 391 = 37 => це символ %
 * plaintext_block = ciphertext_blockˆ(295) mod 391 = 37ˆ295 mod 391 = 97 => це символ а
 *
 *
 * RSA Cracking
 * Розбиття великих чисел зазчивай складне: але не завжди
 * Якщо число має менші дільники за нього, це означає, щл дільники можуть бути знайдені за сотні чи тисячі ітерацій
 * Це місце де прості числа такі важливі, бо якщо ми маємо p і q великими простими чистами ми можемо підрахувати n=p*q дуже швидко
 * Бо спочатку зловмиснику треба занйти всі прсоті числа менші за n (чи можливо за корніь з n) і потім ділити n них, а ділення не дуже
 * швидка операція, та ще й з велитенськими числами.
 * Тому і не можна знайти p і q pf поліноміальний час
 *
 */


public class RSA {
    // this is the 'e' parameter (encryption)
    private BigInteger publicKey;
    // this is the 'd' parameter (decryption)
    private BigInteger privateKey;
    // thi is n = p * q
    private BigInteger n;
    // need a random number generator
    private SecureRandom random;

    public RSA() {
        this.random = new SecureRandom();
    }

    public void generateKes(int keyDigits) {
        // p is a large prime number
        BigInteger p = BigInteger.probablePrime(keyDigits, random);
        // q is a large prime number
        BigInteger q = BigInteger.probablePrime(keyDigits, random);
        // n = p * q. This is a trapdoor function
        n = p.multiply(q);

        // Euler's totient phi function (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // we have to use GCD to find e and gcd(phi,e)=1
        // so e is coprime to phi
        BigInteger e = generatePublicFactor(phi);

        // modular inverse of e - mod phi (Extend Euclidean algorithm)
        BigInteger d = e.modInverse(phi);
        this.privateKey = d;
        this.publicKey = e;
    }

    private BigInteger generatePublicFactor(BigInteger phi) {
        BigInteger e = new BigInteger(phi.bitLength(), random);
        // we are after coprime where gcd(e,phi) = 1
        while (!(e.gcd(phi)).equals(BigInteger.ONE)) {
            e = new BigInteger(phi.bitLength(), random);
        }

        return e;
    }

    public BigInteger encryptMessage(String message) {
        return encypt(publicKey, n, message);
    }

    public String decryptMessage(BigInteger message) {
        return decrypt(privateKey, n, message);
    }

    // the cipher text is a huge integer
    private BigInteger encypt(BigInteger publicKey, BigInteger n, String message) {
        byte[] messageByte = message.getBytes();
        BigInteger messageInt = new BigInteger(messageByte);

        // we have to use modular exponentiation
        // so the cipher text = message^(e mod n)
        return messageInt.modPow(publicKey, n);
    }

    private String decrypt(BigInteger privateKey, BigInteger n, BigInteger cipherText) {
        // we use modular exponentiation for decryption as well
        // cipherˆ(privateKey mod n) = plain text
        BigInteger messageInt = cipherText.modPow(privateKey, n);

        // transform integer into a string
        return new String(messageInt.toByteArray());
    }
}
