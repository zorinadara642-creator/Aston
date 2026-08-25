public class ArraySumCalculator {
    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException(
                    "Ожидался массив 4х4, получено строк: " + (array == null ? "null" : array.length));
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException(
                        "Ожидася массив 4х4, но строка " + i + " имеет длину: " + (array[i] == null ? "null" : array[i].length));
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j].trim());
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "Некоторые данные в ячейке [" + i + "][" + j + "]: \"" + array[i][j] + "\"");
                }
            }
        }
        return sum;
    }
}
