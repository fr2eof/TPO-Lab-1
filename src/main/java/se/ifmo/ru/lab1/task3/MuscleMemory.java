package se.ifmo.ru.lab1.task3;

public class MuscleMemory {
    private boolean remembersSequence;

    public MuscleMemory(boolean remembersSequence) {
        this.remembersSequence = remembersSequence;
    }

    public boolean isRemembersSequence() {
        return remembersSequence;
    }

    public void setRemembersSequence(boolean remembersSequence) {
        this.remembersSequence = remembersSequence;
    }
}
