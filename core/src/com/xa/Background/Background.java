package com.xa.Background;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xa.MainGame;

import java.util.ArrayList;
import java.util.List;

public class Background {

    private List<Texture> textureList;
    private SpriteBatch spriteBatch;
    private float speedOfBackground = 10;
    private float backgroundPosition = 0;

    public Background(SpriteBatch spriteBatch){
        this.spriteBatch = spriteBatch;
        textureList = new ArrayList<>();
        textureList.add(new Texture("background/BG_1.png"));
        textureList.add(new Texture("background/BG_2_Clouds.png"));
        textureList.add(new Texture("background/BG_6_Forest_1.png"));

    }

    public void draw(){
        //spriteBatch.setColor(Color.WHITE);//clear stage before render background to remove transparent effect
        spriteBatch.begin();
        for (int i = 0; i < textureList.size() ; i++){
            spriteBatch.draw(textureList.get(i), -backgroundPosition*speedOfBackground,0, MainGame.V_WIDTH,MainGame.V_HEIGHT);
        }
        for (int i = 0; i < textureList.size() ; i++){
            spriteBatch.draw(textureList.get(i), -backgroundPosition*speedOfBackground+MainGame.V_WIDTH,0, MainGame.V_WIDTH,MainGame.V_HEIGHT);
        }
        spriteBatch.end();
    }

    public void setBackgroundPosition(float backgroundPosition) {
        this.backgroundPosition = backgroundPosition;
    }

    public float getBackgroundPosition() {
        return backgroundPosition;
    }
}
