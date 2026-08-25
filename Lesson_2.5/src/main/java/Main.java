public class Main {
    public static void main(String[] args) {

        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] badDataArray = {
                {"1", "2", "3", "4"},
                {"5", "abc", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] badSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"}
        };

        for (String[][] testArray : new String[][][]{validArray, badDataArray, badSizeArray}) {
            try {
                int result = ArraySumCalculator.sumArray(testArray);
                System.out.println("Сумма элементов массива: " + result);
            } catch (MyArraySizeException e) {
                System.out.println("Ошибка размера массива " + e.getMessage());
            } catch (MyArrayDataException e) {
                System.out.println("Ошибка данных в массимве " + e.getMessage());
            }
        }

        System.out.println();

        try {
            int[] numbers = {10, 20, 30};
            System.out.println("Пытаемся получить элемент по индексу 5...");
            int value = numbers[5];
            System.out.println("Значение: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException " + e.getMessage());
        }
    }
}
