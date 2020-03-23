package com.xa.Maps;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContact implements ContactListener {

    Fixture fa;
    Fixture fb;

    @Override
    public void beginContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();

        if(fa == null || fb == null) return;
        if(fa.getUserData() == null || fb.getUserData() == null) return;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if(contact.getFixtureA() == fa || contact.getFixtureB() == fb){
//            System.out.println("Contact A: "+ contact.getFixtureA().getBody().getPosition().y);
//            System.out.println("Contact B: "+ contact.getFixtureB().getBody().getPosition().y);
//            System.out.println("Contact A: "+ contact.getFixtureA().getBody().getLinearVelocity().y);
//            System.out.println("Contact B: "+ contact.getFixtureB().getBody().getLinearVelocity().y);
            float faPositionY = contact.getFixtureA().getBody().getPosition().y;
            float fbPositionY = contact.getFixtureB().getBody().getPosition().y;
            if(fbPositionY-faPositionY <= 0.34 ){
                contact.setEnabled(false);
            }else {
                contact.setEnabled(true);
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
