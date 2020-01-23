package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.commands.Back;
import nl.shizleshizle.hediumcore.commands.Fly;
import nl.shizleshizle.hediumcore.commands.Frozen;

public class CommandMaster {
    private static CommandMaster instance;

    public static CommandMaster getInstance() {
        return instance;
    }

    public void register() {
        Back.register();
        Fly.register();
        Frozen.register();
    }
}
