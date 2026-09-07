import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;


public class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @DataProvider(name = "factorialData")
    public Object[][] factorialData() {
        return new Object[][]{
                {0, 1L},
                {1, 1L},
                {5, 120L},
                {6, 720L},
                {10, 3628800L}
        };
    }

    @Test(dataProvider = "factorialData")
    public void testFactorial(int number, long expected) {
        assertEquals(calculator.calculate(number), expected);
    }

    @DataProvider(name = "negativeNumbers")
    public Object[][] negativeNumbers() {
        return new Object[][]{
                {-1},
                {-5},
                {-100}
        };
    }

    @Test(dataProvider = "negativeNumbers")
    public void testFactorialOfNegativeNumbersThrowsException(int number) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(number));
    }
}
