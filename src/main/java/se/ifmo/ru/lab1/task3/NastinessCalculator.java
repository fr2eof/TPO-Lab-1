package se.ifmo.ru.lab1.task3;


public class NastinessCalculator {
    public double calculateNastinessLevel(TortureSession session) {
        if (session == null) {
            throw new NullPointerException("session");
        }

        int series = session.getScreamSeriesCount();
        int prisoners = session.getPrisonerGroup().getPrisoners().size();

        boolean rested = session.getTorturer().hasEmotion(Emotion.RESTED);

        double base = series * 10.0;

        double prisonerFactor = prisoners > 0 ? Math.log(prisoners + 1) : 0;

        double restBonus = rested ? 5.0 : 0.0;

        return base + prisonerFactor + restBonus;
    }
}
