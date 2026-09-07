import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NumberComparatorTest {

    private final NumberComparator comparator = new NumberComparator();

    @DataProvider(name = "compareData")
    public Object[][] compareData() {
        return new Object[][]{
                {10, 5, 1},
                {5, 10, -1},
                {7, 7, 0},
                {-5, -10, 1},
                {-10, -5, -1}
        };
    }

    @Test(dataProvider = "compareData")
    public void testCompare(int a, int b, int expected) {
        assertEquals(comparator.compare(a, b), expected);
    }
}
