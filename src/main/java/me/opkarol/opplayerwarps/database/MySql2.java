package me.opkarol.opplayerwarps.database;

import me.opkarol.opc.api.database.mysql.OpMConnection;
import me.opkarol.opc.api.database.mysql.base.OpMySqlDatabase;
import me.opkarol.opc.api.database.mysql.objects.OpMObject;
import me.opkarol.opc.api.database.mysql.objects.OpMObjects;
import me.opkarol.opc.api.database.mysql.types.MySqlAttribute;
import me.opkarol.opc.api.database.mysql.types.MySqlVariableType;
import me.opkarol.opc.api.map.OpMap;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MySql2 implements IDatabase {
    private final OpMySqlDatabase<OpPlayerWarp> database;
    private final OpMap<UUID, List<OpPlayerWarp>> map = new OpMap<>();

    public MySql2() {
        OpMConnection connection = new OpMConnection();
        connection.setup("jdbc:mysql://localhost:3306/yo", "root", "");
        database = new OpMySqlDatabase<>("table", connection, new OpMObjects<>(
                new OpMObject<>("uuid", OpPlayerWarp::getOwnerUUID, MySqlVariableType.TEXT, MySqlAttribute.NOTNULL),
                new OpMObject<>("location", OpPlayerWarp::getStringLocation, MySqlVariableType.TEXT, MySqlAttribute.NOTNULL),
                new OpMObject<>("rating", OpPlayerWarp::getStringRating, MySqlVariableType.TEXT, MySqlAttribute.NOTNULL),
                new OpMObject<>("warpName", OpPlayerWarp::getWarpName, MySqlVariableType.TEXT, MySqlAttribute.NOTNULL),
                new OpMObject<>("id", OpPlayerWarp::getId, MySqlVariableType.INT, MySqlAttribute.PRIMARY),
                new OpMObject<>("name", OpPlayerWarp::getOwnerName, MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)));
        database.create();
    }

    private @NotNull List<OpPlayerWarp> addSafeWarp(@NotNull OpPlayerWarp warp) {
        List<OpPlayerWarp> warpList = map.getOrDefault(warp.getOwnerUUID(), new ArrayList<>());
        warpList.add(warp);
        return warpList;
    }

    public OpMySqlDatabase<OpPlayerWarp> getDatabase() {
        return database;
    }

    @Override
    public void addWarp(@NotNull OpPlayerWarp warp) {
        map.put(warp.getOwnerUUID(), addSafeWarp(warp));
        database.insert(warp);
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
        Optional<OpPlayerWarp> optional = getWarp(uuid, warp);
        if (optional.isPresent()) {
            OpPlayerWarp warp1 = optional.get();
            database.delete(warp1);
            List<OpPlayerWarp> warpList = map.getOrDefault(uuid, new ArrayList<>());
            warpList.removeIf(warp2 -> warp2.getWarpName().equals(warp));
            map.set(uuid, warpList);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getPlayerWarps(@NotNull UUID uuid) {
        return map.getOrDefault(uuid, new ArrayList<>())
                .stream().map(OpPlayerWarp::getWarpName)
                .collect(Collectors.toList());
    }
}
