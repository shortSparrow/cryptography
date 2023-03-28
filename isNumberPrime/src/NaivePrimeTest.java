public class NaivePrimeTest {
    public boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        /**
         * Якщо число складене (не прсоте), то можна його запичати як n = a * b, де
         * 1) a = sqrt(n) i b = sqrt(n)
         * АБО
         * 2) a > 2 i a > sqrt(n) i b < sqrt(n), бо якщо a і b > sqrt(n) => a * b > n
         *
         * Отже виходить что якщо число не просте то максимальний множник буде Math.sqrt(number), тому можна не запускати
         * цикл аж до number
         */
        // Math.sqrt(10) == 3.3.. But we want work only with integer numbers so add Math.floor
        for (int i = 3; i <= Math.floor(Math.sqrt(number)); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
