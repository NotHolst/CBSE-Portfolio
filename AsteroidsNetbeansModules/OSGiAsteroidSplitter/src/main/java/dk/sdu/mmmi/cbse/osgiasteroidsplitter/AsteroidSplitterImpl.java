/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiasteroidsplitter;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IAsteroidSplitter;
import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter{

    @Override
    public void splitAsteroid(Entity asteroid, World world) {
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
