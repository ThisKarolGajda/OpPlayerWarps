package me.opkarol.opplayerwarps;

import me.opkarol.opc.api.plugin.OpPlugin;
import me.opkarol.opplayerwarps.commands.WarpCommand;
import me.opkarol.opplayerwarps.database.Database;

public final class OpPlayerWarps extends OpPlugin {

    @Override
    public void enable() {
        new Database();
        Messages.getInstance();
        new WarpCommand();

        //new OpTeleport(15).save("too");
    }

    @Override
    public void disable() {
        Database.getInstance().onDisable();
    }
}
