import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CalculatorTest {

    public final Calculator calculator = new Calculator();

    @DataProvider(name = "addData")
    public Object[][] addData() {
        return new Object[][]{
                {3, 4, 7},
                {0, 0, 0},
                {-5, 5, 0},
                {-3, -4, -7}
        };
    }

    @Test(dataProvider = "addData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(calculator.add(a, b), expected);
    }

    @DataProvider(name = "subtractData")
    public Object[][] subtractData() {
        return new Object[][]{
                {4, 3, 1},
                {0, 0, 0},
                {5, 10, -5}
        };
    }

    @Test(dataProvider = "subtractData")
    public void testSubtract(int a, int b, int expected) {
        assertEquals(calculator.subtract(a, b), expected);
    }

    @DataProvider(name = "multiplyData")
    public Object[][] multiplyData() {
        return new Object[][]{
                {3, 4, 12},
                {0, 5, 0},
                {-2, 3, -6}
        };
    }

    @Test(dataProvider = "multiplyData")
    public void testMultiply(int a, int b, int expected) {
        assertEquals(calculator.multiply(a, b), expected);
    }

    @DataProvider(name = "divideData")
    public Object[][] divideData() {
        return new Object[][]{
                {5, 2, 2.5},
                {10, 5, 2.0},
                {7, 2, 3.5}
        };
    }

    @Test(dataProvider = "divideData")
    public void testDivide(int a, int b, double expected) {
        assertEquals(calculator.divide(a, b), expected);
    }

    @DataProvider(name = "divideByZeroData")
    public Object[][] divideByZeroData() {
        return new Object[][]{
                {5},
                {0},
                {-10}
        };
    }

    @Test(dataProvider = "divideByZeroData")
    public void testDivideByZeroThrowsException(int a) {
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, 0));
    }
}
