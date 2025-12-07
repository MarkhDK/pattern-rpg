package entities;

import java.security.InvalidParameterException;

public class EnemyFactory {
    private static EnemyFactory instance;

    private EnemyFactory() {}

    public Enemy createEnemy(String type) throws InvalidParameterException {
        return switch (type) {
            case "goblin" -> new Goblin();
            case "orc" -> new Orc();
            default -> throw new InvalidParameterException(type + " is not a valid enemy type.");
        };
    }

    public static EnemyFactory getInstance() {
        if(instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }
}
