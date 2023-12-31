package com.mojang.blaze3d.audio;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

@OnlyIn(Dist.CLIENT)
public class Listener {
   private float gain = 1.0F;
   private Vec3 position = Vec3.ZERO;

   public void setListenerPosition(Vec3 pPosition) {
      this.position = pPosition;
      AL10.alListener3f(4100, (float)pPosition.x, (float)pPosition.y, (float)pPosition.z);
   }

   public Vec3 getListenerPosition() {
      return this.position;
   }

   public void setListenerOrientation(Vector3f pClientViewVector, Vector3f pViewVectorRaised) {
      AL10.alListenerfv(4111, new float[]{pClientViewVector.x(), pClientViewVector.y(), pClientViewVector.z(), pViewVectorRaised.x(), pViewVectorRaised.y(), pViewVectorRaised.z()});
   }

   public void setGain(float pGain) {
      AL10.alListenerf(4106, pGain);
      this.gain = pGain;
   }

   public float getGain() {
      return this.gain;
   }

   public void reset() {
      this.setListenerPosition(Vec3.ZERO);
      this.setListenerOrientation(new Vector3f(0.0F, 0.0F, -1.0F), new Vector3f(0.0F, 1.0F, 0.0F));
   }
}