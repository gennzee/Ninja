package com.xa.Skills;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.xa.MainGame;
import com.xa.Object.Player;

public class SkillObject extends Sprite {
    int x = 0;
    private Animation<TextureRegion> playerAttack;

    public SkillObject(SpriteBatch spriteBatch){
        super(new TextureAtlas("skills/flame.pack").findRegion("flame"));

        //standing animation
        Array<TextureRegion> framesArray = new Array<TextureRegion>();
        for(int i = 0 ;i<11;i++){
            framesArray.add(new TextureRegion(getTexture(),i*64, 0*64, 64, 64));
        }
        playerAttack = new Animation<>(0.1f, framesArray);
        framesArray.clear();
        setBounds(0, 0, 64 / MainGame.PPM, 64 / MainGame.PPM);
    }

    public void update(float delta, Player player){
        setPosition(player.getBody().getPosition().x + 100 / MainGame.PPM, player.getBody().getPosition().y + 0 / MainGame.PPM);
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        TextureRegion region = playerAttack.getKeyFrame(x++, true);
        region.flip(true, false);
        return region;
    }

}
