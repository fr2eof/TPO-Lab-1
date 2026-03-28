package se.ifmo.ru.lab1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class CosSeriesTest {

    private static final double EPSILON = Math.pow(10, -10);
    private static final double LARGE_X = 1_000_000.0;

    private final CosSeries cosSeries = new CosSeries();

    @Nested
    @DisplayName("Валидация входных параметров")
    class ValidationTests {

        @Test
        @DisplayName("Должен выбрасывать исключение при epsilon <= 0")
        void shouldThrowException_whenEpsilonIsNonPositive() {
            double x = 1.0;
            double invalidEpsilonZero = 0.0;
            double invalidEpsilonNegative = -1 * Math.pow(10, -3);

            assertThatThrownBy(() -> cosSeries.calculate(x, invalidEpsilonZero))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThatThrownBy(() -> cosSeries.calculate(x, invalidEpsilonNegative))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Должен корректно обрабатывать NaN")
        void shouldHandleNaN() {
            double x = Double.NaN;

            double actualValue = cosSeries.calculate(x, EPSILON);

            assertThat(actualValue).isNaN();
        }

        @Test
        @DisplayName("Должен корректно обрабатывать бесконечность")
        void shouldHandleInfinity() {
            double x = Double.POSITIVE_INFINITY;

            assertThatThrownBy(() -> cosSeries.calculate(x, EPSILON))
                    .isInstanceOf(Exception.class); // или уточни поведение
        }
    }

    @Nested
    @DisplayName("Корректность вычисления cos(x)")
    class CosCalculationTests {

        static Stream<Arguments> provideAngles() {
            double sqrt2over2 = Math.sqrt(2) / 2;

            return Stream.of(
                    Arguments.of(0.0, 1.0),
                    Arguments.of(Math.PI / 2, 0.0),
                    Arguments.of(-Math.PI / 2, 0.0),
                    Arguments.of(Math.PI, -1.0),
                    Arguments.of(-Math.PI, -1.0),
                    Arguments.of(Math.PI / 4, sqrt2over2),
                    Arguments.of(-Math.PI / 4, sqrt2over2)
            );
        }

        @Test
        @DisplayName("cos(0) должен быть равен 1")
        void shouldReturnOne_whenXIsZero() {
            double x = 0.0;
            double epsilon = EPSILON;

            double actualValue = cosSeries.calculate(x, epsilon);


            assertThat(actualValue)
                    .isEqualTo(1.0);
        }

        @ParameterizedTest(name = "cos({0}) ~~ {1}")
        @MethodSource("provideAngles")
        @DisplayName("Должен корректно считать значения в характерных точках")
        void shouldReturnCorrectValues(double x, double expectedValue) {
            double epsilon = EPSILON;

            double actualValue = cosSeries.calculate(x, epsilon);


            assertThat(actualValue)
                    .isCloseTo(expectedValue, within(Math.pow(10, -8)));
        }

        @ParameterizedTest
        @CsvSource({
                "0.1",
                "0.5",
                "1.0",
                "2.0"
        })
        @DisplayName("cos(x) должен быть чётной функцией")
        void shouldBeEvenFunction(double x) {
            double epsilon = EPSILON;

            double actualPositive = cosSeries.calculate(x, epsilon);
            double actualNegative = cosSeries.calculate(-x, epsilon);


            assertThat(actualPositive)
                    .isCloseTo(actualNegative, within(Math.pow(10, -9)));
        }
    }

    @Nested
    @DisplayName("Сходимость и точность")
    class ConvergenceTests {

        @Test
        @DisplayName("Должен выбрасывать исключение если ряд не сходится")
        void shouldThrowException_whenSeriesDoesNotConverge() {
            double x = LARGE_X;
            double epsilon = Math.pow(10, -20);

            assertThatThrownBy(() -> cosSeries.calculate(x, epsilon))
                    .isInstanceOf(IllegalStateException.class);
        }

        @ParameterizedTest
        @CsvSource({
                "0.1",
                "1.0",
                "3.0",
                "10.0"
        })
        @DisplayName("Ряд должен сходиться к значению Math.cos(x)")
        void shouldConvergeToMathCos(double x) {
            double epsilon = EPSILON;
            double expectedValue = Math.cos(x);

            double actualValue = cosSeries.calculate(x, epsilon);


            assertThat(actualValue)
                    .isCloseTo(expectedValue, within(Math.pow(10, -8)));
        }

        static Stream<Arguments> provideAccuracyCases() {
            return Stream.of(
                    Arguments.of(1.0, Math.pow(10, -3)),
                    Arguments.of(1.0, Math.pow(10, -6)),
                    Arguments.of(1.0, Math.pow(10, -10))
            );
        }

        @ParameterizedTest
        @MethodSource("provideAccuracyCases")
        @DisplayName("Точность должна улучшаться при уменьшении epsilon")
        void shouldImproveAccuracy_whenEpsilonDecreases(double x, double epsilon) {
            double expectedValue = Math.cos(x);

            double actualValue = cosSeries.calculate(x, epsilon);

            assertThat(actualValue)
                    .isCloseTo(expectedValue, within(epsilon * 10));
        }

    }
}