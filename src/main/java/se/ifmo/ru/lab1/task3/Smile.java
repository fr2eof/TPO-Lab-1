package se.ifmo.ru.lab1.task3;

import java.util.Objects;

public class Smile {
    private SmileSpeed speed;
    private SmileReason reason;

    public SmileSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(SmileSpeed speed) {
        this.speed = speed;
    }

    public void setReason(SmileReason reason) {
        this.reason = reason;
    }

    public SmileReason getReason() {
        return reason;
    }

    public Smile(SmileSpeed speed, SmileReason reason) {
        this.speed = Objects.requireNonNull(speed);
        this.reason = Objects.requireNonNull(reason);
    }
}
