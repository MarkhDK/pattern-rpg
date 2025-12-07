package systems;

import entities.Entity;

public class Effect {
    private final String type;
    private final int magnitude;
    private final int duration;

    public Effect(String type, int magnitude, int duration) {
        this.type = type;
        this.magnitude = magnitude;
        this.duration = duration;
    }

    public void apply(Entity target) {
        EffectDispatcher.apply(target, this);
    }

    public String getType() {
        return type;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getDuration() {
        return duration;
    }
}
