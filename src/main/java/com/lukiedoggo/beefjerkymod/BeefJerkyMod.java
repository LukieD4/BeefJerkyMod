package com.lukiedoggo.beefjerkymod;

import com.lukiedoggo.beefjerkymod.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BeefJerkyMod.MOD_ID)
public class BeefJerkyMod {
    public static final String MOD_ID = "beefjerkymod";

    public BeefJerkyMod() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModItems.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.COOKED_JERKY);
            event.accept(ModItems.CRISPY_JERKY);
        }
    }
}