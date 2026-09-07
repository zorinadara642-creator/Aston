import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberComparatorTest {

    private final NumberComparator comparator = new NumberComparator();

    @ParameterizedTest(name = "Сравнение {0} и {1} дает результат {2}")
    @CsvSource({
            "10, 5, 1",
            "5, 10, -1",
            "7, 7, 0",
            "-5, -10, 1",
            "-10, -5, -1"
    })
    void testCompare(int a, int b, int expexted) {
        assertEquals(expexted, comparator.compare(a, b));
    }
}
