package first;


public class Main {
    public static void main(String[] args) {
        System.out.println("Задача 1");
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        System.out.println("_______________");
        System.out.println("Задача 2");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        System.out.println("_______________");
        System.out.println("Задача 3");
        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));
        System.out.println("_______________");
        System.out.println("Задача 4");
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        System.out.println("_______________");
        System.out.println("Задача 5");
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));
        System.out.println("_______________");
        System.out.println("Задача 6");
        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(howManyItems(45, 1.8, 1.9));
        System.out.println(howManyItems(100, 2, 2));
        System.out.println("_______________");
        System.out.println("Задача 7");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println("_______________");
        System.out.println("Задача 8");
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));
        System.out.println("_______________");
        System.out.println("Задача 9");
        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250));
        System.out.println("_______________");
        System.out.println("Задача 10");
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));

    }

    public static double convert(int l) {
        return l * 3.785;
    }

    public static int fitCalc(int min, int in) {
        return min * in;
    }

    public static int containers(int cor, int me, int boc) {
        return cor * 20 + me * 50 + boc * 100;
    }

    public static String triangleType(int a, int b, int c) {
        if (a + b < c || a + c < b || b + c < a) {
            return "not a triangle";
        }
        if (a == b && b == c) {
            return "isosceles";
        } else if (a == b || b == c || c == a) {
            return "equilateral";
        } else return "different-sided";
    }

    public static int ternaryEvaluation(int a, int b) {
        return b < a ? a : b;
    }

    public static int howManyItems(int m, double h, double w) {
        return (int) (m / (h * w) / 2);
    }

    public static int factorial(int x) {
        int f = 1;
        for (int i = 1; i <= x; i++) {
            f = f * i;
        }
        return f;
    }

    public static int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    public static int ticketSaler(int x, int cost) {
        return (int) (x * cost * 0.72);
    }

    public static int tables(int s, int t) {
        if (t*2 > s) return 0;
        int ost = s - t*2;
        return ost % 2 == 0 ? ost / 2 : ost / 2 + 1;
    }
}