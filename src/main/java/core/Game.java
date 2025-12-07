package core;

import entities.Corpse;
import entities.Entity;
import entities.EnemyFactory;
import entities.Player;
import items.Item;
import items.capabilities.Consumable;
import items.capabilities.Container;
import items.equippable.EquipmentSlotType;
import systems.CombatSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Player player;
    List<Corpse> corpseList;

    public Game() {
        player = Player.getInstance();
        corpseList = new ArrayList<>();
    }

    public void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        CombatSystem combatSystem = CombatSystem.getInstance();
        EnemyFactory enemyFactory = EnemyFactory.getInstance();

        boolean running = true;

        while(running && player.getHp() > 0) {
            clear();
            player.render();
            displayOptions();
            int user_input = scanner.nextInt();

            switch (user_input) {
                case 1:
                    Entity loser = combatSystem.combatLoop(enemyFactory.createEnemy("goblin"));
                    if (!(loser instanceof Player) && loser != null) {
                        corpseList.add(new Corpse(loser));
                    }
                    break;
                case 2:
                    combatSystem.combatLoop(enemyFactory.createEnemy("orc"));
                    break;
                case 3:
                    Container playerInventory = (Container) player.getEquippedItem(EquipmentSlotType.BACK);
                    player.showInventory();
                    int inventoryInput = scanner.nextInt() - 1;

                    if(inventoryInput < playerInventory.count()) {
                        Item item = playerInventory.getItem(inventoryInput);
                        if(item instanceof Consumable) {
                            ((Consumable) item).consume(player);
                        }
                    }
                    break;
                case 4:
                    if (corpseList.size() > 0) {
                        for (int i = 0; i < corpseList.size(); i++) {
                            System.out.println((i + 1) + ". " + corpseList.get(i).getName());
                        }
                        int lootChoice = scanner.nextInt() - 1;

                        if (lootChoice < corpseList.size()) {
                            Corpse corpse = corpseList.get(lootChoice);
                            corpse.getBackpack().displayInventory();
                            int lootItemIndex = scanner.nextInt() - 1;

                            if (lootItemIndex < corpse.getBackpack().count()) {
                                Item item = corpse.getBackpack().getItem(lootItemIndex);
                                player.getBackpack().addItem(item);
                                corpse.getBackpack().removeItem(item);
                                if (corpse.getBackpack().count() == 0) {
                                    corpseList.remove(corpse);
                                }
                            }
                        }
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
        if (!corpseList.isEmpty()) {
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
