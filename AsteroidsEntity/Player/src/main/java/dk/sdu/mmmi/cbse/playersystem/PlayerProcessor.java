package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */

public class PlayerProcessor implements IEntityProcessingService {
    
private float shootCooldown = .1f;
private float cooldown = 0;
    
    @Override
    public void process(GameData gameData, World world) {
        for( Entity player : world.getEntities(EntityType.PLAYER)){
            
            if(gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRadians(player.getRadians() + player.getRotationSpeed() * gameData.getDelta());
            }
            else if(gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRadians(player.getRadians() - player.getRotationSpeed() * gameData.getDelta());
            }
            
            if(gameData.getKeys().isDown(GameKeys.UP)){
                player.setDx(player.getDx()+(float)(Math.cos(player.getRadians()) * player.getAcceleration()*gameData.getDelta()));
                player.setDy(player.getDy()+(float)(Math.sin(player.getRadians()) * player.getAcceleration()*gameData.getDelta()));
            }
            
            if(cooldown > 0) cooldown -= gameData.getDelta();
            if(gameData.getKeys().isDown(GameKeys.SPACE) && cooldown <= 0){
                gameData.addEvent(new Event(EventType.PLAYER_SHOOT, player.getID()));
                cooldown = shootCooldown;
            }
                
            player.setX(player.getX() + (player.getDx()*gameData.getDelta()));
            player.setY(player.getY() + (player.getDy()*gameData.getDelta()));
            
            float vec = (float) Math.sqrt(player.getDx() * player.getDx() + player.getDy() * player.getDy());
		if(vec > 0) {
			player.setDx(player.getDx() - (player.getDx() / vec) * player.getDeacceleration() * gameData.getDelta());
			player.setDy(player.getDy() - (player.getDy() / vec) * player.getDeacceleration() * gameData.getDelta());
		}
		if(vec > player.getMaxSpeed()) {
			player.setDx((player.getDx() / vec) * player.getMaxSpeed());
			player.setDy((player.getDy() / vec) * player.getMaxSpeed());
		}

            setShape(player);
        }
    }
    
    private void setShape(Entity player){
        float x = player.getX();
        float y = player.getY();
        float radians = player.getRadians();
        
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        
        shapex[0] = (float) (x + Math.cos(radians) * 8f);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);
        
        player.setShapeX(shapex);
        player.setShapeY(shapey);
    }
}
