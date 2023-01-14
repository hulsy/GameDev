package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
//    private final static int GRAVITY = -10;
    private final static int MOVEMENT = 4;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation playerRightAnimation;
    private Texture texture;

    public Player(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0,0);
        texture = new Texture("playeranimation.png");
        playerRightAnimation = new Animation(new TextureRegion(texture), 6, 1.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 6, texture.getHeight());
    }

    public void update(float dt){
        playerRightAnimation.update(dt);
        velocity.scl(dt);
        position.add(velocity.x,MOVEMENT, 0);

        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return playerRightAnimation.getFrame();
    }

    public void moveRight(){
        position.x += 5;
    }
    public void moveLeft(){
        position.x -= 5;
    }

//    public void jump(){
//        velocity.y = -250;
//    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
