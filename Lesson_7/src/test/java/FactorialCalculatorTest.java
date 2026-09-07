import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @ParameterizedTest(name = "Факториал числа {0} равен {1}")
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "6, 720",
            "10, 3628800"
    })
    void testFactorial(int number, long expected) {
        assertEquals(expected, calculator.calculate(number));
    }

    @ParameterizedTest(name = "Факториал отрицательного числа {0} выбрасывает исключение")
    @CsvSource({"-1", "-5", "-100"})
    void testFactorialOfNegativeNumberThrowsException(int number) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(number));
    }
}
