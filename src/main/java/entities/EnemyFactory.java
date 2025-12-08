package entities;

import java.security.InvalidParameterException;

public class EnemyFactory {
    public EnemyFactory() {}

    public Enemy createEnemy(String type) throws InvalidParameterException {
        return switch (type) {
            case "goblin" -> new Goblin();
            case "orc" -> new Orc();
            default -> throw new InvalidParameterException(type + " is not a valid enemy type.");
        };
    }
}
