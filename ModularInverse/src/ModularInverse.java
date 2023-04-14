// В алгебрі inverse(Обернене за модулем число) of a це таке число x, яке при множенні на а дасть 1 (a*x=1).
// Тобто aˆ-1 * a = 1. Отже aˆ-1 inverse of a
// У математиці, зокрема в області арифметики, модулем мультиплікативного оберненого до цілого числа a називається
// таке ціле число x, що добуток a*x конгруентний 1 за модулем m => a*x CONGRUENT 1 mod m.
// В модульній арифметиці у нас є число а і модуль m. Отже Обернене за модулем число a це таке число inv, яке дасть (a * inv) mod m = 1
// Тобто для 12 mod 31 => inverse number 14 => (12 * 13) mod 31 = 1
// Обернене за модулем число щодо a по модулю m інсуює тільки коли а і m взаємно прості (coprime), тобто, якщо НСД(CGD) = 1

// Недолік такого алгоритму, що він має експоненіййну складність, і для RSA він занадто довго виконується (краще використовувати Euclidean Extended algorithm)
public class ModularInverse {
    public int calculate(int a, int m) {
        // this is brute-force approach, so we can check all possible values in the range [0; m-1]
        // running time seems to be O(m) linear but it is so actually exponential in the number of input bits
        for (int inv = 0; inv < m ; inv++) {
            if ((a*inv) % m == 1) {
                return inv;
            }
        }

        // there is no modular inverse (a is not a coprime to m)
        return -1;
    }
}
