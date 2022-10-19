package me.opkarol.opplayerwarps.commands;

import me.opkarol.opc.api.commands.OpCommand;
import me.opkarol.opc.api.commands.arguments.StringArg;
import me.opkarol.opc.api.commands.suggestions.OpCommandSuggestion;
import me.opkarol.opc.api.files.TranslationObject;
import me.opkarol.opc.api.location.OpSerializableLocation;
import me.opkarol.opplayerwarps.Messages;
import me.opkarol.opplayerwarps.database.Database;
import me.opkarol.opplayerwarps.database.MySql3;
import me.opkarol.opplayerwarps.utils.TeleportUtil;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class WarpCommand {

    public WarpCommand() {
        Messages messages = Messages.getInstance();
        MySql3 database = Database.getInstance().getDatabase();
        new OpCommand("opwarp")
                .addCommand(new OpCommand("create")
                        .addArgSuggestion(new StringArg("warp-name"), "<WARP_NAME>")
                        .executeAsPlayer((sender, args) -> {
                            String warpName = (String) args.get("warp-name");
                            if (database.contains(sender.getUUID(), warpName)) {
                                sender.sendMessage(messages.getValue("warp.alreadyExists"));
                            } else {
                                Player player = sender.getPlayer();
                                database.add(new OpPlayerWarp(player, new OpSerializableLocation(player.getLocation()), warpName));
                                sender.sendMessage(messages.getValue("warp.addedWarp", new TranslationObject("%warp%", warpName)));
                            }
                        }))
                .addCommand(new OpCommand("delete")
                        .addArgSuggestion(new StringArg("warp-name"), getSuggestion())
                        .executeAsPlayer((sender, args) -> {
                            String warpName = (String) args.get("warp-name");
                            if (database.delete(sender.getUUID(), warpName)) {
                                sender.sendMessage(messages.getValue("warp.removedWarp", new TranslationObject("%warp%", warpName)));
                            } else {
                                sender.sendMessage(messages.getValue("warp.noWarp", new TranslationObject("%warp%", warpName)));
                            }
                        }))
                .addCommand(new OpCommand("teleport")
                        .addArgSuggestion(new StringArg("warp-name"), getSuggestion())
                        .executeAsPlayer((sender, args) -> {
                            String warpName = (String) args.get("warp-name");
                            if (database.contains(sender.getUUID(), warpName)) {
                                Optional<OpPlayerWarp> optional = database.get(sender.getUUID(), warpName);
                                if (optional.isEmpty()) {
                                    sender.sendMessage(messages.getValue("warp.noWarp", new TranslationObject("%warp%", warpName)));
                                } else {
                                    OpPlayerWarp warp = optional.get();
                                    TeleportUtil.getInstance().teleport(warp.getLocation(), sender.getPlayer());
                                }
                            } else {
                                sender.sendMessage(messages.getValue("warp.noWarp", new TranslationObject("%warp%", warpName)));
                            }
                        }))
                .register();
    }

    public OpCommandSuggestion getSuggestion() {
        return new OpCommandSuggestion((sender, args) -> {
            if (sender.isPlayer()) {
                UUID uuid = sender.getPlayer().getUniqueId();
                return Database.getInstance().getDatabase()
                        .getList(uuid)
                        .stream().map(OpPlayerWarp::getWarpName)
                        .toList();
            }
            return Collections.singletonList("");
        });
    }
}
