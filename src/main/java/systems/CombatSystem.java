package systems;

import entities.Corpse;
import entities.Entity;
import entities.Observer;
import entities.Player;

import java.util.Scanner;

public class CombatSystem implements Observer {
    private static CombatSystem instance;
    private Entity player;
    private final Scanner scanner;

    private CombatSystem() {
        this.scanner = new Scanner(System.in);
        this.player = Player.getInstance();
        player.addObserver(this);
    }

    public static CombatSystem getInstance() {
        if(instance == null) {
            instance = new CombatSystem();
        }
        return instance;
    }

    public Entity combatLoop(Entity enemy) {
        enemy.addObserver(player);

        while(player != null && enemy.getHp() > 0) {
            clear();
            player.render();
            enemy.render();
            System.out.println();
            System.out.println("1. Attack");
            System.out.println("2. Flee combat");
            int user_input = scanner.nextInt();

            switch (user_input) {
                case 1:
                    player.attack(player, enemy);
                    break;
                case 2:
                    return null;
            }

            if(enemy.getHp() <= 0) {
                enemy.die();
                return enemy;
            } else {
                enemy.attack(enemy, player);

                if(player.getHp() <= 0) {
                    player.die();
                    return player;
                }
            }
        }
        return null;
    }

    private void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    @Override
    public void targetDied(Entity target) {
        if(target instanceof Player) {
            player = null;
        }
    }
}
