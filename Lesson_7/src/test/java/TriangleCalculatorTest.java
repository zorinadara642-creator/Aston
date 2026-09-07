import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleCalculatorTest {

    private final TriangleAreaCalculator calculator = new TriangleAreaCalculator();

    @ParameterizedTest(name = "Площадь треугольника с основанием {0} и высотой {1} равна {2}")
    @CsvSource({
            "10, 5, 25.0",
            "5.0, 2.5, 6.25",
            "8, 4, 16.0",
            "1, 1, 0.5"
    })
    void testAreaCalculation(double base, double height, double expectedArea) {
        assertEquals(expectedArea, calculator.calculatorArea(base, height));
    }

    @ParameterizedTest(name = "Некорректные основание {0} или высота {1} выбрасывают исключение")
    @CsvSource({
            "0, 5",
            "5, 0",
            "-3, 5",
            "5, -3"
    })
    void testInvalidValuesThrowException(double base, double height) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculatorArea(base, height));
    }
}
