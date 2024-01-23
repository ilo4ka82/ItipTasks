import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.math.BigInteger;
import java.util.Stack;
import java.util.Map;

public class task6 {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("---");

        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));
        System.out.println("---");

        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println("---");

        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5,  6, 7, 8, 9, 10}, 10)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println("---");

        System.out.println(java.util.Arrays.toString(isExact(6)));
        System.out.println(java.util.Arrays.toString(isExact(24)));
        System.out.println(java.util.Arrays.toString(isExact(125)));
        System.out.println(java.util.Arrays.toString(isExact(720)));
        System.out.println(java.util.Arrays.toString(isExact(1024)));
        System.out.println(java.util.Arrays.toString(isExact(40320)));
        System.out.println("---");

        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println("---");

        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println("---");

        System.out.println(evaluateExpression("3 + 5 * (2 - 6)"));
        System.out.println(evaluateExpression("6 - 18 / (4 - 1)"));
        System.out.println(evaluateExpression("6 / 0"));
        System.out.println("---");

        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println("---");

        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
        System.out.println("---");


    }
    // Задание 1⦁	Создайте функцию, которая принимает две строки. Первая строка содержит предложение, содержащее буквы второй строки в последовательной последовательности,
    // но в другом порядке. Скрытая анаграмма должна содержать все буквы, включая дубликаты, из второй строки в любом порядке и не должна содержать никаких других букв алфавита.

    public static String hiddenAnagram(String text, String anagram) {
        String cleanedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedAnagram = anagram.replaceAll("[^a-zA-Z]", "").toLowerCase();

        HashMap<Character, Integer> anagramMap = new HashMap<>();
        for (char c : cleanedAnagram.toCharArray()) {
            anagramMap.put(c, anagramMap.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0;
        int lettersToMatch = cleanedAnagram.length();
        for (int windowEnd = 0; windowEnd < cleanedText.length(); windowEnd++) {
            char endChar = cleanedText.charAt(windowEnd);
            if (anagramMap.containsKey(endChar)) {
                anagramMap.put(endChar, anagramMap.get(endChar) - 1);
                if (anagramMap.get(endChar) >= 0) {
                    lettersToMatch--;
                }
            }

            if (lettersToMatch == 0) {
                return cleanedText.substring(windowStart, windowEnd + 1);
            }

            if (windowEnd >= cleanedAnagram.length() - 1) {
                char startChar = cleanedText.charAt(windowStart++);
                if (anagramMap.containsKey(startChar)) {
                    if (anagramMap.get(startChar) >= 0) {
                        lettersToMatch++;
                    }
                    anagramMap.put(startChar, anagramMap.get(startChar) + 1);
                }
            }
        }

        return "notfound"; // worldevolvesin, noldwestactio, mrmojorisin, anamarg, debitcard, notfound
    }
    // Задание 2⦁	Напишите функцию, которая возвращает массив строк,
    // заполненных из срезов символов n-длины данного слова (срез за другим, в то время как n-длина применяется к слову).
    public static List<String> collect(String s, int n) {
        if (s.length() < n) {
            return new ArrayList<>();
        }

        List<String> slices = collect(s.substring(n), n);
        slices.add(s.substring(0, n));
        if (slices.size() == 1) { // сортировка для уменьшения кол-во операций
            Collections.sort(slices);
        }
        return slices; // [ationa, ntalis, ontine, interc], [ths, eng, str], [volcanoconiosis, croscopicsilico, pneumonoultrami]
    }
    // Задание 3⦁	В шифре Nico кодирование осуществляется путем создания цифрового ключа и
    // присвоения каждой буквенной позиции сообщения с помощью предоставленного ключа.
    public static String nicoCipher(String message, String key) {
        List<Pair> keyIndexPairs = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            keyIndexPairs.add(new Pair(key.charAt(i), i));
        }

        Collections.sort(keyIndexPairs);

        while (message.length() % key.length() != 0) {
            message += " ";
        }

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < message.length(); i += key.length()) {
            char[] block = new char[key.length()];
            for (int j = 0; j < key.length(); j++) {
                int originalIndex = keyIndexPairs.get(j).index;
                block[j] = message.charAt(i + originalIndex);
            }
            for (char c : block) {
                encoded.append(c); // добавление символов в encoded
            }
        }

        return encoded.toString();
    }
    // Pair для хранения пар символов
    static class Pair implements Comparable<Pair> {
        char character;
        int index;
        Pair(char character, int index) {
            this.character = character;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            return Character.compare(this.character, other.character); // yowmledrovlvsnieesrh, lnidaevheo s or, bmusarhiahass n, deabtiismaaznig, lovehir    e
        }
    }
    // Задание 4⦁	Создайте метод, который принимает массив arr и число n и
    // возвращает массив из двух целых чисел из arr, произведение которых равно числу n следующего вида:
    public static int[] twoProduct(int[] arr, int n) {
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (n % value == 0 && set.contains(n / value)) {
                return new int[]{n / value, value};
            }
            set.add(value);
        }
        return new int[]{}; // [9, 5], [3, 15], [4, 5], [2, 5], []
    }
    // Задание 5⦁	Создайте рекурсивную функцию, которая проверяет, является ли число точной верхней границей факториала n.
    // Если это так, верните массив точной факториальной границы и n, или иначе, пустой массив.
    public static int[] isExact(int number) {
        return checkFactorial(number, 1, 1);
    }

    private static int[] checkFactorial(int number, int factorial, int n) {
        if (factorial == number) {
            return new int[]{number, n};
        } else if (factorial > number) {
            return new int[]{};
        }
        return checkFactorial(number, factorial * (n + 1), n + 1); // [6, 3], [24, 4], [], [720, 6], [], [40320, 8]
    }
    // Задание 6 Создайте функцию, которая принимает десятичную дробь в строковой форме с повторяющейся частью в круглых скобках
    // и возвращает эквивалентную дробь в строковой форме и в наименьших членах.
    public static String fractions(String number) {
        int indexOfBracket = number.indexOf('(');
        if (indexOfBracket == -1) {
            return "Invalid input";
        }

        String nonRepeating = number.substring(0, indexOfBracket);
        String repeating = number.substring(indexOfBracket + 1, number.length() - 1);

        int nonRepeatingLength = nonRepeating.length() - nonRepeating.indexOf('.') - 1;
        int repeatingLength = repeating.length();

        BigInteger nonRepeatingPart = new BigInteger(nonRepeating.replace(".", ""));
        BigInteger repeatingPart = new BigInteger(repeating);

        BigInteger numerator = nonRepeatingPart.multiply(BigInteger.TEN.pow(repeatingLength))
                .add(repeatingPart)
                .subtract(nonRepeatingPart);
        BigInteger denominator = BigInteger.TEN.pow(nonRepeatingLength + repeatingLength)
                .subtract(BigInteger.TEN.pow(nonRepeatingLength));


        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);

        return numerator + "/" + denominator; // 2/3, 10/9, 22/7, 5343/27775, 823/7500
    }

    // Задание 7 ⦁	В этой задаче преобразуйте строку в серию слов (или последовательности символов), разделенных одним пробелом,
    // причем каждое слово имеет одинаковую длину, заданную первыми 15 цифрами десятичного представления числа Пи:
    public static String pilish_string(String txt) {

        int[] piDigits = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        StringBuilder result = new StringBuilder();
        int charIndex = 0;

        for (int digit : piDigits) {
            if (charIndex >= txt.length()) {
                break;
            }

            if (result.length() > 0) { // если есть символы, то добавляется пробел
                result.append(" ");
            }

            for (int i = 0; i < digit; i++) {
                if (charIndex + i < txt.length()) {
                    result.append(txt.charAt(charIndex + i));
                } else {
                    result.append(txt.charAt(txt.length() - 1));
                }
            }

            charIndex += digit;
        }

        return result.toString(); // 333 1 4444, TOP, XXX, ""
    }
    // Задание 8 Реализуйте алгоритм, который разбирает строку и вычисляет результат выражения, учитывая приоритет операций, скобки и т. д.
    // Математические операции, которые нужно поддерживать, включают в себя сложение, вычитание, умножение, деление и скобки.
    public static String evaluateExpression(String expression) {
        try {
            Stack<Double> numbers = new Stack<>();
            Stack<Character> operations = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == ' ') {
                    continue;
                }

                if (c >= '0' && c <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                        sb.append(expression.charAt(i++));
                    }
                    i--;
                    numbers.push(Double.parseDouble(sb.toString()));
                } else if (c == '(') {
                    operations.push(c);
                } else if (c == ')') {
                    while (operations.peek() != '(') {
                        numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.pop();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!operations.empty() && hasPrecedence(c, operations.peek())) {
                        numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.push(c);
                }
            }

            while (!operations.empty()) {
                numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
            }

            return numbers.pop().toString();
        } catch (Exception e) {
            return "Error: Invalid Expression";
        }
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    public static double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0; // -17, 0, Error: Invalid Expression
    }
    // Задание 9⦁	Шерлок считает строку действительной, если все символы строки встречаются одинаковое количество раз. Также допустимо, если он может удалить только 1 символ из 1 индекса в строке,
    // а остальные символы будут встречаться одинаковое количество раз. Для данной строки str определите, действительна ли она. Если да, верните «ДА», в противном случае верните «НЕТ».
    public static String isValid(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        Map<Integer, Integer> freqOfFreq = new HashMap<>();
        for (int freq : frequencyMap.values()) {
            freqOfFreq.put(freq, freqOfFreq.getOrDefault(freq, 0) + 1);
        }

        if (freqOfFreq.size() == 1) {
            return "YES";
        } else if (freqOfFreq.size() == 2) {
            if (freqOfFreq.containsKey(1) && freqOfFreq.get(1) == 1) {
                return "YES";
            }

            int f1 = 0, f2 = 0, countF1 = 0, countF2 = 0;
            for (Map.Entry<Integer, Integer> entry : freqOfFreq.entrySet()) {
                if (f1 == 0) {
                    f1 = entry.getKey();
                    countF1 = entry.getValue();
                } else {
                    f2 = entry.getKey();
                    countF2 = entry.getValue();
                }
            }

            if ((countF1 == 1 && (f1 - 1 == f2 || f1 == 1)) || (countF2 == 1 && (f2 - 1 == f1 || f2 == 1))) {
                return "YES";
            }
        }

        return "NO"; // NO, NO, YES
    }
    // Задание 10 Создайте функцию, которая будет находить наибольшую общую подпоследовательность (LCS) для двух строк.
    // LCS – это самая длинная последовательность символов, которая встречается как подпоследовательность в обеих строках.
    // Эта задача требует понимания алгоритма динамического программирования для нахождения наибольшей общей подпоследовательности и его эффективной реализации.
    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int index = dp[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs); // bd, gtab
    }
}