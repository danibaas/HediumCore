package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.commands.Back;
import nl.shizleshizle.hediumcore.commands.Fly;
import nl.shizleshizle.hediumcore.commands.Frozen;
import nl.shizleshizle.hediumcore.commands.Ranks;

public class CommandMaster {

    public void register() {
        Back.register();
        Fly.register();
        Frozen.register();
        Ranks.register();
    }
}
