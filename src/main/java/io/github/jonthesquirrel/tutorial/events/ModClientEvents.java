package io.github.jonthesquirrel.tutorial.events;

import io.github.jonthesquirrel.tutorial.Tutorial;
import io.github.jonthesquirrel.tutorial.init.ModBlocks;
import io.github.jonthesquirrel.tutorial.init.ModItems;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Tutorial.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT
)
public class ModClientEvents {

    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.STICK) {
            Tutorial.LOGGER.info("Player jumped with a stick");
            World world = player.getEntityWorld();
            // player.func_233580_cy_() ---> player.getPosition()
            world.setBlockState(
                    player.getPosition().add(0, -1, 0),
                    ModBlocks.RUBY_BLOCK.get().getDefaultState()
            );
        }
    }

    @SubscribeEvent
    public static void onDamageSheep(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.POISON_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (target instanceof SheepEntity) {
                    PlayerEntity player = event.getPlayer();
                    target.addPotionEffect(new EffectInstance(
                            Effects.POISON,
                            10 * 10
                    ));
                    target.setGlowing(true);
                    if (!event.getPlayer().getEntityWorld().isRemote) {
                        String msg = TextFormatting.RED + "That sheep isn't feeling so well...";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onCraftingTableOpen(GuiOpenEvent event) {
        if (event.isCancelable()) {
            if (event.getGui() instanceof CraftingScreen) {
                event.setCanceled(true);
                Tutorial.LOGGER.info("Player tried to open a crafting table :P");
            }
        }
    }

}
