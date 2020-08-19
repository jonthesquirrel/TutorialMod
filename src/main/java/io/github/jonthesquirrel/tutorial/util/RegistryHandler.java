package io.github.jonthesquirrel.tutorial.util;

import io.github.jonthesquirrel.tutorial.Tutorial;
import io.github.jonthesquirrel.tutorial.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Tutorial.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

}
