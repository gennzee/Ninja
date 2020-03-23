package com.xa.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xa.MainGame;

public class FpsCounter {

    public Stage stage;
    private Viewport viewport;

    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private BitmapFont font;

    Table table;
    Label fps;
    Label fpsCounter;

    public FpsCounter(SpriteBatch spriteBatch){
        viewport = new StretchViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();

        table = new Table();
        table.top();
        table.setFillParent(true);
        fps = new Label("FPS  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fpsCounter = new Label(String.valueOf(frameRate), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(fps);
        table.add(fpsCounter);
        stage.addActor(table);

    }

    public void render() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if(sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
        fpsCounter.setText(String.valueOf(frameRate));
    }

}
