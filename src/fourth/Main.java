package fourth;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println("_______________________");
        System.out.println(Arrays.toString(generateBrackets(1)));
        System.out.println(Arrays.toString(generateBrackets(2)));
        System.out.println(Arrays.toString(generateBrackets(3)));
        System.out.println("________________________");
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));
        System.out.println("_________________________");
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));
        System.out.println(alphabeticRow("klmabzyzyxw"));
        System.out.println("_________________________");
        System.out.println(alphCount("aaabbcdd"));
        System.out.println(alphCount("vvvvaajaaaaa"));
        System.out.println("_________________________");
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));
        System.out.println(convertToNum("nine hundred ninety nine"));
        System.out.println(convertToNum("nine hundred nine"));
        System.out.println("_________________________");
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));
        System.out.println(uniqueSubstring("77897891238"));
        System.out.println("_________________________");
        System.out.println(shortestWay(new int[][]{{1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}}));
        System.out.println(shortestWay(new int[][]{{2, 7, 3}, {1, 4, 8}, {4, 5, 9}}));
        System.out.println("_________________________");
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println("_________________________");
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));


    }

    /*
    1. Напишите рекурсивную функцию, которая принимает строку и удаляет из неё повторяющиеся символы.
    Функция должна вернуть строку, в которой каждый символ встречается только один раз.
    */
    public static String nonRepeatable(String s) {
        if (!s.isEmpty()) {
            String sim = Character.toString(s.charAt(0));
            return sim + nonRepeatable(s.replace(sim, ""));//a+b+r+ ...+d+""
        } else return "";
    }

    /*
    2. Напишите функцию, которая генерирует все возможные правильные комбинации пар скобок для заданного числа n.
     */

    public static String[] generateBrackets(int n) {
        List<String> combinations = new ArrayList<>();
        backtrack("", 0, 0, n, combinations);
        String[] s = new String[combinations.size()];
        int i = 0;
        for (String el : combinations) {
            s[i] = el;
            i++;
        }
        return s;
    }

    public static void backtrack(String com, int open, int close, int n, List<String> combinations) {
        if (com.length() == n * 2) {
            combinations.add(com);
        } else {
            if (open < n) {
                backtrack(com + "(", open + 1, close, n, combinations);
            }
            if (close < open) {
                backtrack(com + ")", open, close + 1, n, combinations);
            }
        }
    }

    /*
    3. Напишите функцию, которая генерирует все возможные бинарные комбинации длины n,
     в которых не может быть соседствующих нулей.
     */
    public static String[] binarySystem(int n) {
        List<String> combinations = new ArrayList<>();
        backtrack("", n, combinations);
        String[] s = new String[combinations.size()];
        int i = 0;
        for (String el : combinations) {
            s[i] = el;
            i++;
        }
        return s;
    }

    public static void backtrack(String com, int n, List<String> combinations) {
        if (com.length() == n) {
            combinations.add(com);
        } else {
            // Проверяем, можно ли добавить 0 к текущей комбинации
            if (!com.endsWith("0")) {
                backtrack(com + "0", n, combinations);
            }
            // Всегда можно добавить 1
            backtrack(com + "1", n, combinations);
        }
    }

    /*
    4. Реализуйте функцию, которая принимает строку и возвращает длину самого длинного последовательного ряда в этом массиве.
     Последовательный ряд – это список соседних элементов, идущих подряд в алфавитном порядке,
     который может быть как увеличивающимся, так и уменьшающимся.
     */
    public static String alphabeticRow(String str) {
        String longestStr = "";
        StringBuilder currentStr = new StringBuilder();
        currentStr.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1) + 1 || str.charAt(i) == str.charAt(i - 1) - 1) {
                currentStr.append(str.charAt(i));
            } else {
                if (longestStr.length() < currentStr.length()) {
                    longestStr = currentStr.toString();
                }
                currentStr = new StringBuilder();
                currentStr.append(str.charAt(i));
            }
        }
        if (longestStr.length() < currentStr.length()) {
            longestStr = currentStr.toString();
        }
        return longestStr;
    }

    /*
    5. Напишите функцию, которая принимает строку и подсчитывает количество идущих подряд символов,
    заменяя соответствующим числом повторяющиеся символы.
    Отсортируйте строку по возрастанию длины буквенного паттерна.
     */
    public static String alphCount(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : string.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        StringBuilder res = new StringBuilder();
        List<Integer> values = new ArrayList<>(map.values());//2 3 4
        Collections.sort(values);
        while (!values.isEmpty() && !map.isEmpty()) {
            for (Character key : map.keySet()) {
                if (map.get(key).equals(values.get(0))) {
                    res.append(key);
                    res.append(values.get(0));
                    map.remove(key);
                    values.remove(0);
                    break;
                }
            }
        }
        return res.toString();
    }

    /*
    6. Напишите функцию, принимающую положительное целое число в строковом формате,
     не превышающее 1000, и возвращающую его целочисленное представление.
     */
    public static int convertToNum(String numberString) {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);
        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninety", 90);
        numberMap.put("hundred", 100);

        String[] words = numberString.split(" ");
        int current = 0;

        for (String word : words) {
            int value = numberMap.get(word);

            if (value == 100) {
                current = current * value;
            } else {
                current += value;
            }
        }

        return current;
    }

    /*
    7. Напишите функцию, принимающую строку цифр,
     выполняющую поиск подстроки максимальной длины с уникальными элементами.
     Если найдено несколько подстрок одинаковой длины, верните первую.
     */
    public static String uniqueSubstring(String str) {
        String longestSubstring = "";
        Set<Character> uniqueSet = new HashSet<>();
        int startIndex = 0;

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            while (uniqueSet.contains(currentChar)) {
                uniqueSet.remove(str.charAt(startIndex));
                startIndex++;
            }

            uniqueSet.add(currentChar);

            if (i - startIndex + 1 > longestSubstring.length()) {
                longestSubstring = str.substring(startIndex, i + 1);
            }
        }

        return longestSubstring;
    }

    /*
    8. Напишите функцию поисковик наименьшего матричного пути.
    На вход поступает двумерный массив, размера n x n,
    ваша задача найти путь с минимальной суммой чисел, передвигаясь только вправо или вниз.
     */
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // Инициализация первой строки и первого столбца
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        // Нахождение наименьшего пути для остальных ячеек
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n - 1][n - 1];
    }

    /*
    9. Создайте функцию, принимающую строку, содержащую числа внутри слов.
     Эти числа представляют расположение слова для новой строящейся строки.
     */

    public static String numericOrder(String s) {
        String[] words = s.split(" ");
        StringBuilder fin = new StringBuilder();
        for (int i = 1; i <= words.length; i++) {
            for (String subStr : words) {
                if (subStr.contains(Integer.toString(i))) {
                    subStr = subStr.replace(Integer.toString(i), "");
                    fin.append(subStr);
                }
            }
            fin.append(" ");
        }
        return fin.toString();
    }

    /*
    10. Напишите функцию, принимающую два числа,
     которая делает второе число максимально возможным за счет замены своих элементов элементами первого числа.
     Брать цифру можно только один раз.
     */

    public static int switchNums(int a, int b) {
        List<Integer> numsA = new ArrayList<>();
        List<Integer> numsB = new ArrayList<>();

        while (a > 0) {
            numsA.add(a % 10);
            a = a / 10;
        }
        while (b > 0) {
            numsB.add(b % 10);
            b = b / 10;
        }

        int fin = 0;

        for (int i = numsB.size() - 1; i >= 0; i--) {
            int add = numsB.get(i);
            int ind = 0;
            if (!numsA.isEmpty()) {
                for (int j = 0; j < numsA.size(); j++) {
                    if (numsA.get(j) > add) {
                        add = numsA.get(j);
                        ind = j;
                    }
                }
                numsA.remove(ind);
            }
            fin = fin * 10 + add;
        }
        return fin;
    }
}
