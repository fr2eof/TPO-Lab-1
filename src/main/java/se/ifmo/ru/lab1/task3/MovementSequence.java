package se.ifmo.ru.lab1.task3;

import java.util.List;

public class MovementSequence {
    private final List<String> steps;

    public MovementSequence(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getSteps() {
        return steps;
    }
}
