package me.opkarol.opplayerwarps.database;

import me.opkarol.opc.api.map.OpMap;
import me.opkarol.opplayerwarps.OpPlayerWarps;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlatDatabase implements IDatabase {
    private OpMap<UUID, List<OpPlayerWarp>> map = new OpMap<>();
    private final me.opkarol.opc.api.database.flat.FlatDatabase<OpMap<UUID, List<OpPlayerWarp>>> flatDatabase = new me.opkarol.opc.api.database.flat.FlatDatabase<>(OpPlayerWarps.getInstance(), "flat-database.yml");

    public FlatDatabase() {
        OpMap<UUID, List<OpPlayerWarp>> map = flatDatabase.loadObject();
        if (map != null) {
            this.map = map;
        }
    }

    private @NotNull List<OpPlayerWarp> addSafeWarp(@NotNull OpPlayerWarp warp) {
        List<OpPlayerWarp> warpList = map.getOrDefault(warp.getOwnerUUID(), new ArrayList<>());
        warpList.add(warp);
        return warpList;
    }

    @Override
    public void addWarp(OpPlayerWarp warp) {
        map.set(warp.getOwnerUUID(), addSafeWarp(warp));
    }

    @Override
    public Optional<OpPlayerWarp> getWarp(UUID uuid, String warp) {
        return map.getOrDefault(uuid, new ArrayList<>())
                .stream().filter(warp1 -> warp1.getWarpName().equals(warp))
                .findAny();
    }

    @Override
    public boolean hasWarp(UUID uuid, String warp) {
        return getWarp(uuid, warp).isPresent();
    }

    @Override
    public boolean deleteWarp(UUID uuid, String warp) {
        List<OpPlayerWarp> warpList = map.getOrDefault(uuid, null);
        if (warpList == null) {
            return false;
        }
        return warpList.removeIf(warp1 -> warp1.getWarpName().equals(warp));
    }

    @Override
    public List<String> getPlayerWarps(@NotNull UUID uuid) {
        return map.getOrDefault(uuid, new ArrayList<>())
                .stream().map(OpPlayerWarp::getWarpName)
                .collect(Collectors.toList());
    }

    public void onDisable() {
        flatDatabase.saveObject(map);
    }
}