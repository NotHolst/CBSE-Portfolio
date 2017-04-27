package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    
    /**
     * Initialises the given module
     * @param gameData
     * @param world 
     */
    void start(GameData gameData, World world);
    
    /**
     * Disposes the module and its variables.
     * @param gameData
     * @param world 
     */
    void stop(GameData gameData, World world);
}
