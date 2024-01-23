public class task1 {
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));

        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));

        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));

        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));

        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));

        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(howManyItems(45, 1.8, 1.9));
        System.out.println(howManyItems(100, 2, 2));

        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));

        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));

        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250));

        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));
    }
// public|private

// 1 задание

    public static float convert(int x) { // Объявление функции
        return x * 3.785f; // 18.925, 11.355, 30.28
    }

// 2 задание

    public static int fitCalc(int minutes, int intensity) {
        return minutes * intensity; // 15, 48, 123
    }

// 3 задание

    public static int containers(int boxes, int bags, int barrels) {
        return boxes * 20 + bags * 50 + barrels * 100; // 460, 300, 530
    }


// 4 задание

    public static String triangleType(int x, int y, int z) {

        if (x + y <= z || x + z <= y || y + z <= x) {
            return "not a triangle"; // Неравенство треугольника (две больше третьей)
        } else if (x == y && y == z) {
            return "equilateral"; // Равносторонний треугольник
        } else if (x == y || y == z || x == z) {
            return "isosceles"; // Равнобедренный треугольник
        } else {
            return "different-sided"; // Разносторонний треугольник (ничего не выполнено)
        }
    }

// 5 задание

    public static int ternaryEvaluation(int a, int b) {
        return a > b ? a : b; // 8, 11, 9
    }

// 6 задание

    public static int howManyItems(double n, double w, double h) {
        double oneItem = w * h * 2; // Длина и ширина (n * 2) - количество квадратных метров имеющейся ткани
        int maxItems = (int) (n / oneItem);
        return maxItems; // 3, 6, 12
    }

// 7 задание

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1); // 6, 120, 5040
        }
    }

// 8 задание

    public static int gcd(int a, int b) {
        while (b != 0) { // Не равно
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a; // 6, 4, 7
    }

// 9 задание

    public static int ticketSaler(int tickets, int price) {
        double commissionRate = 0.72; // Стоит double для int
        return (int) (tickets * price * commissionRate); // 75600, 16416, 47700
    }

// 10 задание

    public static int tables(int students, int tablesAvailable) {
        int tablesRequired = (students + 1) / 2; // Добавление 1 нужно для округления вверх в случае нечетного числа студентов
        int tablesNeeded = tablesRequired - tablesAvailable;

        return Math.max(tablesNeeded, 0); // 1, 0, 4
    }
}
