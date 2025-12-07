package systems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import entities.Entity;

public class EffectDispatcher {
    private static final Map<String, BiConsumer<Entity, Effect>> handlers = new HashMap<>();

    static {
        handlers.put("heal", (entity, effect) ->
                entity.restoreHealth(effect.getMagnitude())
        );
    }

    public static void apply(Entity target, Effect effect) {
        handlers.get(effect.getType()).accept(target,effect);
    }
}
