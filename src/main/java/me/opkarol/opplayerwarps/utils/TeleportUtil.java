package me.opkarol.opplayerwarps.utils;

import me.opkarol.opc.api.location.OpSerializableLocation;
import me.opkarol.opc.api.teleport.OpTeleport;
import me.opkarol.opc.api.teleport.TeleportRegistration;
import me.opkarol.opc.api.utils.VariableUtil;
import org.bukkit.entity.Player;

public class TeleportUtil {
    private static TeleportUtil teleportUtil;
    private final OpTeleport teleport = new OpTeleport("teleport").setRegistration(new TeleportRegistration());

    public TeleportUtil() {
        teleportUtil = this;
    }

    public OpTeleport getTeleport() {
        return teleport;
    }

    public static TeleportUtil getInstance() {
        return VariableUtil.getOrDefault(teleportUtil, new TeleportUtil());
    }

    public void teleport(OpSerializableLocation location, Player player) {
        teleport.copy().setLocation(location).teleport(player);
    }
}
