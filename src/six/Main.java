package six;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("_____________________________________________________________");
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(Arrays.toString(collect("strengths", 3)));
        System.out.println(Arrays.toString(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)));
        System.out.println("_____________________________________________________________");
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println("_____________________________________________________________");
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 1, 1, 1, 5}, 5)));
        System.out.println("_____________________________________________________________");
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(125)));
        System.out.println(Arrays.toString(isExact(720)));
        System.out.println(Arrays.toString(isExact(1024)));
        System.out.println(Arrays.toString(isExact(40320)));
        System.out.println("_____________________________________________________________");
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println(fractions("8.1(7)"));
        System.out.println("_____________________________________________________________");
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println(pilish_string("CANIMAKEAGUESSNOW"));
        System.out.println(pilish_string("FORALOOP"));
        System.out.println(pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println("_____________________________________________________________");
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 1) * (6 + 1)"));
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)"));
        System.out.println(generateNonconsecutive("4 * (4 + 5 * (1 + 1))"));
        System.out.println(generateNonconsecutive("- ( - 2)"));
        System.out.println(generateNonconsecutive("- ( + 1) * -5"));
        System.out.println(generateNonconsecutive("* 5"));
//        System.out.println(generateNonconsecutive("/"));
//        System.out.println(generateNonconsecutive("  8 /"));
        System.out.println("_____________________________________________________________");
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println("_____________________________________________________________");
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
        System.out.println(findLCS("abcd", "abcabcd"));
    }

    //1
    public static String hiddenAnagram(String first, String second) {
        first = first.replaceAll("[^A-Za-z]", "").toLowerCase();
        second = second.replaceAll(" ", "").toLowerCase();
        if (!isAllIn(first, second)) return "notfound";
        int start = 0;
        while (!isAllIn(first.substring(start, start + second.length()), second) && start + second.length() < first.length()) {
            start++;
        }
        return isAllIn(first.substring(start, start + second.length()), second) ? first.substring(start, start + second.length()) : "notfound";
    }

    public static boolean isAllIn(String f, String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (f.contains(Character.toString(c))) {
                s = s.replaceFirst(Character.toString(c), "");
                f = f.replaceFirst(Character.toString(c), "");
            }
        }
        return s.isEmpty();
    }

    //2
    public static String[] collect(String word, int n) {
        if (word.length() < n) {
            return new String[0]; // Возвращает пустой массив, если заданная строка меньше n.
        }

        List<String> result = new ArrayList<>();
        collectRecursive(word, n, result);
        Collections.sort(result); // Убедитесь, что результирующий массив лексикографически упорядочен.

        String[] res = new String[result.size()];
        int i = 0;
        for (String s : result) {
            res[i] = s;
            i++;
        }

        return res;
    }

    private static void collectRecursive(String word, int n, List<String> result) {
        if (word.length() < n) {
            return;
        }

        String slice = word.substring(0, n);
        result.add(slice);

        collectRecursive(word.substring(n), n, result);
    }

    //3
    public static String nicoCipher(String message, String key) {

        while (message.length() % key.length() != 0) {
            message = message + " ";
        }

        int[] keyOrder = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyOrder[i] = key.charAt(i) - 'a' + 1;
        }

        int[] messageOrder = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            messageOrder[i] = i + 1;
        }

        sortColumns(messageOrder, keyOrder);

        StringBuilder encodedMessage = new StringBuilder();
        int rows = message.length() / key.length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                int index = i * key.length() + messageOrder[j] - 1;
                encodedMessage.append(message.charAt(index));
            }
        }

        return encodedMessage.toString();
    }

    private static void sortColumns(int[] order, int[] keyOrder) {
        for (int i = 0; i < keyOrder.length - 1; i++) {
            for (int j = 0; j < keyOrder.length - i - 1; j++) {
                if (keyOrder[j] > keyOrder[j + 1]) {
                    swap(order, j, j + 1);
                    swap(keyOrder, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //4
    public static int[] twoProduct(int[] mas, int mul) {
        int start = 1;
        int end = 0;
        while (start != mas.length && mas[start] * mas[end] != mul) {
            end++;
            if (end == start) {
                start++;
                end = 0;
            }
        }
        if (start == mas.length) {
            return new int[0];
        }
        return new int[]{mas[end], mas[start]};
    }

    // 5
    public static int[] isExact(int e) {
        int i = 0;
        int f = 0;
        while (f < e) {
            i++;
            f = getFact(i);
        }
        if (f != e) {
            return new int[0];
        }
        return new int[]{e, i};
    }

    public static int getFact(int n) {
        return n == 1 ? 1 : n * getFact(n - 1);
    }

    // 6

    public static String fractions(String decimalFraction) {
        Pattern pattern = Pattern.compile("(\\d*).(\\d*)\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(decimalFraction);

        if (matcher.matches()) {
            String nonRepeatingPartC = matcher.group(1);
            String nonRepeatingPartD = matcher.group(2);
            String repeatingPart = matcher.group(3);
            int nonRepeatingPartValueC;
            int nonRepeatingPartValueD;

            try {
                nonRepeatingPartValueC = Integer.parseInt(nonRepeatingPartC);
            } catch (NumberFormatException e) {
                nonRepeatingPartValueC = 0;
            }
            try {
                nonRepeatingPartValueD = Integer.parseInt(nonRepeatingPartD);
            } catch (NumberFormatException e) {
                nonRepeatingPartValueD = 0;
            }

            int repeatingPartValue = Integer.parseInt(repeatingPart);

            //x=0.(6)
            //10x=6.(6)
            //9x = 6
            int denominator = ((int) Math.pow(10, repeatingPart.length()) - 1) * ((int) Math.pow(10, nonRepeatingPartD.length()));
            int numerator = nonRepeatingPartValueC * denominator + nonRepeatingPartValueD * ((int) Math.pow(10, repeatingPart.length()) - 1) + repeatingPartValue;

            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;

            return numerator + "/" + denominator;
        }

        return "Неправильный формат десятичной дроби";
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // 7
    public static String pilish_string(String s) {
        int[] pi = new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        String fin = "";
        if (s.equals(fin)) {
            return "";
        }
        int i = 0;
        int start = 0;
        while (i < pi.length && start < s.length()) {
            if (start + pi[i] > s.length()) {
                String str = s.substring(start);
                int count = pi[i] - str.length();
                for (int j = 0; j < count; j++) {
                    str += str.charAt(str.length() - 1);
                }
                start += pi[i];
                fin += str;
            } else {
                fin = fin + s.substring(start, start + pi[i]) + " ";
                start = start + pi[i];
                i++;
            }

        }
        return fin.trim();
    }

    //8
    public static int generateNonconsecutive(String s) throws Exception {
        while (s.contains("(")) {
            if (s.indexOf("(") < s.lastIndexOf(")")) {
                String staples = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));

                int res = generateNonconsecutive(staples);
                s = s.replace(staples, Integer.toString(res));
            } else {
                String subStr1 = s.substring(0, s.lastIndexOf(")"));
                int a1 = generateNonconsecutive(subStr1);
                String subStr2 = s.substring(s.indexOf("(") + 1);
                int a2 = generateNonconsecutive(subStr2);
                s = s.replace(subStr1, Integer.toString(a1));
                s = s.replace(subStr2, Integer.toString(a2));
            }
            s = s.replace("(", "");
            s = s.replace(")", "");
        }

        String[] seq = s.split(" ");
        List<String> list = new ArrayList<>();
        Arrays.stream(seq).forEach(a -> list.add(a));
        while (list.contains("*")) {
            int i = list.indexOf("*");
            int left = 0;
            if (i - 1 > -1) {
                left = parser(list.get(i - 1));
            }
            int right = 0;
            if (i + 1 < list.size()) {
                right = parser(list.get(i + 1));
            }
            int res = left * right;
            if (i + 1 < list.size()) {
                list.remove(i+1);
            }
            list.remove(i);
            if (i - 1 > -1) {
                list.remove(i-1);
                list.add(i - 1, Integer.toString(res));
            } else list.add(i, Integer.toString(res));
        }
        while (list.contains("/")) {
            int i = list.indexOf("/");
            int left = 0;
            if (i - 1 > -1) {
                left = parser(list.get(i - 1));
            }
            int right = 0;
            if (i + 1 < list.size()) {
                right = parser(list.get(i + 1));
            }
            if (right == 0) {
                throw new RuntimeException("деление на ноль");
            }
            int res = left / right;
            if (i + 1 < list.size()) {
                list.remove(i+1);
            }
            list.remove(i);
            if (i - 1 > -1) {
                list.remove(i-1);
                list.add(i - 1, Integer.toString(res));
            } else list.add(i, Integer.toString(res));
        }
        while (list.contains("+")) {
            int i = list.indexOf("+");

            int left = 0;
            if (i - 1 > -1) {
                left = parser(list.get(i - 1));
            }
            int right = 0;
            if (i + 1 < list.size()) {
                right = parser(list.get(i + 1));
            }
            int res = left + right;
            if (i + 1 < list.size()) {
                list.remove(i+1);
            }
            list.remove(i);
            if (i - 1 > -1) {
                list.remove(i-1);
                list.add(i - 1, Integer.toString(res));
            } else list.add(i, Integer.toString(res));
        }
        while (list.contains("-")) {
            int i = list.indexOf("-");
            int left = 0;
            int right = 0;
            if (i - 1 > -1) {
                left = parser(list.get(i - 1));
            }
            if (i + 1 < list.size()) {
                right = parser(list.get(i + 1));
            }
            int res = left - right;

            if (i + 1 < list.size()) {
                list.remove(i+1);
            }
            list.remove(i);
            if (i - 1 > -1) {
                list.remove(i-1);
                list.add(i - 1, Integer.toString(res));
            } else list.add(i, Integer.toString(res));

        }
        return Integer.parseInt(list.get(0));
    }

    public static int parser(String num) {
        int i;
        try {
            i = Integer.parseInt(num);
        } catch (Exception e){
            i = 0;
        }
        return i;
    }

    //9
    private static String isValid(String str) {
        // Посчитали каждый символ
        Collection<Long> counting = str.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values();

        Long minValue = Collections.min(counting);
        boolean isAllValueEquals = true;
        for (Long value : counting) {
            if (!value.equals(minValue)) {
                if (!isAllValueEquals) {
                    return "NO";
                }

                if (value - minValue == 1) {
                    isAllValueEquals = false;
                } else {
                    return "NO";
                }
            }
        }

        return "YES";
    }

    //10
    public static String findLCS(String first, String second) {
        int m = first.length();
        int n = second.length();
        char[] X = first.toCharArray();
        char[] Y = second.toCharArray();
        int L[][] = new int[m + 1][n + 1];
        List<Character> path = new ArrayList<>();

        /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1]) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);
            }
        }

        int j = n;
        int i = m;
        while (i != 1 || j != 1) {
            if (L[i - 1][j] < L[i][j] && L[i][j - 1] < L[i][j]) {
                path.add(X[i - 1]);
            }
            if (i == 1) {
                j--;
            } else if (j == 1) {
                i--;
            } else if (L[i - 1][j] == L[i][j]) {
                i--;
            } else if (L[i][j - 1] == L[i][j]) {
                j--;
            } else if (L[i - 1][j] > L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        if (L[0][1] < L[1][1] && L[1][0] < L[1][1]) {
            path.add(X[0]);
        }

        StringBuilder result = new StringBuilder();
        path.forEach(result::append);
        return result.reverse().toString();
    }

}
