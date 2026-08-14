import java.util.Arrays;

public class Lesson_2 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(isSumInRange(5, 7));
        checkPositiveOrNegative();
        System.out.println(isNegative(6));
        printStringMultipleTimes("Motya", 5);
        System.out.println(isLeapYear(2026));
        int[] binaryArray = {1, 1, 0, 0, 0, 1, 1};
        invertBinaryArray(binaryArray);
        System.out.println("Инвертированный массив: " + Arrays.toString(binaryArray));
        task11();
        task12();
        task13();
        int[] result = createArray(5, 7);
        System.out.println(java.util.Arrays.toString(result));
    }

    private static void printThreeWords() {
        System.out.println("Задание 1");
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        System.out.println("Задание 2");
        int a = -10;
        int b = 6;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        System.out.println("Задание 3");
        int value = 55;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else if (value > 100) {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        System.out.println("Задание 4");
        int a = 30;
        int b = 85;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean isSumInRange(int a, int b) {
        System.out.println("Задание 5");
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void checkPositiveOrNegative() {
        System.out.println("Задание 6");
        int number = -6;
        if (number >= 0) {
            System.out.println(number + " - положительное число");
        } else {
            System.out.println(number + " - отрицательное число");
        }
    }

    public static boolean isNegative(int number) {
        System.out.println("Задание 7");
        return number < 0;
    }

    public static void printStringMultipleTimes(String str, int count) {
        System.out.println("Задание 8");
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        System.out.println("Задание 9");
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void invertBinaryArray(int[] array) {
        System.out.println("Задание 10");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
    }

    public static void task11() {
        System.out.println("Задание 11");
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(java.util.Arrays.toString(arr));
    }

    public static void task12() {
        System.out.println("Задание 12");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(java.util.Arrays.toString(arr));
    }

    public static void task13() {
        System.out.println("Задание 13");
        int size = 5;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] createArray(int len, int initialValue) {
        System.out.println("Задание 14");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
