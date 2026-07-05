package com.lukiedoggo.beefjerkymod;

import com.lukiedoggo.beefjerkymod.registry.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BeefJerkyMod.MOD_ID)
public class BeefJerkyMod {
    public static final String MOD_ID = "beefjerkymod";

    public BeefJerkyMod() {
        // Note: FMLJavaModLoadingContext.get() shows a [removal] warning in
        // some Forge builds but is still the correct, working API on
        // 1.20.1 (the constructor-injected IEventBus pattern only applies
        // starting in later Forge/NeoForge versions). Safe to leave as-is.
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}