package se.ifmo.ru.lab1.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertionSort {

    public static class Trace {
        private final List<String> events = new ArrayList<>();

        void onOuterLoop(int i) {
            events.add("OUTER i=" + i);
        }

        void onCompare(int i, int j) {
            events.add("COMPARE i=" + i + " j=" + j);
        }

        void onShift(int from, int to) {
            events.add("SHIFT from=" + from + " to=" + to);
        }

        void onInsert(int position, int key) {
            events.add("INSERT pos=" + position + " key=" + key);
        }

        public List<String> getEvents() {
            return Collections.unmodifiableList(events);
        }
    }

    public int[] sort(int[] array, Trace trace) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        if (array.length < 2) {
            return array;
        }

        Trace t = trace != null ? trace : new Trace();

        for (int i = 1; i < array.length; i++) {
            t.onOuterLoop(i);
            int key = array[i];
            int j = i - 1;

            while (j >= 0) {
                t.onCompare(i, j);
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    t.onShift(j, j + 1);
                    j--;
                } else {
                    break;
                }
            }

            array[j + 1] = key;
            t.onInsert(j + 1, key);
        }

        return array;
    }
}

