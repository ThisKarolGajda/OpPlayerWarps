package me.opkarol.opplayerwarps.commands;

import me.opkarol.opc.api.commands.OpCommand;
import me.opkarol.opc.api.commands.arguments.StringArg;
import me.opkarol.opc.api.database.manager.IDefaultDatabase;
import me.opkarol.opc.api.files.SimpleTranslation;
import me.opkarol.opc.api.location.OpSerializableLocation;
import me.opkarol.opplayerwarps.OpPlayerWarps;
import me.opkarol.opplayerwarps.utils.TeleportUtil;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;

import static me.opkarol.opc.api.plugin.OpMessagesPlugin.getMappedMessage;

public class WarpCommand {

    public WarpCommand() {
        IDefaultDatabase<OpPlayerWarp, String> database = OpPlayerWarps.getDatabaseInterface();
        TeleportUtil teleportUtil = TeleportUtil.getInstance(TeleportUtil.class);
        assert teleportUtil != null;
        new OpCommand("opwarp")
                .setGlobalArgSuggestion(new StringArg<>(1), database.getSuggestions(OpPlayerWarp::getWarpName))
                .addCommandWithoutGlobals(new OpCommand("create")
                        .addArgSuggestion(new StringArg<>(1), "<WARP_NAME>")
                        .executeAsPlayer((sender, args) -> args.useString(1, warpName -> database.addAndUse(sender.getUUID(), warpName,
                                new OpPlayerWarp(sender.getPlayer(), new OpSerializableLocation(sender.getPlayer().getLocation()), warpName),
                                getMappedMessage(sender, "warp.alreadyExists", SimpleTranslation.as("%warp%", warpName)),
                                getMappedMessage(sender, "warp.addedWarp", SimpleTranslation.as("%warp%", warpName))))))
                .addCommand(new OpCommand("delete")
                        .executeAsPlayer((sender, args) -> args.useString(1, warpName -> database.deleteAndUse(sender.getUUID(), warpName,
                                getMappedMessage(sender, "warp.noWarp", SimpleTranslation.as("%warp%", warpName)),
                                getMappedMessage(sender, "warp.removedWarp", SimpleTranslation.as("%warp%", warpName))))))
                .addCommand(new OpCommand("teleport")
                        .executeAsPlayer((sender, args) -> args.useString(1, warpName -> database.checkAndUse(sender.getUUID(), warpName,
                                getMappedMessage(sender, "warp.noWarp", SimpleTranslation.as("%warp%", warpName)),
                                (warp) -> teleportUtil.teleport(warp.getLocation(), sender.getPlayer())))))
                .register();


    }
}
