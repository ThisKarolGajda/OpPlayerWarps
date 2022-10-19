package me.opkarol.opplayerwarps.database;

import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDatabase {
    void addWarp(OpPlayerWarp warp);

    Optional<OpPlayerWarp> getWarp(UUID uuid, String warp);

    default Optional<OpPlayerWarp> getWarp(@NotNull Player player, String warp) {
        return getWarp(player.getUniqueId(), warp);
    }

    boolean hasWarp(UUID uuid, String warp);

    boolean deleteWarp(UUID uuid, String warp);

    List<String> getPlayerWarps(@NotNull UUID uuid);

    default boolean hasWarp(@NotNull Player player, String warp) {
        return hasWarp(player.getUniqueId(), warp);
    }
}
