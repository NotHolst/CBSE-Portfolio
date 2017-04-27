/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.entitywrapper;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author movie
 */
public class EntityWrapperSystem implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities()){
            if(entity.getX() - entity.getRadius() > gameData.getDisplayWidth())entity.setX(0 - entity.getRadius());
            if(entity.getY() - entity.getRadius() > gameData.getDisplayHeight())entity.setY(0 - entity.getRadius());
            if(entity.getX() + entity.getRadius() < 0)entity.setX(gameData.getDisplayWidth() + entity.getRadius());
            if(entity.getY() + entity.getRadius() < 0)entity.setY(gameData.getDisplayHeight() + entity.getRadius());
            
        }
    }
    
}
