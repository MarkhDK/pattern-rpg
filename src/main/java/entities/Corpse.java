package entities;

import systems.EquipmentSystem;

public class Corpse extends Entity{
    EquipmentSystem equipment;

    public Corpse(Entity entity) {
        super("Corpse of " + entity.name, 1, entity.equipment);
    }

    @Override
    public void render() {
        System.out.println("-----" + name + "-----");
        equipment.render();
    }

    @Override
    public void die() {

    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public void targetDied(Entity target) {

    }
}
