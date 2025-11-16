package net.lxshh.fleshz.compat.everycomp;

import net.lxshh.fleshz.FleshZ;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class WoodGoodIntegration {
    public static void init() {
        EveryCompatAPI.registerModule(new WoodGood(FleshZ.MOD_ID));
    }
}
