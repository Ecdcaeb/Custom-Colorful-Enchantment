package net.minecraft.util.valueproviders;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Function;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class UniformFloat extends FloatProvider {
   public static final Codec<UniformFloat> CODEC = RecordCodecBuilder.<UniformFloat>create((p_146601_) -> {
      return p_146601_.group(Codec.FLOAT.fieldOf("min_inclusive").forGetter((p_146612_) -> {
         return p_146612_.minInclusive;
      }), Codec.FLOAT.fieldOf("max_exclusive").forGetter((p_146609_) -> {
         return p_146609_.maxExclusive;
      })).apply(p_146601_, UniformFloat::new);
   }).comapFlatMap((p_274956_) -> {
      return p_274956_.maxExclusive <= p_274956_.minInclusive ? DataResult.error(() -> {
         return "Max must be larger than min, min_inclusive: " + p_274956_.minInclusive + ", max_exclusive: " + p_274956_.maxExclusive;
      }) : DataResult.success(p_274956_);
   }, Function.identity());
   private final float minInclusive;
   private final float maxExclusive;

   private UniformFloat(float p_146595_, float p_146596_) {
      this.minInclusive = p_146595_;
      this.maxExclusive = p_146596_;
   }

   public static UniformFloat of(float pMinInclusive, float pMaxExclusive) {
      if (pMaxExclusive <= pMinInclusive) {
         throw new IllegalArgumentException("Max must exceed min");
      } else {
         return new UniformFloat(pMinInclusive, pMaxExclusive);
      }
   }

   public float sample(RandomSource pRandom) {
      return Mth.randomBetween(pRandom, this.minInclusive, this.maxExclusive);
   }

   public float getMinValue() {
      return this.minInclusive;
   }

   public float getMaxValue() {
      return this.maxExclusive;
   }

   public FloatProviderType<?> getType() {
      return FloatProviderType.UNIFORM;
   }

   public String toString() {
      return "[" + this.minInclusive + "-" + this.maxExclusive + "]";
   }
}