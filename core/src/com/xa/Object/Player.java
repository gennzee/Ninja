package com.xa.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.xa.KeyInput.ArrowKeys;
import com.xa.PlayScreen.GamePlay;
import com.xa.Skills.SkillObject;

public class Player extends Sprite {

    public enum State{STANDING, WALKING, RUNNING, JUMPING, FALLING}

    private GamePlay game;
    private World world;
    private Body body;

    private TextureAtlas textureAtlas;
    private State currentState;
    private State previousState;
    private Animation<TextureRegion> playerRunning;
    private Animation<TextureRegion> playerWalking;
    private Animation<TextureRegion> playerJumping;
    private Animation<TextureRegion> playerStanding;
    private Animation<TextureRegion> playerFalling;
    private boolean isRunningRight;
    private float stateTimer;
    private Sprite testSprite;

    private TextureRegion playerIdle;
    private SkillObject skillObject;

    public Player(World world, GamePlay game, SkillObject skillObject){
        super(game.getTextureAtlas().findRegion("idle"));
        this.world = world;
        this.game = game;
        this.skillObject = skillObject;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        isRunningRight = true;

        //standing animation
        Array<TextureRegion> framesArray = new Array<TextureRegion>();
        for(int i = Math.round(925f/32f) ;i<Math.round((32f/32f)+(925f/32f));i++){
            framesArray.add(new TextureRegion(getTexture(),i*32, 0*64, 32, 68));
        }
        playerStanding = new Animation<>(0.1f, framesArray);
        framesArray.clear();
        //getFrames(framesArray, playerStanding, 1, 27, 1);

        //walking animation
        for(int i = Math.round(544f/32f) ;i<((192f/32f)+(544f/32f));i++){
            framesArray.add(new TextureRegion(getTexture(), i*32, 0*64, 32, 68));
        }
        playerWalking = new Animation<>(0.1f, framesArray);
        framesArray.clear();

        //running animation
        for(int i = Math.round(352f/32f) ;i<((192f/32f)+(352f/32f));i++){
            framesArray.add(new TextureRegion(getTexture(), i*32, 0*64, 32, 68));
        }
        playerRunning = new Animation<>(0.1f, framesArray);
        framesArray.clear();

        //jumping animation
        for(int i = Math.round(736f/32f) ;i<Math.round((96f/32f)+(736f/32f));i++){
            framesArray.add(new TextureRegion(getTexture(), i*32, 0*64, 32, 68));
        }
        playerJumping = new Animation<>(0.1f, framesArray);
        framesArray.clear();

        //falling animation
        for(int i = Math.round(832f/32f) ;i<Math.round((93f/32f)+(832f/32f));i++){
            framesArray.add(new TextureRegion(getTexture(), i*32, 0*48, 32, 68));
        }
        playerFalling = new Animation<>(0.1f, framesArray);
        framesArray.clear();


        setBounds(0, 0, game.getScaleWithPPM(32), game.getScaleWithPPM(68));
        drawNinjaPlayer();
    }

    public void drawNinjaPlayer(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(game.getScaleWithPPM(100f), game.getScaleWithPPM(100f));//set position where player spawn
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(game.getScaleWithPPM(13), game.getScaleWithPPM(25));
        fixtureDef.shape = polygonShape;

        body.createFixture(fixtureDef).setUserData("player");
    }

    public void update(float delta, ArrowKeys arrowKeys){
        setPosition(body.getPosition().x -getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
        //update skill object
        if(Gdx.input.isKeyJustPressed(Input.Keys.C) || arrowKeys.isPressAttack()) {
            if(this.isRunningRight()){
                skillObject.setSkillOnRight(true);
            }
            skillObject.setAttacking(true);
            skillObject.setSkillPositionXWhenActive(this.getBody().getPosition().x);
            skillObject.setSkillPositionYWhenActive(this.getBody().getPosition().y);
        }
        skillObject.update(delta, this);
    }

    public void render(){
        this.draw(game.getGame().batch);
        //draw skill object
        if(skillObject.isAttacking()) {
            skillObject.draw(game.getGame().batch);
        }
    }


    public TextureRegion getFrame(float delta){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = playerJumping.getKeyFrame(stateTimer);
                break;
            case WALKING:
                region = playerWalking.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = playerRunning.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
                region = playerFalling.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = playerStanding.getKeyFrame(stateTimer, true);
                break;
            default:
                region = playerIdle;
                break;

        }

        if((body.getLinearVelocity().x < 0 || !isRunningRight) && !region.isFlipX()){
            region.flip(true, false);
            isRunningRight = false;
        }else if((body.getLinearVelocity().x > 0 || isRunningRight) && region.isFlipX()){
            region.flip(true, false);
            isRunningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    private State getState(){
        if(body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) return State.JUMPING;
        if(body.getLinearVelocity().y < 0) return State.FALLING;
        if(body.getLinearVelocity().x <= 1.9){
            if(body.getLinearVelocity().x == 0.0){
                return State.STANDING;
            }else if(body.getLinearVelocity().x >= -1.9){
                return State.WALKING;
            }
        }
        if(body.getLinearVelocity().x != 0) return State.RUNNING;
        return State.STANDING;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public boolean isRunningRight() {
        return isRunningRight;
    }

    public void setRunningRight(boolean runningRight) {
        isRunningRight = runningRight;
    }
}
