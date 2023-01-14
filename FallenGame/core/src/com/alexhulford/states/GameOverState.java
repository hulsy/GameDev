package com.alexhulford.states;

import static com.alexhulford.states.PlayState.bitFont;
import static com.alexhulford.states.PlayState.scoreStr;

import com.alexhulford.FallenGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State{

    private Texture background;
    private Texture gameOver;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FallenGame.FALLEN_GAME_WIDTH/2, FallenGame.FALLEN_GAME_HEIGHT/2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
//        sb.draw(background,0,0);
        bitFont.setColor(Color.BLACK);
        bitFont.draw(sb, scoreStr, 50, cam.viewportHeight - 50);
        sb.draw(gameOver, cam.position.x - (gameOver.getWidth()/2) , cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
    }
}