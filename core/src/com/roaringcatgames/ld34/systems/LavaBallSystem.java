package com.roaringcatgames.ld34.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.roaringcatgames.ld34.components.LavaBallComponent;
import com.roaringcatgames.ld34.components.TransformComponent;
import com.roaringcatgames.ld34.components.VelocityComponent;

/**
 * Created by barry on 12/12/15 @ 12:00 PM.
 */
public class LavaBallSystem extends IteratingSystem {

    private float absMaxRotation = 75f;
    private float maxScale = 2f;
    private float rotationRate = 90f;
    private float scaleRate = 1f;

    private Array<Entity> lavaBalls;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<VelocityComponent> vm;



    public LavaBallSystem() {
        super(Family.all(LavaBallComponent.class, VelocityComponent.class, TransformComponent.class).get());
        tm = ComponentMapper.getFor(TransformComponent.class);
        vm = ComponentMapper.getFor(VelocityComponent.class);

        lavaBalls = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //lavaBalls.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //lavaBalls.add(entity);
        TransformComponent tc = tm.get(entity);
        VelocityComponent vc = vm.get(entity);

        float currentScale = tc.scale.x;
        float newScale;
        if(currentScale < 0){
            newScale = currentScale - (scaleRate*deltaTime);
            newScale = Math.max(-maxScale, newScale);

            tc.rotation += rotationRate*deltaTime;
            tc.rotation = Math.min(tc.rotation, absMaxRotation);
        }else{
            newScale = currentScale + (scaleRate*deltaTime);
            newScale = Math.min(maxScale, newScale);

            tc.rotation -= rotationRate*deltaTime;
            tc.rotation = Math.max(tc.rotation, -absMaxRotation);
        }
        tc.scale.set(newScale, newScale);

    }
}