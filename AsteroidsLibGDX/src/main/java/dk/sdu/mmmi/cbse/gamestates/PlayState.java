package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.entities.SpaceObject;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
        Player player;
	private List<SpaceObject> world;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		world = new ArrayList<>();
		sr = new ShapeRenderer();
		player = new Player();
                
		world.add(player);
                world.add(new Enemy());
		
	}
	
	public void update(float dt) {
		
            handleInput();
            for (SpaceObject spaceObject : world) {
                spaceObject.update(dt);
            }
		
	}
	
	public void draw() {
            for (SpaceObject spaceObject : world) {
                spaceObject.draw(sr);
            }
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









