package se.ifmo.ru.lab1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainModelTest {

    @Test
    @DisplayName("Простетный Вогон Джельц медленно улыбается из-за проблем с мышечной памятью")
    void vogonSmilesSlowlyBecauseOfMuscleProblem() {
        Character vogon = new Character("Простетный Вогон Джельц", "вогон");

        vogon.smile(SmileSpeed.SLOW, SmileReason.MUSCLE_MEMORY_PROBLEM);
        vogon.addEmotion(Emotion.CONFUSED);

        assertEquals("Простетный Вогон Джельц", vogon.getName());
        assertEquals("вогон", vogon.getSpecies());
        assertEquals(SmileSpeed.SLOW, vogon.getSmileSpeed());
        assertEquals(SmileReason.MUSCLE_MEMORY_PROBLEM, vogon.getSmileReason());
        assertTrue(vogon.hasEmotion(Emotion.CONFUSED));
    }

    @Test
    @DisplayName("После освежающей серии воплей Вогон отдохнувший и готов к гнусности")
    void tortureSessionMakesVogonRestedAndReadyForNastiness() {
        Character vogon = new Character("Простетный Вогон Джельц", "вогон");
        Character prisoner = new Character("пленник", "человек");
        TortureSession session = new TortureSession(vogon);
        session.addPrisoner(prisoner);

        session.performRefreshingScreamSeries(1);

        assertTrue(session.isRefreshing());
        assertEquals(1, session.getScreamSeriesCount());
        assertTrue(session.getPrisoners().contains(prisoner));
        assertTrue(vogon.hasEmotion(Emotion.RESTED));
        assertTrue(vogon.hasEmotion(Emotion.READY_FOR_NASTINESS));
    }
}

