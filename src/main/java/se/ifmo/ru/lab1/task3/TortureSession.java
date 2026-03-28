package se.ifmo.ru.lab1.task3;


import java.util.Objects;

public class TortureSession {

    private final Character torturer;
    private final PrisonerGroup prisonerGroup;
    private int screamSeriesCount;
    private boolean refreshing;

    public TortureSession(Character torturer, PrisonerGroup prisonerGroup) {
        this.torturer = Objects.requireNonNull(torturer, "torturer");
        this.prisonerGroup = Objects.requireNonNull(prisonerGroup, "prisonerGroup");
    }

    public PrisonerGroup getPrisonerGroup() {
        return prisonerGroup;
    }

    public int getScreamSeriesCount() {
        return screamSeriesCount;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void performRefreshingScreamSeries(ScreamSeries series) {
        ScreamSeries s = Objects.requireNonNull(series, "series");

        this.screamSeriesCount += s.getCount();
        this.refreshing = true;

        torturer.addEmotion(Emotion.RESTED);
        torturer.addEmotion(Emotion.READY_FOR_NASTINESS);
    }

    public Character getTorturer() {
        return this.torturer;
    }

    public double getNastinessLevel() {
        NastinessCalculator calculator = new NastinessCalculator();
        return calculator.calculateNastinessLevel(this);
    }
}