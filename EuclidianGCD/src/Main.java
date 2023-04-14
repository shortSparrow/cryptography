public class Main {
    public static void main(String[] args) {
        EuclideanGreatestCommonDivisor gcd = new EuclideanGreatestCommonDivisor();

        System.out.println(gcd.gcd_recursion(24, 9)); // 3
        System.out.println(gcd.gcd_recursion(24, 12)); // 12
        System.out.println(gcd.gcd_recursion(1545, 221)); // 1

        System.out.println(gcd.gcd_iteration(24, 9)); // 3
        System.out.println(gcd.gcd_iteration(24, 12)); // 12
        System.out.println(gcd.gcd_iteration(1545, 221)); // 1
    }
}