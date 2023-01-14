package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Platform {
    private static final int FLUCTUATION = 125;
    private static final int PLATFORM_OPENING = 80;
    public static final int PLATFORM_HEIGHT = 52;
    private Texture leftPlatform;
    private Texture rightPlatform;
    private Vector2 posLeftPlatform, posRightPlatform;
    private Rectangle boundsRight, boundsLeft;
    private Random rand;
    private Sound crash;

    public Platform(float y){
        leftPlatform = new Texture("leftplatform.png");
        rightPlatform = new Texture("rightplatform.png");
        rand = new Random();
        crash = Gdx.audio.newSound(Gdx.files.internal("crash.ogg"));

        posRightPlatform = new Vector2(rand.nextInt(FLUCTUATION) + PLATFORM_OPENING, y);
        posLeftPlatform = new Vector2(posRightPlatform.x - PLATFORM_OPENING - rightPlatform.getWidth(), y);

        boundsRight = new Rectangle(posRightPlatform.x, posRightPlatform.y, rightPlatform.getWidth(), rightPlatform.getHeight());
        boundsLeft = new Rectangle(posLeftPlatform.x, posLeftPlatform.y, leftPlatform.getWidth(), leftPlatform.getHeight());
    }

    public Texture getLeftPlatform(){
        return leftPlatform;
    }

    public Texture getRightPlatform(){
        return rightPlatform;
    }

    public Vector2 getPosLeftPlatform(){
        return posLeftPlatform;
    }
    public Vector2 getPosRightPlatform(){
        return posRightPlatform;
    }
    public void reposition(float y){
        posRightPlatform.set(rand.nextInt(FLUCTUATION) + PLATFORM_OPENING, y);
        posLeftPlatform.set(posRightPlatform.x - PLATFORM_OPENING - rightPlatform.getWidth(), y);
        boundsRight.setPosition(posRightPlatform.x, posRightPlatform.y);
        boundsLeft.setPosition(posLeftPlatform.x, posLeftPlatform.y);
    }

    public boolean collides (Rectangle player){
        crash.play(0.08f);
        return player.overlaps(boundsRight) || player.overlaps(boundsLeft);
    }
}
