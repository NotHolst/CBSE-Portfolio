/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author movie
 */
public class AsteroidPlugin implements IGamePluginService{
    
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }
    
        @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity entity : world.getEntities(EntityType.ASTEROIDS)) {
            world.removeEntity(entity);
        }
    }
    
        private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Entity();
        asteroid.setType(EntityType.ASTEROIDS);

        asteroid.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);


        asteroid.setRadians(3.1415f / 2);
        asteroid.setRotationSpeed(5);

        asteroid.setRadius(200);

        return asteroid;
    }
    
}
