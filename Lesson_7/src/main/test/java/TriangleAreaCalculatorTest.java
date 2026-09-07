import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class TriangleAreaCalculatorTest {

    private final TriangleAreaCalculator calculator = new TriangleAreaCalculator();

    @DataProvider(name = "areaData")
    public Object[][] areaData() {
        return new Object[][]{
                {10.0, 5.0, 25.0},
                {5.0, 2.5, 6.25},
                {8.0, 4.0, 16.0},
                {1.0, 1.0, 0.5}
        };
    }

    @Test(dataProvider = "areaData")
    public void testAreaCalculation(double base, double height, double expectedArea) {
        assertEquals(calculator.calculatorArea(base, height), expectedArea);
    }

    @DataProvider(name = "invalidValues")
    public Object[][] invalidValues() {
        return new Object[][]{
                {0.0, 5.0},
                {5.0, 0.0},
                {-3.0, 5.0},
                {5.0, -3.0}
        };
    }

    @Test(dataProvider = "invalidValues")
    public void testInvalidValuesThrowException(double base, double height) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculatorArea(base, height));
    }
}
