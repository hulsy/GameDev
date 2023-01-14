package com.alexhulford.states;

import com.alexhulford.FallenGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import org.w3c.dom.Text;

import sprites.Platform;
import sprites.Player;

public class PlayState extends State{
    private static final int PLATFORM_SPACING = 180;
    private static final int PLATFORM_COUNT = 4;

    private Player player;
    private Texture bg;
    private Texture leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;
    public static int score;
    public static String scoreStr;
    public static BitmapFont bitFont;


    private Array<Platform> platforms;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(105, 350);
        bg = new Texture("bg.png");
        leftWall = new Texture("leftwall.png");
        rightWall = new Texture("rightwall.png");
        cam.setToOrtho(false, FallenGame.FALLEN_GAME_WIDTH/2, FallenGame.FALLEN_GAME_HEIGHT/2);
        platforms = new Array<Platform>();
        leftWallPos1 = new Vector2(0, cam.position.y -(cam.viewportHeight / 2));
        leftWallPos2 = new Vector2(0, cam.position.y -(cam.viewportHeight / 2) + leftWall.getHeight());
        rightWallPos1 = new Vector2(cam.viewportWidth-10, cam.position.y -(cam.viewportHeight / 2));
        rightWallPos2 = new Vector2(cam.viewportWidth-10, cam.position.y -(cam.viewportHeight / 2) + rightWall.getHeight());
        score = 0;
        scoreStr = "Score: " + score;
        bitFont = new BitmapFont();

        for(int i = 1; i <= PLATFORM_COUNT; i++){
            platforms.add(new Platform(i * PLATFORM_SPACING + Platform.PLATFORM_HEIGHT + 300));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            player.moveRight();
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            player.moveLeft();
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            player.jump();
//        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
        updateWall();
        cam.position.y = player.getPosition().y +80;

        for (Platform platform : platforms){
            if(cam.position.y -(cam.viewportHeight /2) > platform.getPosRightPlatform().y + platform.getRightPlatform().getHeight()) {
                platform.reposition(platform.getPosRightPlatform().y + ((platform.PLATFORM_HEIGHT + PLATFORM_SPACING) * PLATFORM_COUNT));
            }
            if(platform.collides(player.getBounds())){
                gsm.set(new GameOverState(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
//        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        for(Platform platform : platforms){
            sb.draw(platform.getRightPlatform(), platform.getPosRightPlatform().x, platform.getPosRightPlatform().y);
            sb.draw(platform.getLeftPlatform(), platform.getPosLeftPlatform().x, platform.getPosLeftPlatform().y);
        }
        sb.draw(leftWall, leftWallPos1.x, leftWallPos1.y);
        sb.draw(leftWall, leftWallPos2.x, leftWallPos2.y);
        sb.draw(rightWall, rightWallPos1.x, rightWallPos1.y);
        sb.draw(rightWall, rightWallPos2.x, rightWallPos2.y);
        bitFont.setColor(Color.BLACK);
        bitFont.draw(sb, scoreStr, 50, cam.viewportHeight - 50);
        sb.end();
    }

    private void updateWall(){
        if(cam.position.y - (cam.viewportHeight / 2) > leftWallPos1.y + leftWall.getHeight()){
            leftWallPos1.add(0, leftWall.getHeight()*2);
            score+=2;
            scoreStr = "Score: " + score;
        }
        if(cam.position.y - (cam.viewportHeight / 2) > leftWallPos2.y + leftWall.getHeight()){
            leftWallPos2.add(0, leftWall.getHeight()*2);
        }
        if(cam.position.y - (cam.viewportHeight / 2) > rightWallPos1.y + rightWall.getHeight()){
            rightWallPos1.add(0, rightWall.getHeight()*2);
        }
        if(cam.position.y - (cam.viewportHeight / 2) > rightWallPos2.y + rightWall.getHeight()){
            rightWallPos2.add(0, rightWall.getHeight()*2);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        leftWall.dispose();
        rightWall.dispose();
        player.dispose();
    }
}
