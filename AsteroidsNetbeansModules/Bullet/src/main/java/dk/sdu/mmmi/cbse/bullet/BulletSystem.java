package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    , 
    @ServiceProvider(service = IGamePluginService.class)}
)
public class BulletSystem implements IEntityProcessingService, IGamePluginService {
    
    List<Event> splitEvents = new ArrayList<>();

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void process(GameData gameData, World world) {

        for(Event shootEvent : gameData.getEvents()){
            if(shootEvent.getType() == EventType.PLAYER_SHOOT){
                for(Entity player : world.getEntities(EntityType.PLAYER)){
                    world.addEntity(createBullet(player));
                    gameData.removeEvent(shootEvent);
                }
            }
        }
        
        for( Entity bullet : world.getEntities(EntityType.BULLET)){
            if(bullet.getExpiration() <= 0) world.removeEntity(bullet);
            bullet.setX(bullet.getX() + (bullet.getDx()*gameData.getDelta()));
            bullet.setY(bullet.getY() + (bullet.getDy()*gameData.getDelta()));
            bullet.reduceExpiration(gameData.getDelta());
            updateShape(bullet);
        }
    }

    private void updateShape(Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        
        shapex[0] = (float) (x + Math.cos(radians) * 2f);
        shapey[0] = (float) (y + Math.sin(radians) * 2);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 1.6) * 2);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 1.6) * 2);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 1.6);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 1.6);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 1.6) * 2);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 1.6) * 2);
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private Entity createBullet(Entity player){
        Entity bullet = new Entity();
        bullet.setType(EntityType.BULLET);
        bullet.setRadius(2);
        bullet.setRadians(player.getRadians());
        bullet.setMaxSpeed(200);
        bullet.setX(player.getX() + (float)Math.cos(player.getRadians()) * 8.2f);
        bullet.setY(player.getY() + (float)Math.sin(player.getRadians()) * 8.2f);
        bullet.setDx(player.getDx() + (float)Math.cos(player.getRadians())* bullet.getMaxSpeed());
        bullet.setDy(player.getDy() + (float)Math.sin(player.getRadians())* bullet.getMaxSpeed());
        bullet.setExpiration(2);
        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity entity : world.getEntities(EntityType.BULLET)) {
            world.removeEntity(entity);
        }
    }

}
