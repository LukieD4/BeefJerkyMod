package com.lukiedoggo.beefjerkymod.registry;

import com.lukiedoggo.beefjerkymod.BeefJerkyMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeefJerkyMod.MOD_ID);

    public static final RegistryObject<Item> COOKED_JERKY = ITEMS.register("cooked_flesh",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.6F)
                            .meat()
                            .build())));

    public static final RegistryObject<Item> CRISPY_JERKY = ITEMS.register("crispy_flesh",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.8F)
                            .meat()
                            .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}