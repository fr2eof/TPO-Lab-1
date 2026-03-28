package se.ifmo.ru.lab1.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PrisonerGroup {

    private final List<Character> prisoners = new ArrayList<>();

    public void addPrisoner(Character prisoner) {
        prisoners.add(Objects.requireNonNull(prisoner, "prisoner"));
    }

    public List<Character> getPrisoners() {
        return Collections.unmodifiableList(prisoners);
    }
}
