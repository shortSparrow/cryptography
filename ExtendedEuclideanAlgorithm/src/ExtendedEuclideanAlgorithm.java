// Розширений алгоритм Евкліда використовується для пошуку модулів мультиплікативного оберненого до
// цілого числа (modular multiplicative inverse)  за лінійний час.
// Як і звичайний алгоритм Евкліда розширений алгоритм шукаю НСД(GCD) дл цілхи чисел a i b, але окрім цього
// шукає x i y, для яких виконується рівняння Безу (лема Безу): a*x + b*y = gcd(a,b)
//
// Тотожність Безу. Нехай a i b цілі числа з найбільшим спільним дільником d. Тоді існують цілі числа x і y такі, що
// ax+by=d. Більш точніше, цілі числа вигляду ax+by є дільниками d
//
// Розширений алгоритм Евкліда корисний коли a i b взаємно прості (coprime) (наш випадок з RSA).
// x - це мультиплікативно обернене до a mod b (a*x mod m=1)
// y - це мультиплікативно обернене до b mod a (b*y mod m=1)

public class ExtendedEuclideanAlgorithm {

    // В нашій реалізації a < b
    public int[] egcd(int a, int b) {
        // base-case
        // if a = 0 than gcd is b. gcd(0,8) = 8; gcd(0,77) = 77;
        if (a == 0) {
            // gcd(0,b)=b and a*x+b*y=b  SO  x=0 and y=1
            return new int[]{b, 0, 1};
        }


//        Оскілльки а повинно бути меншим з b
//        Ми використовуємо Алгоритм Евкліда для gcd
//        b % a - завжди буде меншим числом, і а є меншим числом (a < b)
//        gcd, x1, y1 = egcd(a%b,a)
        int[] values = egcd(b % a, a);
        int gcd = values[0];
        int x1 = values[1];
        int y1 = values[2];

        int x = y1 - (b / a) * x1;
        int y = x1;

        return new int[]{gcd, x, y};
    }
}
