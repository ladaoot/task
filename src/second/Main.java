package second;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));
        System.out.println("_______________________");
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));
        System.out.println("_______________________");
        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));
        System.out.println("_______________________");
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 6}));
        System.out.println("_______________________");
        System.out.println(Arrays.toString(indexMult(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(indexMult(new int[]{3, 3, -2, 408, 3, 31})));
        System.out.println("________________________");
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println("________________________");
        System.out.println(Tribonacci(7));
        System.out.println(Tribonacci(11));
        System.out.println("________________________");
        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(7));
        System.out.println(pseudoHash(0));
        System.out.println("________________________");
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));
        System.out.println("________________________");
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
        System.out.println(isAnagram("aaabb", "bbbaa"));


    }

    public static boolean duplicateChars(String s) {
        // String использовать
//        char[] chars = s.toLowerCase().toCharArray();
//        int i = 0;
//        int l = chars.length;
//        while (i != l) {
//            for (int j = i + 1; j < l; j++) {
//                if (chars[i] == chars[j]) {
//                    return true;
//                }
//            }
//            i++;
//        }
//
//        return false;
        s = s.toLowerCase();
        for (int i = 0; i<s.length()-1; ++i){
            if(s.substring(i + 1).contains(String.valueOf(s.charAt(i)))){
                return true;
            }
        }

        return false;
    }

    public static String getInitials(String s) {
        // String
//        char[] chars = s.toCharArray();
        // StringBuilder

        StringBuilder d = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (!Character.isUpperCase(c)) d.replace(i, i + 1, " ");
        }
        return d.toString().replace(" ", "");
//        String ans = "";
//        for (char ch : s) {
//            if (Character.isUpperCase(ch)) {
//                ans = ans + ch;
//            }
//        }
//        return ans;
    }

    public static int differenceEvenOdd(int[] mas) {
        int even = 0;
        int odd = 0;
        for (int a : mas) {
            // ((a & 1) == 0)
            if ((a & 1) == 0) {
//            if (a % 2 == 0) {
                even = even + a;
            } else odd = odd + a;
        }
        return even > odd ? even - odd : odd - even;
    }

    public static boolean equalToAvg(int[] mass) {
        int count = mass.length;
        int sum = 0;
        for (int a : mass) {
            sum = sum + a;
        }
        double avg = (double) sum / count;
        for (int a : mass) {
            if (a == avg) {
                return true;
            }
        }
        return false;
    }

    public static int[] indexMult(int[] mass) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = mass[i] * i;
        }
        return mass;
    }

    public static String reverse(String s) {
        // StringBuilder.reverse()
        StringBuilder d = new StringBuilder(s);
        return d.reverse().toString();
//        char[] chars = s.toCharArray();
//        String finih = "";
//        for (int i = chars.length - 1; i >= 0; i--) {
//            finih = finih + chars[i];
//        }
//        return finih;
    }

    public static int Tribonacci(int n) {
        int[] trib = new int[n];
        for (int i = 0; i < n; i++) {
            if (i <= 1) {
                trib[i] = 0;
            } else if (i == 2) {
                trib[i] = 1;
            } else {
                trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
            }
        }
        return trib[n - 1];
    }

    public static String pseudoHash(int n) {
        String sim = "abcdef0123456789";
        StringBuilder ans = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            ans.append(sim.charAt(r.nextInt(sim.length() - 1)));
        }
        return ans.toString();
    }

    public static String botHelper(String s) {
        // String.contains()
        return s.toLowerCase().contains("help") ? "Calling for a staff member" : "Keep waiting";
//        int a = s.indexOf("help");
//        if (a != -1) {
//            return "Calling for a staff member";
//        }
//        return "Keep waiting";
    }

    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        a = a.replace(" ", "").toLowerCase();
        b = b.replace(" ", "").toLowerCase();
        StringBuilder c = new StringBuilder(b);
        for (char ch : a.toCharArray()) {
            int index = c.indexOf(String.valueOf(ch));
            if (index < 0) {
                return false;
            }
            c.deleteCharAt(index);
        }
        return true;
    }
}
