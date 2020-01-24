package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.commands.*;

public class CommandMaster {

    public void register() {
        Back.register();
        Fly.register();
        Frozen.register();
        Ranks.register();
        Warp.register();
        Wild.register();
    }
}
