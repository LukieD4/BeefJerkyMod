// src/main/java/com/lukiedoggo/beefjerkymod/event/ZombieBurnDropHandler.java
package com.lukiedoggo.beefjerkymod.event;

import com.lukiedoggo.beefjerkymod.BeefJerkyMod;
import com.lukiedoggo.beefjerkymod.registry.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

/**
 * Handles replacing a zombie's vanilla rotten flesh drop with our cooked
 * jerky whenever the zombie died while burning, in any form (fire, lava,
 * fire tick, etc).
 *
 * Registered on the main Forge event bus (NOT the mod event bus).
 */
@Mod.EventBusSubscriber(modid = BeefJerkyMod.MOD_ID)
public class ZombieBurnDropHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (!(entity instanceof Zombie)) {
            return;
        }

        if (!diedInFire(entity, event.getSource())) {
            return;
        }

        // Remove any vanilla rotten flesh from the drop list so it doesn't
        // drop alongside our jerky.
        Iterator<ItemEntity> iterator = event.getDrops().iterator();
        while (iterator.hasNext()) {
            ItemEntity itemEntity = iterator.next();
            if (itemEntity.getItem().is(net.minecraft.world.item.Items.ROTTEN_FLESH)) {
                iterator.remove();
            }
        }

        int count = 1 + entity.getRandom().nextInt(2); // 1 or 2
        ItemEntity jerkyDrop = new ItemEntity(
                entity.level,
                entity.getX(), entity.getY(), entity.getZ(),
                new ItemStack(ModItems.COOKED_JERKY.get(), count)
        );
        jerkyDrop.setDefaultPickUpDelay();
        event.getDrops().add(jerkyDrop);
    }

    /**
     * True if the zombie's death is attributable to fire in any form:
     * direct fire, lava, or was simply on fire / had fire ticks remaining
     * at time of death (covers burning-then-killed-by-something-else edge
     * cases).
     */
    private static boolean diedInFire(LivingEntity entity, DamageSource source) {
        boolean fireDamageSource = source != null && (
                source.isFire()
                        || source == DamageSource.ON_FIRE
                        || source == DamageSource.IN_FIRE
                        || source == DamageSource.LAVA
        );

        boolean wasOnFire = entity.isOnFire() || entity.getRemainingFireTicks() > 0;

        return fireDamageSource || wasOnFire;
    }
}