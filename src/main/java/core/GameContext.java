package core;

import entities.Corpse;
import entities.EnemyFactory;
import entities.Entity;
import items.capabilities.Container;
import systems.CombatSystem;
import systems.EquipmentSystem;

import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private static GameContext instance;
    private Mode mode;
    private Entity player;
    private Entity enemy;
    private List<Corpse> corpseList;
    private CombatSystem combatSystem;
    private EnemyFactory enemyFactory;
    private Container activeContainer;
    private EquipmentSystem activeEquipmentSystem;

    private GameContext() {
        mode = Mode.MAIN_MENU;
        corpseList = new ArrayList<>();
        enemyFactory = new EnemyFactory();
    }

    public static GameContext getInstance() {
        if(instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    public EquipmentSystem getActiveEquipmentSystem() {
        return activeEquipmentSystem;
    }

    public void setActiveEquipmentSystem(EquipmentSystem activeEquipmentSystem) {
        this.activeEquipmentSystem = activeEquipmentSystem;
    }

    public void setActiveContainer(Container activeContainer) {
        this.activeContainer = activeContainer;
    }

    public Container getActiveContainer() {
        return activeContainer;
    }

    public CombatSystem getCombatSystem() {
        return combatSystem;
    }

    public void setCombatSystem(CombatSystem combatSystem) {
        this.combatSystem = combatSystem;
    }

    public EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }

    public void setEnemyFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }

    public Entity getEnemy() {
        return enemy;
    }

    public void addCorpse(Entity entity) {
        corpseList.add(new Corpse(entity));
    }

    public void removeCorpse(Corpse corpse) {
        corpseList.remove(corpse);
    }

    public Corpse getCorpse(int index) {
        return corpseList.get(index);
    }

    public int corpseCount() {
        return corpseList.size();
    }

    public List<Corpse> getCorpses() {
        return corpseList;
    }

    public enum Mode { COMBAT, INVENTORY, MAIN_MENU, LOOTING, INSPECT_ITEM, EQUIPMENT }
}
