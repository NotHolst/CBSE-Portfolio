
package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class EnemySystem implements IEntityProcessingService, IGamePluginService{


    @Override
    public void start(GameData gameData, World world) {
        System.out.println("I STARTED YAY");
    }

    @Override
    public void stop(GameData gameData, World world) {
                System.out.println("I STARTED YAY");

    }

    @Override
    public void process(GameData gameData, World world) {
                System.out.println("I STARTED YAY");

        gameData.addEvent(new Event(EventType.PLAYER_SHOOT, world.getEntities(EntityType.PLAYER).get(0).getID()));
    }
}
