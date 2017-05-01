package dk.sdu.mmmi.cbse.entitywrapper;

import dk.sdu.mmmi.cbse.common.data.Entity;
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
public class EntityWrapper implements IEntityProcessingService, IGamePluginService {
    
    List<Event> splitEvents = new ArrayList<>();

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void process(GameData gameData, World world) {

        for(Entity entity : world.getEntities()){
            if(entity.getX() - entity.getRadius() > gameData.getDisplayWidth())entity.setX(0 - entity.getRadius());
            if(entity.getY() - entity.getRadius() > gameData.getDisplayHeight())entity.setY(0 - entity.getRadius());
            if(entity.getX() + entity.getRadius() < 0)entity.setX(gameData.getDisplayWidth() + entity.getRadius());
            if(entity.getY() + entity.getRadius() < 0)entity.setY(gameData.getDisplayHeight() + entity.getRadius());
            
        }
    }

   

    @Override
    public void stop(GameData gameData, World world) {

    }

}
