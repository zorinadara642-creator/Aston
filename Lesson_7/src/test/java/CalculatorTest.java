import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "3, 4, 7",
            "0, 0, 0",
            "-5, 5, 0",
            "-3, -4, -7"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "4, 3, 1",
            "0, 0, 0",
            "5, 10, -5"
    })
    void testSubtract(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "3, 4, 12",
            "0, 5, 0",
            "-2, 3, -6"
    })
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "Деление {0} на ноль выбрасывает исключение")
    @CsvSource({"5", "0", "-10"})
    void testDivideByZeroThrowsException(int a) {
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, 0));
    }
}
