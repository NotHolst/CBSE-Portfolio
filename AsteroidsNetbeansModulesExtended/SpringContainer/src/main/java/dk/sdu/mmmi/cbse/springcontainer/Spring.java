
package dk.sdu.mmmi.cbse.springcontainer;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.springplayer.PlayerControlSystem;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ServiceProviders(value = { 
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)}
)
public class Spring implements IGamePluginService, IEntityProcessingService{
    
    PlayerControlSystem playerControlSystem;
    
    @Override
    public void start(GameData gameData, World world) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        playerControlSystem = (PlayerControlSystem) context.getBean("playerBean");
        playerControlSystem.start(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        playerControlSystem.stop(gameData, world);
    }

    @Override
    public void process(GameData gameData, World world) {
        playerControlSystem.process(gameData, world);
    }

}
