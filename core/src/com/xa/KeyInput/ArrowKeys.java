package com.xa.KeyInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xa.MainGame;
import com.xa.PlayScreen.GamePlay;

public class ArrowKeys {

    private GamePlay game;

    private Viewport viewport;
    private Stage stage;
    private boolean isPressUp, isPressDown, isPressRight, isPressLeft,
            isPressLeftUp, isPressLeftDown,isPressRightUp,isPressRightDown, isPressMiddle, isPressJump, isPressAttack;

    public ArrowKeys(SpriteBatch spriteBatch, GamePlay game){
        this.game = game;
        viewport = new StretchViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image imgJump = new Image(new Texture("stickKeys/jump.png"));
        imgJump.setColor(1f,1f,1f,.5f);
        imgJump.setSize(50,50);
        imgJump.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressJump = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressJump = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgAttack = new Image(new Texture("stickKeys/attackButton.png"));
        imgAttack.setColor(1f,1f,1f,.5f);
        imgAttack.setSize(70,70);
        imgAttack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressAttack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressAttack = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgDown = new Image(new Texture("stickKeys/down.png"));
        imgDown.setColor(1f,1f,1f,.5f);
        imgDown.setSize(50,50);
        imgDown.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressDown = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgUp = new Image(new Texture("stickKeys/up.png"));
        imgUp.setColor(1f,1f,1f,.5f);
        imgUp.setSize(50,50);
        imgUp.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressUp = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressUp = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgLeft = new Image(new Texture("stickKeys/left.png"));
        imgLeft.setColor(1f,1f,1f,.5f);
        imgLeft.setSize(50,50);
        imgLeft.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeft = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeft = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgMiddle = new Image(new Texture("stickKeys/middle.png"));
        imgMiddle.setColor(1f,1f,1f,.5f);
        imgMiddle.setSize(50,50);
        imgMiddle.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressMiddle = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressMiddle = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgRight = new Image(new Texture("stickKeys/right.png"));
        imgRight.setColor(1f,1f,1f,.5f);
        imgRight.setSize(50,50);
        imgRight.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressRight = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressRight = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgLeftUp = new Image(new Texture("stickKeys/leftUp.png"));
        imgLeftUp.setColor(1f,1f,1f,.5f);
        imgLeftUp.setSize(50,50);
        imgLeftUp.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeftUp = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeftUp = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgLeftDown = new Image(new Texture("stickKeys/leftDown.png"));
        imgLeftDown.setColor(1f,1f,1f,.5f);
        imgLeftDown.setSize(50,50);
        imgLeftDown.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeftDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressLeftDown = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgRightUp = new Image(new Texture("stickKeys/rightUp.png"));
        imgRightUp.setColor(1f,1f,1f,.5f);
        imgRightUp.setSize(50,50);
        imgRightUp.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressRightUp = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressRightUp = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        Image imgRightDown = new Image(new Texture("stickKeys/rightDown.png"));
        imgRightDown.setColor(1f,1f,1f,.5f);
        imgRightDown.setSize(50,50);
        imgRightDown.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressRightDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressRightDown = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }
        });

        //table.add();
        table.add(imgLeftUp).size(imgLeftUp.getWidth(), imgLeftUp.getHeight());
        table.pad(5,5,5,5);
        table.add(imgUp).size(imgUp.getWidth(), imgUp.getHeight());
        table.pad(5,5,5,5);
        table.add(imgRightUp).size(imgRightUp.getWidth(), imgRightUp.getHeight());
        table.pad(5,5,5,5);
        table.row();
        table.add(imgLeft).size(imgLeft.getWidth(), imgLeft.getHeight());
        table.pad(5,5,5,5);
        table.add(imgMiddle).size(imgMiddle.getWidth(), imgMiddle.getHeight());
        table.pad(5,5,5,5);
        table.add(imgRight).size(imgRight.getWidth(), imgRight.getHeight());
        table.pad(5,5,5,5);
        table.row();
        table.add(imgLeftDown).size(imgLeftDown.getWidth(), imgLeftDown.getHeight());
        table.pad(5,5,5,5);
        table.add(imgDown).size(imgDown.getWidth(), imgDown.getHeight());
        table.pad(5,5,5,5);
        table.add(imgRightDown).size(imgRightDown.getWidth(), imgRightDown.getHeight());
        table.pad(5,5,5,5);
        stage.addActor(table);

        Table table2 = new Table();
        table2.setPosition(750,70);
        //table.add();
        table2.add(imgJump).size(imgJump.getWidth(), imgJump.getHeight()).expandX();
        table2.pad(5,5,5,5);
        stage.addActor(table2);

        Table table3 = new Table();
        table3.setPosition(680,50);
        table3.add(imgAttack).size(imgAttack.getWidth(), imgAttack.getHeight()).expandX();
        table3.pad(5,5,5,5);
        stage.addActor(table3);
    }

    public void draw(){
        stage.draw();
    }

    public void handleInputForJoystick(float delta){

        //for windows
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)/* && game.getPlayer().getBody().getLinearVelocity().y == 0*/)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && game.getPlayer().getBody().getLinearVelocity().x <= 2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && game.getPlayer().getBody().getLinearVelocity().x >= -2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(-0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);

        //for android
        if(isPressJump() && game.getPlayer().getBody().getLinearVelocity().y == 0)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(isPressUp() && game.getPlayer().getBody().getLinearVelocity().y == 0)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(isPressLeftUp() && game.getPlayer().getBody().getLinearVelocity().y == 0)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(-0.8f, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(isPressRightUp() && game.getPlayer().getBody().getLinearVelocity().y == 0)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0.8f, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(isPressRight() && game.getPlayer().getBody().getLinearVelocity().x <= 2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);
        if(isPressLeft() && game.getPlayer().getBody().getLinearVelocity().x >= -2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(-0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);

    }

    public void handleInput(float delta){
        if(Gdx.input.isTouched()){
            game.getGameCam().position.x += Gdx.input.getDeltaX() * -2;
            game.getGameCam().position.y += Gdx.input.getDeltaY() * 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && game.getPlayer().getBody().getLinearVelocity().y == 0)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0, 6f), game.getPlayer().getBody().getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && game.getPlayer().getBody().getLinearVelocity().x <= 2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && game.getPlayer().getBody().getLinearVelocity().x >= -2)
            game.getPlayer().getBody().applyLinearImpulse(new Vector2(-0.1f, 0), game.getPlayer().getBody().getWorldCenter(), true);
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public boolean isPressUp() {
        return isPressUp;
    }

    public boolean isPressDown() {
        return isPressDown;
    }

    public boolean isPressRight() {
        return isPressRight;
    }

    public boolean isPressLeft() {
        return isPressLeft;
    }

    public boolean isPressLeftUp() {
        return isPressLeftUp;
    }

    public boolean isPressLeftDown() {
        return isPressLeftDown;
    }

    public boolean isPressRightUp() {
        return isPressRightUp;
    }

    public boolean isPressRightDown() {
        return isPressRightDown;
    }

    public boolean isPressMiddle() {
        return isPressMiddle;
    }

    public boolean isPressJump() {
        return isPressJump;
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isPressAttack() {
        return isPressAttack;
    }
}
