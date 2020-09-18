package io.github.jonthesquirrel.tutorial.items;

import io.github.jonthesquirrel.tutorial.Tutorial;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonApple extends Item {

    public PoisonApple() {
        super(new Item.Properties()
                .group(Tutorial.TAB)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(1.2f)
                        .effect(new EffectInstance(Effects.NAUSEA, 15 * 20, 0), 1.0f)
                        .effect(new EffectInstance(Effects.POISON, 15 * 20, 1), 1.0f)
                        .effect(new EffectInstance(Effects.HUNGER, 15 * 20, 0), 0.3f)
                        .setAlwaysEdible()
                        .build()
                )
        );
    }

}
