package io.github.jonthesquirrel.tutorial.items;

import io.github.jonthesquirrel.tutorial.Tutorial;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(Tutorial.TAB));
    }

}
