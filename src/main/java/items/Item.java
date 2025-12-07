package items;

public abstract class Item {
    String name;
    float volume;

    public Item(String name, float volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public float getVolume() {
        return volume;
    }
}
