package se.ifmo.ru.lab1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DomainModelTest {

    @Nested
    @DisplayName("Character")
    class CharacterTests {

        @Test
        @DisplayName("Должен корректно задавать улыбку и эмоции")
        void shouldSetSmileAndEmotion() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");

            vogon.smile(SmileSpeed.SLOW, SmileReason.MUSCLE_MEMORY_PROBLEM);
            vogon.addEmotion(Emotion.CONFUSED);

            assertThat(vogon.getName())
                    .isEqualTo("Простетный Вогон Джельц");

            assertThat(vogon.getSpecies())
                    .isEqualTo("вогон");

            assertThat(vogon.getSmileSpeed())
                    .isEqualTo(SmileSpeed.SLOW);

            assertThat(vogon.getSmileReason())
                    .isEqualTo(SmileReason.MUSCLE_MEMORY_PROBLEM);

            assertThat(vogon.getEmotions())
                    .contains(Emotion.CONFUSED);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null параметрах улыбки")
        void shouldThrowException_whenSmileParamsAreNull() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");

            assertThatThrownBy(() -> vogon.smile(null, SmileReason.FOR_EFFECT))
                    .isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> vogon.smile(SmileSpeed.SLOW, null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null эмоции")
        void shouldThrowException_whenEmotionIsNull() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");

            assertThatThrownBy(() -> vogon.addEmotion(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("TortureSession")
    class TortureSessionTests {

        @Test
        @DisplayName("Освежающая серия воплей делает палача отдохнувшим и готовым к гнусности")
        void shouldMakeVogonRestedAndReadyForNastiness() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");
            Character prisoner = new Character("пленник", "человек");
            TortureSession session = new TortureSession(vogon);

            session.addPrisoner(prisoner);

            session.performRefreshingScreamSeries(1);

            assertThat(session.isRefreshing())
                    .isTrue();

            assertThat(session.getScreamSeriesCount())
                    .isEqualTo(1);

            assertThat(session.getPrisoners())
                    .contains(prisoner);

            assertThat(vogon.getEmotions())
                    .contains(Emotion.RESTED, Emotion.READY_FOR_NASTINESS);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при некорректном количестве серий")
        void shouldThrowException_whenSeriesCountIsInvalid() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");
            TortureSession session = new TortureSession(vogon);

            assertThatThrownBy(() -> session.performRefreshingScreamSeries(0))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThatThrownBy(() -> session.performRefreshingScreamSeries(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при добавлении null пленника")
        void shouldThrowException_whenPrisonerIsNull() {
            Character vogon = new Character("Простетный Вогон Джельц", "вогон");
            TortureSession session = new TortureSession(vogon);

            assertThatThrownBy(() -> session.addPrisoner(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }
}