package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.Map;

public interface IEntityProcessingService {
    
    /**
     * Runs for every entity on every game tick
     * @param gameData
     * @param world 
     */
    void process(GameData gameData, World world);
}
