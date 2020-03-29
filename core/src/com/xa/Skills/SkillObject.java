package com.xa.Skills;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.xa.MainGame;
import com.xa.Object.Player;
import com.xa.PlayScreen.GamePlay;

public class SkillObject extends Sprite {
    float x;
    private boolean isAttacking;
    private float skillPositionXWhenActive;
    private float skillPositionYWhenActive;
    private boolean isSkillOnRight;

    private Animation<TextureRegion> playerAttack;

    private GamePlay game;
    private World world;
    private Body body;

    public SkillObject(World world, GamePlay game){
        super(new TextureAtlas("skills/flame.pack").findRegion("flame"));
        this.world = world;
        this.game = game;

        x = 0;
        isAttacking = false;
        skillPositionXWhenActive =0;
        skillPositionYWhenActive =0;
        isSkillOnRight = false;

        //standing animation
        Array<TextureRegion> framesArray = new Array<TextureRegion>();
        for(int i = 0 ;i<12;i++){
            framesArray.add(new TextureRegion(getTexture(),i*64, 0*64, 64, 64));
        }
        playerAttack = new Animation<>(0.1f, framesArray);
        framesArray.clear();
        setBounds(0, 0, 64 / MainGame.PPM, 64 / MainGame.PPM);
        drawSkill();
    }

    public void drawSkill(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(game.getScaleWithPPM(100f), game.getScaleWithPPM(100f));//set position where player spawn
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(game.getScaleWithPPM(15), game.getScaleWithPPM(15));
        fixtureDef.shape = polygonShape;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData(this);
    }

    public void update(float delta, Player player){

        if(isAttacking && !playerAttack.isAnimationFinished(x)){
            if(isSkillOnRight){
                body.setTransform(skillPositionXWhenActive + 130 / MainGame.PPM, skillPositionYWhenActive + 30 / MainGame.PPM, 0);
                setPosition(skillPositionXWhenActive + 100 / MainGame.PPM, skillPositionYWhenActive + 0 / MainGame.PPM);
            }else{
                body.setTransform(skillPositionXWhenActive - 130 / MainGame.PPM, skillPositionYWhenActive + 30 / MainGame.PPM, 0);
                setPosition(skillPositionXWhenActive - 160 / MainGame.PPM, skillPositionYWhenActive + 0 / MainGame.PPM);
            }
            setRegion(drawFrame(delta));
        }else{
            x=0;
            isAttacking = false;
            isSkillOnRight = false;
        }
    }

    private TextureRegion drawFrame(float delta) {
        TextureRegion region = playerAttack.getKeyFrame(x, true);
        //region.flip(true, false);
        x+=delta*2;

        return region;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public float getSkillPositionXWhenActive() {
        return skillPositionXWhenActive;
    }

    public void setSkillPositionXWhenActive(float skillPositionXWhenActive) {
        this.skillPositionXWhenActive = skillPositionXWhenActive;
    }

    public float getSkillPositionYWhenActive() {
        return skillPositionYWhenActive;
    }

    public void setSkillPositionYWhenActive(float skillPositionYWhenActive) {
        this.skillPositionYWhenActive = skillPositionYWhenActive;
    }

    public boolean isSkillOnRight() {
        return isSkillOnRight;
    }

    public void setSkillOnRight(boolean skillOnRight) {
        isSkillOnRight = skillOnRight;
    }
}
