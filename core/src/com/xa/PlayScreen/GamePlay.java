package com.xa.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xa.Background.Background;
import com.xa.KeyInput.ArrowKeys;
import com.xa.MainGame;
import com.xa.Maps.MapLoader;
import com.xa.Maps.MyContact;
import com.xa.Object.Player;
import com.xa.Skills.SkillObject;

public class GamePlay implements Screen {

    private MainGame game;
    //background
    private Background background;
    //main maploader
    private OrthographicCamera gameCam;
    private Viewport gameViewport;
    //minimap loader
    private OrthographicCamera miniGameCam;
    private Viewport miniGameViewport;

    private FpsCounter fpsCounter;
    private MapLoader mapLoader;
    private MapLoader miniMapLoader;
    private ArrowKeys arrowKeys;

    private Player player;
    private TextureAtlas textureAtlas;
    //private HUD hud;
    float x, y =0;
    private SkillObject skillObject;

    public GamePlay(MainGame game){

        this.game = game;
        gameCam = new OrthographicCamera();
        gameViewport = new StretchViewport(getScaleWithPPM(MainGame.V_WIDTH), getScaleWithPPM(MainGame.V_HEIGHT), gameCam);
        miniGameCam = new OrthographicCamera();
        miniGameViewport = new StretchViewport(MainGame.V_WIDTH/7f, MainGame.V_HEIGHT/7f, miniGameCam);

        fpsCounter = new FpsCounter(game.batch);
        background = new Background(game.batch);
        mapLoader = new MapLoader("maps/map.tmx", this);
        miniMapLoader = new MapLoader("maps/map.tmx", this);
        arrowKeys = new ArrowKeys(game.batch, this);
        textureAtlas = new TextureAtlas("objects/Ninja.pack");
        player = new Player(mapLoader.getWorld(), this);
        mapLoader.getWorld().setContactListener(new MyContact());
        //hud = new HUD(game.batch);


        gameCam.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);
        miniGameCam.position.set(miniGameViewport.getWorldWidth() / 2, (miniGameViewport.getWorldHeight() / -2f) + getScaleWithPPM(miniGameViewport.getWorldHeight()*10), 0);
        skillObject = new SkillObject(game.batch);

    }

    @Override
    public void show() {

    }

    public void update(float delta){
        //update camera
        gameCam.update();
        miniGameCam.update();
        //set view camera
        mapLoader.getOrthogonalTiledMapRenderer().setView(gameCam);
        miniMapLoader.getOrthogonalTiledMapRenderer().setView(miniGameCam);

        //update player
        mapLoader.getWorld().step(1/60f, 10,2);
        miniMapLoader.getWorld().step(1/60f, 10,2);
        player.update(delta);
        skillObject.update(delta, player);

        //moving mini map to anywhere on screen
//            if(Gdx.input.getX() > miniGameCam.position.x &&  Gdx.input.getX() <= miniGameCam.position.x + miniGameCam.viewportWidth
//                    && Gdx.input.getY() > miniGameCam.position.y && Gdx.input.getY() <= miniGameCam.position.y + miniGameCam.viewportHeight){
//                miniGameCam.position.x += -Gdx.input.getDeltaX() / 5;
//                miniGameCam.position.y += Gdx.input.getDeltaY() / 5;
//            }
//            System.out.println(miniGameCam.position.x);
//            System.out.println(miniGameCam.position.y);
//            System.out.println(miniGameCam.viewportWidth);
//            System.out.println(miniGameCam.viewportHeight);
//            System.out.println("----------------------------");


        //update cemera when rich to max height or width of the map
        if(player.getBody().getPosition().x >= gameViewport.getWorldWidth() / 2
                && player.getBody().getPosition().x < ((mapLoader.getLayer().getTileWidth() * mapLoader.getLayer().getWidth()) / MainGame.PPM - (gameViewport.getWorldWidth() / 2))){
            gameCam.position.x = player.getBody().getPosition().x;
            background.setBackgroundPosition(player.getBody().getPosition().x - gameViewport.getWorldWidth() / 2);
        }else if(((mapLoader.getLayer().getTileWidth() * mapLoader.getLayer().getWidth()) / MainGame.PPM) - player.getBody().getPosition().x < gameViewport.getWorldWidth() / 2
                && ((mapLoader.getLayer().getTileWidth() * mapLoader.getLayer().getWidth()) / MainGame.PPM - (gameViewport.getWorldWidth() / 2)) <= player.getBody().getPosition().x){
            gameCam.position.x = ((mapLoader.getLayer().getTileWidth() * mapLoader.getLayer().getWidth()) / MainGame.PPM - (gameViewport.getWorldWidth() / 2));
            if(player.getBody().getPosition().x >= (mapLoader.getLayer().getTileWidth() * mapLoader.getLayer().getWidth()) / MainGame.PPM - 0.2){
                this.getPlayer().getBody().applyLinearImpulse(new Vector2(-1f, 0), this.getPlayer().getBody().getWorldCenter(), true);
            }
        }else {
            gameCam.position.x = gameViewport.getWorldWidth()/2;
            if(player.getBody().getPosition().x <= 0.2){
                this.getPlayer().getBody().applyLinearImpulse(new Vector2(1f, 0), this.getPlayer().getBody().getWorldCenter(), true);
            }
        }

        if(player.getBody().getPosition().y >= gameViewport.getWorldHeight() / 2){
            gameCam.position.y = player.getBody().getPosition().y;
            if(((mapLoader.getLayer().getTileHeight() * mapLoader.getLayer().getHeight()) / MainGame.PPM) - player.getBody().getPosition().y <= 0.2){
                this.getPlayer().getBody().applyLinearImpulse(new Vector2(0, -0.5f), this.getPlayer().getBody().getWorldCenter(), true);
            }
        }else{
            gameCam.position.y = gameViewport.getWorldHeight()/2;
        }

//        System.out.println(gameCam.position.x);
//        System.out.println(gameCam.position.y);
//        System.out.println(gameViewport.getWorldWidth()/2);
//        System.out.println(gameViewport.getWorldHeight()/2);
//        System.out.println(player.getBody().getPosition().x);
//        System.out.println(player.getBody().getPosition().y);
//        System.out.println("------------------------");

        //update joystick
        arrowKeys.handleInputForJoystick(delta);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        //render fps
        game.batch.setProjectionMatrix(fpsCounter.stage.getCamera().combined);
        fpsCounter.render();
        fpsCounter.stage.draw();

        //draw moving background, background speed is equal 10 * player position
        background.draw();

        //render map and objects
        mapLoader.getOrthogonalTiledMapRenderer().render();
        mapLoader.getBox2DDebugRenderer().render(mapLoader.getWorld(), gameCam.combined);
        miniMapLoader.getOrthogonalTiledMapRenderer().render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        skillObject.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(miniGameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        skillObject.draw(game.batch);
        game.batch.end();

        //draw joystick
        arrowKeys.draw();

        //render hud
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        miniGameViewport.update(width, height);
        arrowKeys.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public float getScaleWithPPM(float delta){
        return delta / MainGame.PPM;
    }

    public MainGame getGame() {
        return game;
    }

    public void setGame(MainGame game) {
        this.game = game;
    }

    public OrthographicCamera getGameCam() {
        return gameCam;
    }

    public void setGameCam(OrthographicCamera gameCam) {
        this.gameCam = gameCam;
    }

    public Viewport getGameViewport() {
        return gameViewport;
    }

    public void setGameViewport(Viewport gameViewport) {
        this.gameViewport = gameViewport;
    }

    public MapLoader getMapLoader() {
        return mapLoader;
    }

    public void setMapLoader(MapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public ArrowKeys getArrowKeys() {
        return arrowKeys;
    }

    public void setArrowKeys(ArrowKeys arrowKeys) {
        this.arrowKeys = arrowKeys;
    }

    public FpsCounter getFpsCounter() {
        return fpsCounter;
    }

    public void setFpsCounter(FpsCounter fpsCounter) {
        this.fpsCounter = fpsCounter;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }


}
