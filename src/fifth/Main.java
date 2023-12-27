package fifth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println("______________________________________");
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
        System.out.println(spiderVsFly("C2", "A4"));
        System.out.println(spiderVsFly("A2", "C4"));
        System.out.println("______________________________________");
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));
        System.out.println("______________________________________");
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println("______________________________________");
        Arrays.stream(sumsUp(new int[]{1, 2, 3, 4, 5})).forEach(a -> System.out.print(Arrays.toString(a)));
        System.out.println();
        Arrays.stream(sumsUp(new int[]{1, 2, 3, 7, 9})).forEach(a -> System.out.print(Arrays.toString(a)));
        System.out.println();
        Arrays.stream(sumsUp(new int[]{10, 9, 7, 2, 8})).forEach(a -> System.out.print(Arrays.toString(a)));
        System.out.println();
        Arrays.stream(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})).forEach(a -> System.out.print(Arrays.toString(a)));
        System.out.println();
        System.out.println("______________________________________");
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
        System.out.println("______________________________________");
        System.out.println(caesarCipher("encode", "hello world", 3));
        System.out.println(caesarCipher("decode", "almost last task!", 4));
        System.out.println("______________________________________");
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));
        System.out.println("______________________________________");
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("______________________________________");
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(123));
        System.out.println(isNew(213));
        System.out.println(isNew(1000));
        System.out.println(isNew(896));
        System.out.println(isNew(888));
    }

    //1 Создайте функцию, которая возвращает true, если две строки имеют один и тот же буквенный шаблон, и false в противном случае.
    public static boolean sameLetterPattern(String a, String b) {
        if (a.length() != b.length()) return false;
        Map<Character, Character> characterMap = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            if (!characterMap.containsKey(a.charAt(i))) {
                characterMap.put(a.charAt(i), b.charAt(i));
            } else {
                if (!characterMap.get(a.charAt(i)).equals(b.charAt(i))) return false;
            }
        }
        return true;
    }

    //    2 Паутина определяется кольцами, пронумерованными от 0 до 4 от центра, и радиалами, помеченными по часовой стрелке сверху как A-H.
    //
    //Создайте функцию, которая принимает координаты паука и мухи и возвращает кратчайший путь для паука, чтобы добраться до мухи.
    public static String spiderVsFly(String spider, String fly) {
        if (spider.equals(fly)) return spider;
        String fin = spider;
        char radSp = spider.charAt(0);
        int cirSp = Integer.parseInt(String.valueOf(spider.charAt(1)));
        char radF = fly.charAt(0);
        int cirF = Integer.parseInt(String.valueOf(fly.charAt(1)));
        if (cirF > cirSp) {
            String[] points = spiderVsFly(fly, spider).split("-");
            String ret = "";
            for (String p : points) {
                if (ret.equals("")){
                    ret = p;
                }else ret = p + "-" + ret;
            }
            return ret;
        }
        while (cirSp > cirF) {
            cirSp--;
            fin = fin + "-" + radSp + cirSp;
        }

        // через центр
        double cent = cirSp + cirF;

        // по кругу
        double cir = 0;
        String stcir="";

        String left = "";
        String right = "";
        char radSpR = radSp;
        //right
        while (radSpR != radF) {
            if (radSpR == 'H') {
                radSpR = 'A';
            } else radSpR++;
            right = right + "-" + radSpR + cirSp;
        }
        //left
        while (radSp != radF) {
            if (radSp == 'A') {
                radSp = 'H';
            } else radSp--;
            left = left + "-" + radSp + cirSp;
        }

        if (left.length() < right.length()) {
            cir = left.length();
            stcir=left;
        } else {
            cir = right.length();
            stcir = right;
        }


        if (cent < cir) {
            while (cirSp > 1) {
                cirSp--;
                fin = fin + "-" + radSp + cirSp;
            }
            fin += "-A0";
            cirSp = 0;
            while (cirSp < cirF) {
                cirSp++;
                fin = fin + "-" + radF + cirSp;
            }
        } else {
            fin = fin + stcir;
        }
        return fin;
    }

    // 3 Создайте функцию, которая будет рекурсивно подсчитывать количество цифр числа.
    // Преобразование числа в строку не допускается, поэтому подход является рекурсивным.
    public static int digitsCount(long a) {
        return a < 10 ? 1 : digitsCount(a / 10) + 1;
    }

    // 4 Игроки пытаются набрать очки, формируя слова, используя буквы из 6-буквенного скремблированного слова.
    //Они выигрывают раунд, если им удается успешно расшифровать слово из 6 букв.

    public static int totalPoints(String[] guess, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : word.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else map.put(ch, 1);
        }
        int points = 0;
        for (String w : guess) {
            Map<Character, Integer> clone = new HashMap<>(Map.copyOf(map));
            boolean valid = true;
            for (Character c : w.toCharArray()) {
                if (clone.containsKey(c)) {
                    clone.put(c, clone.get(c) - 1);
                } else {
                    valid = false;
                    break;
                }
            }
            if (clone.containsValue(-1)) valid = false;
            if (valid) {
                if (w.length() == 6) points += 50;
                points += w.length() - 2;
            }
        }
        return points;
    }

    // 5
    public static int[][] sumsUp(int[] arr) {
        List<int[]> result = new ArrayList<>();

        Arrays.sort(arr); // Сортируем массив по возрастанию

        int start = 0; // Индекс начала
        int end = arr.length - 1; // Индекс конца

        while (start < end) {
            int sum = arr[start] + arr[end];

            if (sum == 8) { // Если сумма равна 8
                result.add(new int[]{arr[start], arr[end]});
                start++; // Переходим к следующей паре
                end--; // Переходим к предыдущей паре
            } else if (sum < 8) {
                start++; // Увеличиваем начальный индекс
            } else {
                end--; // Уменьшаем конечный индекс
            }
        }
        int[][] fin = new int[result.size()][2];
        int i = 0;
        for (int[] m : result) {
            fin[i] = m;
            i++;
        }

        return fin;
    }

    // 6 Какой процент вы можете набрать на тесте, который в одиночку снижает средний балл по классу на 5%?
    // Учитывая массив оценок ваших одноклассников, создайте функцию, которая возвращает ответ.
    // Округлите до ближайшего процента.

    public static String takeDownAverage(String[] score) {
        int sum = 0;
        for (int i = 0; i < score.length; i++) {
            int t = Integer.parseInt(score[i].replace("%", ""));
            sum += t;
        }
        return (int) (((double) sum / score.length - 5) * (score.length + 1) - sum) + "%";
    }

    //7 Создайте функцию, которая будет шифровать и дешифровать сообщения с использованием шифра Цезаря.

    public static String caesarCipher(String mode, String sentence, int a) {
        String fin = "";
        if (mode.equals("decode")) a = -a;
        else if (!mode.equals("encode")) {
            throw new RuntimeException("Invalid mode");
        }
        for (char c : sentence.toUpperCase().toCharArray()) {
            int a1 = a;
            if (Character.isAlphabetic(c)) {
                if (c + a1 > 'Z') {
                    a1 = a1 - 'Z' + c - 1;
                    c = 'A';
                } else if (c + a1 < 'A') {
                    a1 = c - 'A' + a1 + 1;
                    c = 'Z';
                }
                fin = fin + (char) (c + a1);
            } else fin += c;
        }
        return fin;
    }

    // 8 Создайте метод для рекурсивного вычисления количества различных способов
    // как можно разместить k элементов из множества из n элементов без повторений

    public static int setSetup(int n, int k) {
        if (n < k) throw new RuntimeException("Invalid data");
        return k == 0 ? 1 : n * setSetup(n - 1, k - 1);
    }

    // 9 В этой задаче цель состоит в том, чтобы вычислить, сколько времени сейчас в двух разных городах.
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        // Задаем формат даты
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-M-d HH:mm");

        Map<String, String> timeZ = new HashMap<>();
        timeZ.put("Los Angeles", "GMT-8:00");
        timeZ.put("New York", "GMT-5:00");
        timeZ.put("Caracas", "GMT-4:30");
        timeZ.put("Buenos Aires", "GMT-3:00");
        timeZ.put("London", "GMT+0:00");
        timeZ.put("Rome", "GMT+1:00");
        timeZ.put("Moscow", "GMT+3:00");
        timeZ.put("Tehran", "GMT+3:30");
        timeZ.put("New Delhi", "GMT+5:30");
        timeZ.put("Beijing", "GMT+8:00");
        timeZ.put("Canberra", "GMT+10:00");

        // Устанавливаем часовой пояс для города cityA
        TimeZone timeZoneA = TimeZone.getTimeZone(timeZ.get(cityA));
        inputFormat.setTimeZone(timeZoneA);

        // Устанавливаем часовой пояс для города cityB
        TimeZone timeZoneB = TimeZone.getTimeZone(timeZ.get(cityB));
        outputFormat.setTimeZone(timeZoneB);

        try {
            // Преобразуем входную строку времени в объект Date
            Date date = inputFormat.parse(timestamp);

            // Создаем календарь и устанавливаем дату и время
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Применяем часовой пояс cityA к календарю
            calendar.setTimeZone(timeZoneA);

            // Получаем дату и время в часовом поясе cityB
            return outputFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 10

    public static boolean isNew(int a) {
        String numberString = String.valueOf(a);
        for (int i = 1; i < a; i++) {
            int c = i;
            String string = String.copyValueOf(numberString.toCharArray());
            List<Integer> sim = new ArrayList<>();
            while (c > 0) {
                sim.add(c % 10);
                c = c / 10;
            }
            for (Integer d : sim) {
                string = string.replaceFirst(d.toString(), "");
            }
            if (string.isEmpty()) return false;
        }
        return true;
    }
}

