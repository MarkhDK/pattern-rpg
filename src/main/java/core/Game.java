package core;

import entities.Corpse;
import entities.Entity;
import entities.Player;
import items.Item;
import items.capabilities.Consumable;
import items.capabilities.Container;
import items.equippable.EquipmentSlotType;
import systems.ActionRegistry;
import systems.CombatSystem;
import systems.actions.Action;
import systems.actions.providers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public GameContext gameContext;
    public static boolean running;
    Player player;
    ActionRegistry actionRegistry;

    public Game() {
        gameContext = GameContext.getInstance();
        player = new Player();
        gameContext.setPlayer(player);
        gameContext.setCombatSystem(new CombatSystem(player));

        actionRegistry = new ActionRegistry();
        actionRegistry.registerProvider(new CombatActionProvider());
        actionRegistry.registerProvider(new ConsumableActionProvider());
        actionRegistry.registerProvider(new EquipmentActionProvider());
        actionRegistry.registerProvider(new InventoryActionProvider());
        actionRegistry.registerProvider(new LootingActionProvider());
        actionRegistry.registerProvider(new GeneralActionProvider());
        actionRegistry.registerProvider((ActionProvider) player.getBackpack());
        actionRegistry.registerProvider((ActionProvider) player.getEquipment().getWeapon());
    }

    public void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        List<Action> actions = new ArrayList<>();
        int userInput;

        running = true;

        while(running) {
            clear();
            gameContext.setMode(GameContext.Mode.MAIN_MENU);
            actions = actionRegistry.getActions(player, null, gameContext);
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(i + ". " + actions.get(i).getLabel());
            }
            userInput = scanner.nextInt();
            actions.get(userInput).execute();
        }

        while(running && player.getHp() > 0) {
            clear();
            player.render();
            displayOptions();
            int user_input = scanner.nextInt();
            Entity loser;

            switch (user_input) {
                case 1:
                    gameContext.setMode(GameContext.Mode.COMBAT);
                    loser = gameContext.getCombatSystem().combatLoop(gameContext.getEnemyFactory().createEnemy("goblin"));
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
                    if (!(loser instanceof Player) && loser != null) {
                        gameContext.addCorpse(new Corpse(loser));
                    }
                    break;
                case 2:
                    gameContext.setMode(GameContext.Mode.COMBAT);
                    loser = gameContext.getCombatSystem().combatLoop(gameContext.getEnemyFactory().createEnemy("orc"));
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
                    if (!(loser instanceof Player) && loser != null) {
                        gameContext.addCorpse(new Corpse(loser));
                    }
                    break;
                case 3:
                    gameContext.setMode(GameContext.Mode.INVENTORY);
                    Container playerInventory = (Container) player.getEquippedItem(EquipmentSlotType.BACK);
                    player.showInventory();
                    int inventoryInput = scanner.nextInt() - 1;

                    if(inventoryInput < playerInventory.count()) {
                        Item item = playerInventory.getItem(inventoryInput);
                        if(item instanceof Consumable) {
                            ((Consumable) item).consume(player);
                        }
                    }
                    gameContext.setMode(GameContext.Mode.MAIN_MENU);
                    break;
                case 4:
                    if (!gameContext.getCorpses().isEmpty()) {
                        gameContext.setMode(GameContext.Mode.LOOTING);
                        for (int i = 0; i < gameContext.corpseCount(); i++) {
                            System.out.println((i + 1) + ". " + gameContext.getCorpse(i).getName());
                        }
                        int lootChoice = scanner.nextInt() - 1;

                        if (lootChoice < gameContext.corpseCount()) {
                            Corpse corpse = gameContext.getCorpse(lootChoice);
                            gameContext.setActiveContainer(corpse.getBackpack());
                            corpse.getBackpack().displayInventory();
                            int lootItemIndex = scanner.nextInt() - 1;

                            if (lootItemIndex < corpse.getBackpack().count()) {
                                Item item = corpse.getBackpack().getItem(lootItemIndex);
                                player.getBackpack().addItem(item);
                                corpse.getBackpack().removeItem(item);
                                if (corpse.getBackpack().count() == 0) {
                                    gameContext.removeCorpse(corpse);
                                }
                            }
                        }
                        gameContext.setMode(GameContext.Mode.MAIN_MENU);
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayOptions() {
        System.out.println("1. Fight goblin");
        System.out.println("2. Fight orc");
        System.out.println("3. Open inventory");
        if (!gameContext.getCorpses().isEmpty()) {
            System.out.println("4. Loot");
        }
        System.out.println("0. Exit game");
    }

    private void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
