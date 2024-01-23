import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class task4 {

    // Тестирование всех функций
    public static void main(String[] args) {
        // Задание 1: Удаление повторяющихся символов
        System.out.println(nonRepeatable("abracadabra")); // ➞ "abrcd"
        System.out.println(nonRepeatable("paparazzi")); // ➞ "parzi"

        // Задание 2: Генерация скобок
        System.out.println(Arrays.toString(generateBrackets(3).toArray())); // ➞ ["((()))", "(()())", "(())()", "()(())", "()()()"]

        // Задание 3: Генерация бинарных последовательностей
        System.out.println(Arrays.toString(binarySystem(3).toArray())); // ➞ ["010", "011", "101", "110", "111"]

        // Задание 4: Самый длинный алфавитный ряд
        System.out.println(alphabeticRow("abcdjuwx")); // ➞ "abcd"
        System.out.println(alphabeticRow("klmabzyxw")); // ➞ "zyxw"

        // Задание 5: Сжатие строки и сортировка
        System.out.println(compressAndSortString("aaabbcdd")); // ➞ "c1b2d2a3"
        System.out.println(compressAndSortString("vvvvaajaaaaa")); // ➞ "j1a2v4a5"

        // Задание 6: Конвертация слов в числа
        System.out.println(convertToNum("eight")); // ➞ 8
        System.out.println(convertToNum("five hundred sixty seven")); // ➞ 567
        System.out.println(convertToNum("thirty one")); // ➞ 31

        // Задание 7: Нахождение уникальной подстроки
        System.out.println(uniqueSubstring("123412324")); // ➞ "1234"
        System.out.println(uniqueSubstring("111111")); // ➞ "1"
        System.out.println(uniqueSubstring("77897898")); // ➞ "789"

        // Задание 8: Наименьший матричный путь
        int[][] matrix1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(shortestWay(matrix1)); // ➞ 7
        int[][] matrix2 = {{2, 7, 3}, {1, 4, 8}, {4, 5, 9}};
        System.out.println(shortestWay(matrix2)); // ➞ 21

        // Задание 9: Упорядочивание слов по числам
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng")); // ➞ "One ring to rule them all"
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat")); // ➞ "With great power comes great responsibility"

        // Задание 10: Максимизация числа
        System.out.println(switchNums(519, 723)); // ➞ 953
        System.out.println(switchNums(491, 3912)); // ➞ 9942
        System.out.println(switchNums(6274, 71259)); // ➞ 77659
    }

    // Задание 1: Функция для удаления повторяющихся символов

    // Вспомогательный метод для удаления повторяющихся символов
    private static String removeDuplicates(String str, char[] seen, int index) {
        if (index == str.length()) { // Базовый случай рекурсии
            return "";
        }
        char currentChar = str.charAt(index);
        if (seen[currentChar] == 0) { // Если символ еще не встречался
            seen[currentChar] = 1; // Помечаем символ как встреченный
            return currentChar + removeDuplicates(str, seen, index + 1); // Включаем символ в результат
        } else {
            return removeDuplicates(str, seen, index + 1); // Пропускаем символ
        }
    }

    // Публичный метод для удаления повторяющихся символов
    public static String nonRepeatable(String str) {
        return removeDuplicates(str, new char[256], 0); // Вызываем вспомогательный метод
    }



    // Задание 2: Функция для генерации скобок

    // Вспомогательный метод для генерации скобок
    private static void generateBracketsHelper(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) { // Если строка достигла максимально возможной длины
            result.add(current); // Добавляем текущую комбинацию в результат
            return;
        }

        if (open < max) { // Если мы можем добавить открытую скобку
            generateBracketsHelper(result, current + "(", open + 1, close, max);
        }
        if (close < open) { // Если мы можем добавить закрытую скобку
            generateBracketsHelper(result, current + ")", open, close + 1, max);
        }
    }

    // Публичный метод для генерации скобок
    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBracketsHelper(result, "", 0, 0, n);
        return result;
    }

    // Задание 3: Функция для генерации бинарных последовательностей

    // Вспомогательный метод для генерации бинарных комбинаций
    private static void binarySystemHelper(List<String> result, String current, int n, char lastChar) {
        if (current.length() == n) { // Если текущая строка достигла требуемой длины
            result.add(current); // Добавляем её в результат
            return;
        }

        // Добавляем "1" и продолжаем рекурсию
        binarySystemHelper(result, current + "1", n, '1');

        // Если последний добавленный символ не "0", добавляем "0" и продолжаем рекурсию
        if (lastChar != '0') {
            binarySystemHelper(result, current + "0", n, '0');
        }
    }

    // Публичный метод для генерации бинарных комбинаций
    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        binarySystemHelper(result, "", n, ' '); // Пробел указывает, что предыдущего символа не было
        return result;
    }



    // Задание 4: Функция для нахождения самого длинного алфавитного ряда

    // Функция для поиска самой длинной последовательности
    public static String alphabeticRow(String s) {
        String longest = ""; // Самая длинная последовательность
        String incr = ""; // Возрастающая последовательность
        String decr = ""; // Убывающая последовательность

        // Инициализация первым символом
        incr += s.charAt(0);
        decr += s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            // Проверка для возрастающей последовательности
            if (curr == prev + 1) {
                incr += curr;
            } else {
                incr = "" + curr;
            }

            // Проверка для убывающей последовательности
            if (curr == prev - 1) {
                decr += curr;
            } else {
                decr = "" + curr;
            }

            // Обновление самой длинной последовательности
            if (incr.length() > longest.length()) {
                longest = incr;
            }
            if (decr.length() > longest.length()) {
                longest = decr;
            }
        }

        return longest;
    }



    // Задание 5: Функция для сжатия и сортировки строки

    // Функция для подсчета идущих подряд символов и их сортировки
    public static String compressAndSortString(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        // Подсчет идущих подряд символов
        for (int i = 1; i <= str.length(); i++) {
            if (i == str.length() || str.charAt(i) != str.charAt(i - 1)) {
                compressed.append(str.charAt(i - 1)).append(count);
                count = 1; // Сброс счетчика
            } else {
                count++;
            }
        }

        // Получение списка отдельных групп символов
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < compressed.length(); i += 2) {
            groups.add(compressed.substring(i, i + 2));
        }

        // Сортировка списка по длине буквенного паттерна
        Collections.sort(groups, (a, b) -> {
            int comp = Integer.compare(a.charAt(1), b.charAt(1));
            if (comp == 0) {
                return a.charAt(0) - b.charAt(0);
            }
            return comp;
        });

        // Сборка отсортированной строки
        StringBuilder sortedCompressed = new StringBuilder();
        for (String group : groups) {
            sortedCompressed.append(group);
        }

        return sortedCompressed.toString();
    }



    // Задание 6: Функция для конвертации слов в числа

    // Словарь для чисел
    private static final Map<String, Integer> numbers = new HashMap<>();
    static {
        numbers.put("zero", 0);
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
        numbers.put("ten", 10);
        numbers.put("eleven", 11);
        numbers.put("twelve", 12);
        numbers.put("thirteen", 13);
        numbers.put("fourteen", 14);
        numbers.put("fifteen", 15);
        numbers.put("sixteen", 16);
        numbers.put("seventeen", 17);
        numbers.put("eighteen", 18);
        numbers.put("nineteen", 19);
        numbers.put("twenty", 20);
        numbers.put("thirty", 30);
        numbers.put("forty", 40);
        numbers.put("fifty", 50);
        numbers.put("sixty", 60);
        numbers.put("seventy", 70);
        numbers.put("eighty", 80);
        numbers.put("ninety", 90);
        numbers.put("hundred", 100);
        numbers.put("thousand", 1000);
    }

    // Функция для конвертации словесного числа в целочисленное
    public static int convertToNum(String wordNum) {
        int result = 0;
        int temp = 0;

        // Разделение строки на слова
        String[] words = wordNum.split(" ");

        for (String word : words) {
            // Если слово соответствует числу
            if (numbers.containsKey(word)) {
                temp += numbers.get(word);
            } else if (word.equals("hundred") && temp != 0) {
                temp *= numbers.get(word);
            } else if (word.equals("thousand")) {
                result += temp * numbers.get(word);
                temp = 0;
            }
        }

        result += temp;
        return result;
    }

    // Задание 7: Функция для поиска уникальной подстроки

    // Функция для поиска подстроки с уникальными элементами
    public static String uniqueSubstring(String s) {
        HashMap<Character, Integer> seen = new HashMap<>();
        String output = "";
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (seen.containsKey(currentChar)) {
                start = Math.max(start, seen.get(currentChar) + 1);
            }

            if (output.length() < end - start + 1) {
                output = s.substring(start, end + 1);
            }

            seen.put(currentChar, end);
        }

        return output;
    }

    // Задание 8: Функция для нахождения наименьшего матричного пути

    // Функция для нахождения наименьшего матричного пути
    public static int shortestWay(int[][] grid) {
        int n = grid.length;

        // Копирование первой строки и первого столбца как есть, так как до них можно дойти только одним способом
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
            grid[i][0] += grid[i - 1][0];
        }

        // Вычисление минимального пути для остальных ячеек
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        // Возвращение значения из правого нижнего угла, так как оно представляет минимальный путь
        return grid[n - 1][n - 1];
    }




    // Задание 9: Функция для упорядочивания слов по встроенным числам

    // Функция для упорядочивания слов по встроенным числам
    public static String numericOrder(String str) {
        // Разделяем строку на слова
        String[] words = str.split(" ");
        // Создаем массив для упорядоченных слов
        String[] ordered = new String[words.length];

        // Паттерн для поиска чисел в слове
        Pattern p = Pattern.compile("\\d+");

        for (String word : words) {
            Matcher m = p.matcher(word);
            m.find();
            // Извлекаем число и используем его для расстановки слов на правильные позиции
            int index = Integer.parseInt(m.group()) - 1; // Вычитаем 1, потому что индексы начинаются с 0
            ordered[index] = word.replaceAll("\\d", ""); // Удаляем числа из слова
        }

        // Собираем слова в результирующую строку
        return String.join(" ", ordered);
    }


    // Задание 10: Функция для максимизации числа

    // Функция для замены чисел для получения максимального числа
    public static int switchNums(int num1, int num2) {
        // Преобразование первого числа в массив цифр и сортировка
        char[] digits1 = Integer.toString(num1).toCharArray();
        Arrays.sort(digits1);

        // Преобразование второго числа в массив цифр
        char[] digits2 = Integer.toString(num2).toCharArray();

        // Флаги использования цифр первого числа
        boolean[] used = new boolean[digits1.length];

        // Поиск цифр для замены
        for (int i = 0; i < digits2.length; i++) {
            for (int j = digits1.length - 1; j >= 0; j--) {
                if (!used[j] && digits2[i] < digits1[j]) {
                    digits2[i] = digits1[j]; // Замена цифры
                    used[j] = true; // Отметка использования цифры
                    break;
                }
            }
        }

        // Преобразование обновлённого массива цифр обратно в число
        return Integer.parseInt(new String(digits2));
    }
}

