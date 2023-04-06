package me.opkarol.opplayerwarps;

import me.opkarol.opc.api.events.EventRegister;
import me.opkarol.opc.api.plugin.OpMessagesPlugin;
import me.opkarol.opplayerwarps.commands.WarpCommand;
import me.opkarol.opplayerwarps.inventories.Test;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.bukkit.event.player.PlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public final class OpPlayerWarps extends OpMessagesPlugin<OpPlayerWarp, String> {

    @Override
    public void enable() {
        saveInstance(new WarpCommand());

        EventRegister.registerEvent(PlayerChatEvent.class, event -> new Test().test(event.getPlayer()));
    }

    @Override
    public @NotNull String getFlatFileName() {
        return "database.yml";
    }

    @Override
    public @NotNull Function<OpPlayerWarp, String> getBaseFunction() {
        return OpPlayerWarp::getWarpName;
    }

    @Override
    public Class<? extends OpPlayerWarp> getClassInstance() {
        return OpPlayerWarp.class;
    }
}
