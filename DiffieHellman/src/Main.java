import java.math.BigInteger;

//    Алгоритм Деффі-Хельмана використовує прості числа. Чому n повинно бути простим, а g primitive root modulo n?
//    g це простий корінь n, якщо g mod n...gˆn-1 генерує всі можливі значення в межах [1, n-1]
//
//    Чому g повиино бути primitive root modulo n?
//    Розглянемо випадок, коли g НЕ Є primitive root modulo n.
//    n = 10; g = 11 -> g is a NOT primitive root of n. Бо у нас тільки два значення: [1,10], a не [1,2,3,4,5,6,7,8,9.10]
//    10ˆ1 mod 11 = 10
//    10ˆ2 mod 11 = 1
//    10ˆ3 mod 11 = 10
//    10ˆ4 mod 11 = 1
//    10ˆ5 mod 11 = 10
//    10ˆ6 mod 11 = 1
//    10ˆ7 mod 11 = 10
//    10ˆ8 mod 11 = 1
//    10ˆ9 mod 11 = 10
//    10ˆ10 mod 11 = 1
//
//    Розглянемо випадок, коли g Є primitive root modulo n.
//    n=11; g = 8;  g is a primitive root of n. Бо у нас є всі значення [1, n-1]
//    8ˆ1 mod 11 = 8
//    8ˆ2 mod 11 = 9
//    8ˆ3 mod 11 = 6
//    8ˆ4 mod 11 = 4
//    8ˆ5 mod 11 = 10
//    8ˆ6 mod 11 = 3
//    8ˆ7 mod 11 = 2
//    8ˆ8 mod 11 = 5
//    8ˆ9 mod 11 = 7
//    8ˆ10 mod 11 = 1
//
//    Тож як ми бачимо, якщо g не є primitive root modulo n, то нраші k (k = gˆx mod n чи gˆy mod n) будуть обмеженими,
//    тобто key space нашого ключа стаэ меншим і зловмиснику буде простіше підіьрати значення. Адже як ми бачимо на приколаді,
//    коли g не є primitive root  modulo n то було тільки два ключа 1 і 10, а у випадку, коли g є primitive root modulo n то список
//    потенційних ключів це весь простір [1,n-1]


//    Чому n повинно бути простим?
//    Напевно частокво, щоб  g могло бути primitive root modulo n, але це не точно, а також
//    щоб зловмисники не могли застосувати Chinese Reminder Theorem (китайське теорема про залишок)

//    Алгоритм Деффі-Хельмана збулований на тому, що дуже складно (немає наразі швидкого алгоритму)
//    обрахувати дискретний алгоритм (discrete logarithm problem).
//    Ми легко можемо зробити операцію bˆk = a; А от щоб знайти К нам потрібно обрахувати  k = logba (log від a за соновою b),
//    і це складно (експоненційна складність)

public class Main {
    public static void main(String[] args) {
        // it should be a huge prime number
        BigInteger n = new BigInteger(Integer.toString(37));

        // it should be a prime primitive root of n
        BigInteger g = new BigInteger(Integer.toString(13));

        DiffieHellman diffieHellman = new DiffieHellman();
        diffieHellman.generatePrivateKeys(n,g);

    }
}