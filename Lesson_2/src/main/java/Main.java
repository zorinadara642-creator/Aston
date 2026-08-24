public class Main {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

        System.out.println(sumInRange(5, 10));
        printIsPositive(-3);
        System.out.println(isNegative(0));
        printString("Java", 3);
        System.out.println(isLeapYear(2024));

        // 10
        int[] arr10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr10.length; i++) {
            arr10[i] = (arr10[i] == 0) ? 1 : 0;
        }
        for (int n : arr10) {
            System.out.print(n + " ");
        }
        System.out.println();

        // 11
        int[] arr11 = new int[100];
        for (int i = 0; i < arr11.length; i++) {
            arr11[i] = i + 1;
        }
        for (int n : arr11) {
            System.out.print(n + " ");
        }
        System.out.println();

        // 12
        int[] arr12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr12.length; i++) {
            if (arr12[i] < 6) {
                arr12[i] *= 2;
            }
        }
        for (int n : arr12) {
            System.out.print(n + " ");
        }
        System.out.println();

        // 13
        int size = 5;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // 14
        int[] arr14 = createArray(5, 7);
        for (int n : arr14) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // 1
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2
    public static void checkSumSign() {
        int a = 5;
        int b = -3;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3
    public static void printColor() {
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4
    public static void compareNumbers() {
        int a = 10;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5
    public static boolean sumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6
    public static void printIsPositive(int number) {
        if (number >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }

    // 7
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8
    public static void printString(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);
        }
    }

    // 9
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 14
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}