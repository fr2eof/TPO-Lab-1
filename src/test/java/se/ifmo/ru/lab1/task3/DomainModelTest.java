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
            Smile smile = new Smile(SmileSpeed.SLOW, SmileReason.MUSCLE_MEMORY_PROBLEM);

            vogon.smile(smile);
            vogon.addEmotion(Emotion.CONFUSED);

            assertThat(vogon.getName()).isEqualTo("Простетный Вогон Джельц");
            assertThat(vogon.getSpecies()).isEqualTo("вогон");

            assertThat(vogon.getSmileSpeed()).isEqualTo(SmileSpeed.SLOW);
            assertThat(vogon.getSmileReason()).isEqualTo(SmileReason.MUSCLE_MEMORY_PROBLEM);

            assertThat(vogon.getEmotions()).contains(Emotion.CONFUSED);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null улыбке")
        void shouldThrowException_whenSmileIsNull() {
            Character vogon = new Character("Вогон", "вогон");

            assertThatThrownBy(() -> vogon.smile(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null эмоции")
        void shouldThrowException_whenEmotionIsNull() {
            Character vogon = new Character("Вогон", "вогон");

            assertThatThrownBy(() -> vogon.addEmotion(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("Smile")
    class SmileTests {

        @Test
        @DisplayName("Должен корректно создаваться")
        void shouldCreateSmile() {
            Smile smile = new Smile(SmileSpeed.SLOW, SmileReason.MUSCLE_MEMORY_PROBLEM);

            assertThat(smile.getSpeed()).isEqualTo(SmileSpeed.SLOW);
            assertThat(smile.getReason()).isEqualTo(SmileReason.MUSCLE_MEMORY_PROBLEM);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null параметрах")
        void shouldThrowException_whenParamsAreNull() {
            assertThatThrownBy(() -> new Smile(null, SmileReason.FOR_EFFECT))
                    .isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> new Smile(SmileSpeed.SLOW, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("ScreamSeries")
    class ScreamSeriesTests {

        @Test
        @DisplayName("Должен корректно создаваться")
        void shouldCreateValidSeries() {
            ScreamSeries series = new ScreamSeries(1);

            assertThat(series.getCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при некорректном количестве")
        void shouldThrowException_whenInvalidCount() {
            assertThatThrownBy(() -> new ScreamSeries(0))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThatThrownBy(() -> new ScreamSeries(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("TortureSession")
    class TortureSessionTests {

        @Test
        @DisplayName("Освежающая серия воплей изменяет состояние персонажа")
        void shouldMakeVogonRestedAndReadyForNastiness() {
            Character vogon = new Character("Вогон", "вогон");
            Character prisoner = new Character("пленник", "человек");
            PrisonerGroup group = new PrisonerGroup();
            group.addPrisoner(prisoner);

            TortureSession session = new TortureSession(vogon, group);
            ScreamSeries series = new ScreamSeries(1);

            session.performRefreshingScreamSeries(series);

            assertThat(session.isRefreshing()).isTrue();
            assertThat(session.getScreamSeriesCount()).isEqualTo(1);

            assertThat(session.getPrisonerGroup().getPrisoners())
                    .contains(prisoner);

            assertThat(vogon.getEmotions())
                    .contains(Emotion.RESTED, Emotion.READY_FOR_NASTINESS);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null группе пленников")
        void shouldThrowException_whenGroupIsNull() {
            Character vogon = new Character("Вогон", "вогон");

            assertThatThrownBy(() -> new TortureSession(vogon, null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null серии воплей")
        void shouldThrowException_whenSeriesIsNull() {
            Character vogon = new Character("Вогон", "вогон");
            PrisonerGroup group = new PrisonerGroup();

            TortureSession session = new TortureSession(vogon, group);

            assertThatThrownBy(() -> session.performRefreshingScreamSeries(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("PrisonerGroup")
    class PrisonerGroupTests {

        @Test
        @DisplayName("Должен добавлять пленника")
        void shouldAddPrisoner() {
            PrisonerGroup group = new PrisonerGroup();
            Character prisoner = new Character("пленник", "человек");

            group.addPrisoner(prisoner);

            assertThat(group.getPrisoners()).contains(prisoner);
        }

        @Test
        @DisplayName("Должен выбрасывать исключение при null пленнике")
        void shouldThrowException_whenPrisonerIsNull() {
            PrisonerGroup group = new PrisonerGroup();

            assertThatThrownBy(() -> group.addPrisoner(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }
}