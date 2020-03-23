package com.xa.Maps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xa.PlayScreen.GamePlay;

public class MapLoader {

    private GamePlay game;

    //load map
    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    //create map object, gravity, collision, coordinate, ... using box2d
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private TiledMapTileLayer layer;


    public MapLoader(String map, GamePlay game){
        this.game = game;
        //load map
        tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load(map);
        layer = (TiledMapTileLayer)tiledMap.getLayers().get("background");
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, game.getScaleWithPPM(1f));
        //load map object
        createMapObject();
    }

    public void createMapObject(){
        //create map object
        world = new World(new Vector2(0,-10),true);
//        box2DDebugRenderer = new Box2DDebugRenderer(true,
//                /*drawJoints*/         true,
//                /*drawAABBs*/          false,
//                /*drawInactiveBodies*/ true,
//                /*drawVelocities*/     false,
//                /*drawContacts*/       true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.setDrawBodies(false);
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for(MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(game.getScaleWithPPM(rect.getX() + rect.getWidth() / 2), game.getScaleWithPPM(rect.getY() + rect.getHeight() / 2));
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(game.getScaleWithPPM(rect.getWidth() / 2), game.getScaleWithPPM(rect.getHeight() / 2));
            fixtureDef.shape = polygonShape;
            fixtureDef.restitution = 0.001f;
            body.createFixture(fixtureDef).setUserData(this);
        }
    }

    public TmxMapLoader getTmxMapLoader() {
        return tmxMapLoader;
    }

    public void setTmxMapLoader(TmxMapLoader tmxMapLoader) {
        this.tmxMapLoader = tmxMapLoader;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public OrthogonalTiledMapRenderer getOrthogonalTiledMapRenderer() {
        return orthogonalTiledMapRenderer;
    }

    public void setOrthogonalTiledMapRenderer(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer) {
        this.orthogonalTiledMapRenderer = orthogonalTiledMapRenderer;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    public void setBox2DDebugRenderer(Box2DDebugRenderer box2DDebugRenderer) {
        this.box2DDebugRenderer = box2DDebugRenderer;
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }
}
