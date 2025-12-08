package systems;

import core.Game;
import core.GameContext;
import entities.Entity;
import entities.Observer;
import entities.Player;
import systems.actions.Action;

import java.util.List;
import java.util.Scanner;

public class CombatSystem implements Observer {
    private Entity player;
    private final Scanner scanner;
    private final GameContext gameContext = GameContext.getInstance();

    public CombatSystem(Entity player) {
        this.scanner = new Scanner(System.in);
        this.player = player;
    }

    public void combatLoop(Entity enemy, ActionRegistry actionRegistry) {
        gameContext.setMode(GameContext.Mode.COMBAT);
        enemy.addObserver(player);

        while(gameContext.getMode() == GameContext.Mode.COMBAT) {
            clear();
            player.render();
            enemy.render();

            List<Action> actions = actionRegistry.getAvailableActions(player, null, gameContext);
            int userInput = scanner.nextInt();
            actions.get(userInput).execute();

            if (enemy.isAlive()) {
                enemy.attack(player);

                if (!player.isAlive()) {
                    player.die();
                    Game.running = false;
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
                }
            } else {
                enemy.die();
                gameContext.setMode(GameContext.Mode.MAIN_MENU);
            }
        }
    }

    public Entity combatLoop(Entity enemy) {
        gameContext.setMode(GameContext.Mode.COMBAT);
        enemy.addObserver(player);

        while(gameContext.getMode() == GameContext.Mode.COMBAT) {
            clear();
            player.render();
            enemy.render();
            System.out.println();
            System.out.println("1. Attack");
            System.out.println("2. Flee combat");
            int user_input = scanner.nextInt();

            switch (user_input) {
                case 1:
                    player.attack(enemy);
                    break;
                case 2:
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
                    return null;
            }

            if(enemy.getHp() <= 0) {
                enemy.die();
                gameContext.setMode(GameContext.Mode.MAIN_MENU);
                return enemy;
            } else {
                enemy.attack(player);

                if(player.getHp() <= 0) {
                    player.die();
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
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
