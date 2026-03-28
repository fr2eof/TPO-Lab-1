package se.ifmo.ru.lab1.task3;

public class ScreamSeries {

    private final int count;

    public ScreamSeries(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be positive");
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}