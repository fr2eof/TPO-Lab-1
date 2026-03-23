package se.ifmo.ru.lab1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InsertionSortTest {

    private final InsertionSort sorter = new InsertionSort();

    @Nested
    @DisplayName("Валидация входных параметров")
    class ValidationTests {

        @Test
        @DisplayName("Должен выбрасывать исключение при null массиве")
        void shouldThrowException_whenArrayIsNull() {
            int[] array = null;
            InsertionSort.Trace trace = new InsertionSort.Trace();

            assertThatThrownBy(() -> sorter.sort(array, trace))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("Базовые случаи")
    class BaseCasesTests {

        @Test
        @DisplayName("Сортировка пустого массива не изменяет его")
        void shouldHandleEmptyArray() {
            int[] array = new int[0];
            InsertionSort.Trace trace = new InsertionSort.Trace();

            int[] actualResult = sorter.sort(array, trace);

            assertThat(actualResult)
                    .isEmpty();
        }

        @Test
        @DisplayName("Сортировка массива из одного элемента")
        void shouldHandleSingleElement() {
            int[] array = {42};
            InsertionSort.Trace trace = new InsertionSort.Trace();

            int[] actualResult = sorter.sort(array, trace);

            assertThat(actualResult)
                    .containsExactly(42);
        }
    }

    @Nested
    @DisplayName("Корректность сортировки")
    class SortingTests {

        @Test
        @DisplayName("Должен корректно сортировать уже отсортированный массив")
        void shouldHandleAlreadySortedArray() {
            int[] array = {1, 2, 3, 4, 5};
            InsertionSort.Trace trace = new InsertionSort.Trace();

            int[] actualResult = sorter.sort(array, trace);
            List<String> actualEvents = trace.getEvents();

            assertThat(actualResult)
                    .containsExactly(1, 2, 3, 4, 5);

            long outerLoopCount = actualEvents.stream()
                    .filter(e -> e.startsWith("OUTER"))
                    .count();

            assertThat(outerLoopCount)
                    .isEqualTo(4);
        }

        @Test
        @DisplayName("Должен сортировать массив с дубликатами")
        void shouldSortArrayWithDuplicates() {
            int[] array = {3, 1, 2, 3, 1};

            int[] actualResult = sorter.sort(array, new InsertionSort.Trace());

            assertThat(actualResult)
                    .containsExactly(1, 1, 2, 3, 3);
        }
    }

    @Nested
    @DisplayName("Трассировка алгоритма")
    class TraceTests {

        @Test
        @DisplayName("Должен формировать корректную трассировку для типичного случая")
        void shouldProduceExpectedTraceForTypicalInput() {
            int[] array = {5, 2, 4, 6, 1, 3};
            InsertionSort.Trace trace = new InsertionSort.Trace();

            List<String> expectedEvents = List.of(
                    "OUTER i=1",
                    "COMPARE i=1 j=0",
                    "SHIFT from=0 to=1",
                    "INSERT pos=0 key=2",

                    "OUTER i=2",
                    "COMPARE i=2 j=1",
                    "SHIFT from=1 to=2",
                    "COMPARE i=2 j=0",
                    "INSERT pos=1 key=4",

                    "OUTER i=3",
                    "COMPARE i=3 j=2",
                    "INSERT pos=3 key=6",

                    "OUTER i=4",
                    "COMPARE i=4 j=3",
                    "SHIFT from=3 to=4",
                    "COMPARE i=4 j=2",
                    "SHIFT from=2 to=3",
                    "COMPARE i=4 j=1",
                    "SHIFT from=1 to=2",
                    "COMPARE i=4 j=0",
                    "SHIFT from=0 to=1",
                    "INSERT pos=0 key=1",

                    "OUTER i=5",
                    "COMPARE i=5 j=4",
                    "SHIFT from=4 to=5",
                    "COMPARE i=5 j=3",
                    "SHIFT from=3 to=4",
                    "COMPARE i=5 j=2",
                    "SHIFT from=2 to=3",
                    "COMPARE i=5 j=1",
                    "INSERT pos=2 key=3"
            );

            int[] actualResult = sorter.sort(array, trace);
            List<String> actualEvents = trace.getEvents();

            assertThat(actualResult)
                    .containsExactly(1, 2, 3, 4, 5, 6);

            assertThat(actualEvents)
                    .isEqualTo(expectedEvents);
        }
    }
}