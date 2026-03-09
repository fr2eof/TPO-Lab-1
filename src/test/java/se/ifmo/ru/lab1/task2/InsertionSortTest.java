package se.ifmo.ru.lab1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InsertionSortTest {

    private final InsertionSort sorter = new InsertionSort();

    @Test
    @DisplayName("Сортировка пустого массива не изменяет его")
    void shouldHandleEmptyArray() {
        int[] array = new int[0];
        int[] result = sorter.sort(array, new InsertionSort.Trace());
        assertArrayEquals(new int[0], result);
    }

    @Test
    @DisplayName("Сортировка массива из одного элемента")
    void shouldHandleSingleElement() {
        int[] array = {42};
        int[] result = sorter.sort(array, new InsertionSort.Trace());
        assertArrayEquals(new int[]{42}, result);
    }

    @Test
    @DisplayName("Сортировка уже отсортированного массива")
    void shouldHandleAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        InsertionSort.Trace trace = new InsertionSort.Trace();
        int[] result = sorter.sort(array, trace);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
        List<String> events = trace.getEvents();
        // Проверяем только количество характерных точек внешнего цикла
        long outerCount = events.stream().filter(e -> e.startsWith("OUTER")).count();
        assertEquals(4, outerCount);
    }

    @Test
    @DisplayName("Сортировка массива, содержащего дубликаты")
    void shouldSortArrayWithDuplicates() {
        int[] array = {3, 1, 2, 3, 1};
        int[] result = sorter.sort(array, new InsertionSort.Trace());
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, result);
    }

    @Test
    @DisplayName("Подробная трассировка характерных точек для типичного примера")
    void shouldProduceExpectedTraceForTypicalInput() {
        int[] array = {5, 2, 4, 6, 1, 3};
        InsertionSort.Trace trace = new InsertionSort.Trace();

        int[] result = sorter.sort(array, trace);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, result);

        List<String> events = trace.getEvents();

        // Эталонная последовательность попадания в характерные точки
        List<String> expectedPrefix = List.of(
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

        assertEquals(expectedPrefix, events);
    }

    @Test
    @DisplayName("Передача null массива приводит к исключению")
    void shouldThrowOnNullArray() {
        assertThrows(IllegalArgumentException.class, () -> sorter.sort(null, new InsertionSort.Trace()));
    }
}

