public class task2 {

    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[]{1, 3}));
        // ... и так далее для других функций
    }

    public static String repeat(String str, int n) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            for (int i = 0; i < n; i++) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static int differenceMaxMin(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min;
    }

    public static boolean isAvgWhole(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum % arr.length == 0;
    }

    public static int[] cumulativeSum(int[] arr) {
        int sum = 0;
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            result[i] = sum;
        }
        return result;
    }

    public static int getDecimalPlaces(String str) {
        if (str.contains(".")) {
            return str.length() - str.indexOf('.') - 1;
        }
        return 0;
    }

    public static int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static boolean isValid(String str) {
        return str.matches("\\d{5}");
    }

    public static boolean isStrangePair(String str1, String str2) {
        if (str1.isEmpty() && str2.isEmpty()) {
            return true;
        }
        return str1.length() > 0 && str2.length() > 0 &&
                str1.charAt(0) == str2.charAt(str2.length() - 1) &&
                str1.charAt(str1.length() - 1) == str2.charAt(0);
    }

    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(prefix.substring(0, prefix.length() - 1));
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.endsWith(suffix.substring(1));
    }

    public static int boxSeq(int step) {
        int result = 0;
        for (int i = 1; i <= step; i++) {
            if (i % 2 != 0) {
                result += 3;
            } else {
                result -= 1;
            }
        }
        return result;
    }
}
