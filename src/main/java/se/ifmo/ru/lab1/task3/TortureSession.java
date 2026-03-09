package se.ifmo.ru.lab1.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TortureSession {

    private final Character torturer;
    private final List<Character> prisoners = new ArrayList<>();
    private int screamSeriesCount;
    private boolean refreshing;

    public TortureSession(Character torturer) {
        this.torturer = Objects.requireNonNull(torturer, "torturer");
    }

    public Character getTorturer() {
        return torturer;
    }

    public List<Character> getPrisoners() {
        return Collections.unmodifiableList(prisoners);
    }

    public int getScreamSeriesCount() {
        return screamSeriesCount;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void addPrisoner(Character prisoner) {
        prisoners.add(Objects.requireNonNull(prisoner, "prisoner"));
    }

    public void performRefreshingScreamSeries(int seriesCount) {
        if (seriesCount <= 0) {
            throw new IllegalArgumentException("Series count must be positive");
        }
        this.screamSeriesCount += seriesCount;
        this.refreshing = true;

        torturer.addEmotion(Emotion.RESTED);
        torturer.addEmotion(Emotion.READY_FOR_NASTINESS);
    }
}

