
// GCD - greatest common divisor (НСД - набільгий спільний дільник). Для його пошуку використовують
// алгоритм Евкліда (Euclidean algorithm).
// gcd(45,10) спочатку ми припускаємо що перше число більше за друге
// 45 = 10*q + r =>
// 45 = 10*4 + 5
// 10 = 5*q + r
// 10 = 5*2 + 0 Коли остача = 0, то це кінець алгоритсу і 5 є GCD (НСД)
//
// Також можна да допомогою mod обрахувати
// gcd(a,b)
// Припускаємо, що a >= b
// якщо b%a==0, тобто b/a ділиться без залишку, то gcd(a,b) = b
// Інакше gcd(a,b) = gcd(b,a mod b)
// gcd(24,9)
// 24 mod 9 = 6
// gcd(9,6)
// 9 mod 6 = 3
// gcd(6,3)
// 6 mod 3 = 0 Отже 3 є GCD




public class EuclideanGreatestCommonDivisor {
    // Ми вважаємо, що a > b
    public int gcd_recursion(int a, int b) {
        // Випадко, коли b націло ділиться на а - ознасає що GCD(НСД) це b
        int reminder = a % b;
        if(reminder == 0) return b;

        return gcd_recursion(b, reminder);
    }

    // Ми вважаємо, що a > b
    public int gcd_iteration(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }

        return  a;
    }
}
