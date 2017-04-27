package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
    
        /**
         * Runs after initial game entity updates, 
         * which is useful for things like collision checking
         * or "game of life" type programs
         * @param gameData
         * @param world 
         */
        void process(GameData gameData, World world);
}
