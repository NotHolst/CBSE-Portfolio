package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jcs
 */
public class AsteroidSystem implements IEntityProcessingService {
        
    List<Event> splitEvents = new ArrayList<>();
    
    @Override
    public void process(GameData gameData, World world) {
        
        for(Event splitEvent : gameData.getEvents()){
            if(splitEvent.getType() == EventType.ASTEROID_SPLIT){
                splitEvents.add(splitEvent);
                gameData.removeEvent(splitEvent);
            }
        }
        
        for( Entity asteroid : world.getEntities(EntityType.ASTEROIDS)){
            asteroid.setX(asteroid.getX() + (asteroid.getDx()*gameData.getDelta()));
            asteroid.setY(asteroid.getY() + (asteroid.getDy()*gameData.getDelta()));
            setShape(asteroid);
            for(Entity bullet : world.getEntities(EntityType.BULLET)){
                if(Math.sqrt(Math.pow(bullet.getX() - asteroid.getX(), 2) + Math.pow(bullet.getY() - asteroid.getY(), 2)) < bullet.getRadius() + asteroid.getRadius()){
                    world.removeEntity(bullet);
                    world.removeEntity(asteroid);
                    if(asteroid.getRadius() > 20)
                    for(int i = 0; i < 2; i++){
                        Entity a = new Entity();
                        a.setType(EntityType.ASTEROIDS);
                        a.setRadius(asteroid.getRadius()/2);
                        a.setPosition(asteroid.getX(), asteroid.getY());
                        Random r = new Random();
                        a.setDx(-50/2 + r.nextInt(50));
                        a.setDy(-50/2 + r.nextInt(50));
                        world.addEntity(a);
                    }
                }
            }
        }
        
    }
    private void setShape(Entity entity){
        if(entity.getRadius() < 1) entity.setRadius(1);
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        
        float[] shapex = new float[8];
        float[] shapey = new float[8];
        
        Random r = new Random(entity.getID().hashCode()+1337);
        int variance = 1 + (int)Math.abs(entity.getRadius()/2);
        
        shapex[0] = (float) (x + Math.cos(radians + (0 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[0] = (float) (y + Math.sin(radians + (0 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));

        shapex[1] = (float) (x + Math.cos(radians + (0.25 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[1] = (float) (y + Math.sin(radians + (0.25 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));

        shapex[2] = (float) (x + Math.cos(radians + (0.5 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[2] = (float) (y + Math.sin(radians + (0.5 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));

        shapex[3] = (float) (x + Math.cos(radians + (0.75 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[3] = (float) (y + Math.sin(radians + (0.75 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        
        shapex[4] = (float) (x + Math.cos(radians + (1 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[4] = (float) (y + Math.sin(radians + (1 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        
        shapex[5] = (float) (x + Math.cos(radians + (1.25 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[5] = (float) (y + Math.sin(radians + (1.25 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        
        shapex[6] = (float) (x + Math.cos(radians + (1.5 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[6] = (float) (y + Math.sin(radians + (1.5 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        
        shapex[7] = (float) (x + Math.cos(radians + (1.75 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        shapey[7] = (float) (y + Math.sin(radians + (1.75 * Math.PI)) * entity.getRadius() + (-variance/2 + r.nextInt(variance)));
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
