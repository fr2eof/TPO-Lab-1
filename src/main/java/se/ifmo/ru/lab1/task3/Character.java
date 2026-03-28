package se.ifmo.ru.lab1.task3;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Character {

    private final String name;
    private final String species;
    private final Set<Emotion> emotions = EnumSet.noneOf(Emotion.class);
    private SmileSpeed smileSpeed;
    private SmileReason smileReason;
    private Smile smile;


    public Character(String name, String species) {
        this.name = Objects.requireNonNull(name, "name");
        this.species = Objects.requireNonNull(species, "species");
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public Set<Emotion> getEmotions() {
        return Collections.unmodifiableSet(emotions);
    }

    public void addEmotion(Emotion emotion) {
        emotions.add(Objects.requireNonNull(emotion, "emotion"));
    }

    public boolean hasEmotion(Emotion emotion) {
        return emotions.contains(emotion);
    }

    public SmileSpeed getSmileSpeed() {
        return smileSpeed;
    }

    public SmileReason getSmileReason() {
        return smileReason;
    }

    public Smile getSmile() {
        return this.smile;
    }

    public void smile(Smile smile) {
        Smile s = Objects.requireNonNull(smile, "smile");

        this.smileSpeed = s.getSpeed();
        this.smileReason = s.getReason();
    }
}

