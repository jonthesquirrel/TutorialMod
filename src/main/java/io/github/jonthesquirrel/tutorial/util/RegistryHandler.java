package io.github.jonthesquirrel.tutorial.util;

import io.github.jonthesquirrel.tutorial.Tutorial;
import io.github.jonthesquirrel.tutorial.armor.ModArmorMaterial;
import io.github.jonthesquirrel.tutorial.blocks.BlockItemBase;
import io.github.jonthesquirrel.tutorial.blocks.RubyBlock;
import io.github.jonthesquirrel.tutorial.items.ItemBase;
import io.github.jonthesquirrel.tutorial.items.PoisonApple;
import io.github.jonthesquirrel.tutorial.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);
    public static final RegistryObject<PoisonApple> POISON_APPLE = ITEMS.register("poison_apple", PoisonApple::new);

    // Tools
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () ->
            new SwordItem(ModItemTier.RUBY, 2, -2.4f, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () ->
            new PickaxeItem(ModItemTier.RUBY, 0, -2.8f, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () ->
            new ShovelItem(ModItemTier.RUBY, 0.5f, -3.0f, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () ->
            new AxeItem(ModItemTier.RUBY, 5, -3.1f, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () ->
            new HoeItem(ModItemTier.RUBY, -3, -1.0f, new Item.Properties().group(Tutorial.TAB))
    );

    // Armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register(
            "ruby_helmet",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register(
            "ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register(
            "ruby_leggings",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(Tutorial.TAB))
    );
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register(
            "ruby_boots",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(Tutorial.TAB))
    );

    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);

    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () ->
            new BlockItemBase(RUBY_BLOCK.get())
    );

}
