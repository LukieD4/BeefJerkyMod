package com.lukiedoggo.beefjerkymod;

import com.lukiedoggo.beefjerkymod.registry.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BeefJerkyMod.MOD_ID)
public class BeefJerkyMod {
    public static final String MOD_ID = "beefjerkymod";

    public BeefJerkyMod() {
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
