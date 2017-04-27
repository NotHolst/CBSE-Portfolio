package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class EntityPlugin implements IGamePluginService {

    public EntityPlugin() {
    }
    
    @Override
    public void start(GameData gameData, World world) {
        for(int i = 0; i < 1; i++){
            Entity entity = new Entity();
            entity.setPosition(gameData.getDisplayWidth()/2, gameData.getDisplayHeight()/2);
            entity.setType(EntityType.ASTEROIDS);
            entity.setRadius(200);
            Random r = new Random();
            entity.setDx(-50/2 + r.nextInt(50));
            entity.setDy(-50/2 + r.nextInt(50));
            world.addEntity(entity);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity asteroid : world.getEntities(EntityType.ASTEROIDS))world.removeEntity(asteroid);
    }

}
