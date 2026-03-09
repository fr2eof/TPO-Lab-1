package se.ifmo.ru.lab1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CosSeriesTest {

    private static final double EPSILON = 1e-10;
    private final CosSeries cosSeries = new CosSeries();

    @Test
    @DisplayName("Отрицательное или нулевое epsilon недопустимо")
    void shouldThrowOnNonPositiveEpsilon() {
        assertThrows(IllegalArgumentException.class, () -> cosSeries.calculate(0.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> cosSeries.calculate(1.0, -1e-3));
    }

    @ParameterizedTest(name = "cos({0}) ≈ {1}")
    @CsvSource({
            "0.0, 1.0",
            "1.5707963267948966, 0.0",      // pi/2
            "-1.5707963267948966, 0.0",     // -pi/2
            "3.141592653589793, -1.0",      // pi
            "-3.141592653589793, -1.0",     // -pi
            "0.7853981633974483, 0.7071067811865476",   // pi/4
            "-0.7853981633974483, 0.7071067811865476"   // -pi/4
    })
    @DisplayName("Проверка значений cos(x) на характерных точках")
    void shouldApproximateCosOnKeyPoints(double x, double expected) {
        double actual = cosSeries.calculate(x, EPSILON);
        assertEquals(expected, actual, 1e-8);
    }

    @ParameterizedTest(name = "cos должна быть чётной функцией для x={0}")
    @CsvSource({
            "0.1",
            "0.5",
            "1.0",
            "2.0"
    })
    @DisplayName("Чётность функции cos(x)")
    void shouldBeEvenFunction(double x) {
        double positive = cosSeries.calculate(x, EPSILON);
        double negative = cosSeries.calculate(-x, EPSILON);
        assertEquals(positive, negative, 1e-9);
    }

    @ParameterizedTest(name = "Сходимость ряда cos(x) для |x|={0}")
    @CsvSource({
            "0.1",
            "1.0",
            "3.0",
            "10.0"
    })
    @DisplayName("Сходимость степенного ряда для различных значений x")
    void shouldConvergeForDifferentX(double x) {
        double seriesValue = cosSeries.calculate(x, EPSILON);
        double mathValue = Math.cos(x);
        assertEquals(mathValue, seriesValue, 1e-8);
    }
}

