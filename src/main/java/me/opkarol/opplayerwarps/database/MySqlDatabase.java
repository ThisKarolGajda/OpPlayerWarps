package me.opkarol.opplayerwarps.database;

import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MySqlDatabase implements IDatabase {
    @Override
    public void addWarp(OpPlayerWarp warp) {

    }

    @Override
    public Optional<OpPlayerWarp> getWarp(UUID uuid, String warp) {
        return Optional.empty();
    }

    @Override
    public boolean hasWarp(UUID uuid, String warp) {
        return false;
    }

    @Override
    public boolean deleteWarp(UUID uuid, String warp) {
        return false;
    }

    @Override
    public List<String> getPlayerWarps(@NotNull UUID uuid) {
        return null;
    }
    // private final MySqlDatabase database = new MySqlDatabase();
    // private final OpMap<UUID, List<OpPlayerWarp>> map = new OpMap<>();
    // private final MySqlTable table = new MySqlTable("OpPlayerWarps")
    //         .addMySqlVariable("uuid", MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)
    //         .addMySqlVariable("name", MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)
    //         .addMySqlVariable("location", MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)
    //         .addMySqlVariable("rating", MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)
    //         .addMySqlVariable("warpName", MySqlVariableType.TEXT, MySqlAttribute.NOTNULL)
    //         .addMySqlVariable("id", MySqlVariableType.INT, MySqlAttribute.NOTNULL, MySqlAttribute.PRIMARY);
//
    // public MySqlDatabase() {
    //     FileConfiguration config = OpPlayerWarps.getInstance().getConfig();
    //     database.setup(config.getString("mysql.jdbcUrl"), "mysql.username", "mysql.password");
    //     database.create(table);
//
    //     ResultSet set = database.get(table);
    //     try {
    //         while (set.next()) {
    //             UUID playerUUID = UUID.fromString(set.getString("uuid"));
    //             String name = set.getString("name");
    //             OpSerializableLocation location = new OpSerializableLocation(set.getString("location"));
    //             OpPlayerWarpRating rating = new OpPlayerWarpRating().fromString(set.getString("rating"));
    //             String warpName = set.getString("warpName");
    //             int id = set.getInt("id");
    //             map.set(playerUUID, addSafeWarp(new OpPlayerWarp(playerUUID, name, location, rating, warpName, id)));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
//
    // private @NotNull List<OpPlayerWarp> addSafeWarp(@NotNull OpPlayerWarp warp) {
    //     List<OpPlayerWarp> warpList = map.getOrDefault(warp.getOwnerUUID(), new ArrayList<>());
    //     warpList.add(warp);
    //     return warpList;
    // }
//
    // @Override
    // public void addWarp(OpPlayerWarp warp) {
    //     map.set(warp.getOwnerUUID(), addSafeWarp(warp));
    //     database.insert(new MySqlInsertTable(table)
    //             .addValue("uuid", warp.getOwnerUUID())
    //             .addValue("name", warp.getOwnerName())
    //             .addValue("location", warp.getLocation())
    //             .addValue("rating", warp.getRating().toString())
    //             .addValue("id", warp.getId(getPlayerWarpsCount(warp.getOwnerUUID()))));
    // }
//
    // @Override
    // public Optional<OpPlayerWarp> getWarp(UUID uuid, String warp) {
    //     return map.getOrDefault(uuid, new ArrayList<>())
    //             .stream().filter(warp1 -> warp1.getWarpName().equals(warp))
    //             .findAny();
    // }
//
    // @Override
    // public boolean deleteWarp(UUID uuid, String warp) {
    //     Optional<OpPlayerWarp> optional = getWarp(uuid, warp);
    //     if (optional.isPresent()) {
    //         OpPlayerWarp warp1 = optional.get();
    //         database.delete(new MySqlDeleteTable(table)
    //                 .addDeletion("uuid", warp1.getOwnerUUID())
    //                 .addDeletion("location", warp1.getLocation())
    //                 .addDeletion("warpName", warp1.getWarpName())
    //                 .addDeletion("id", warp1.getId()));
    //         List<OpPlayerWarp> warpList = map.getOrDefault(uuid, null);
    //         warpList.removeIf(warp2 -> warp2.getWarpName().equals(warp));
    //         map.set(uuid, warpList);
    //         return true;
    //     }
    //     return false;
    // }
//
    // @Override
    // public boolean hasWarp(UUID uuid, String warp) {
    //     return getWarp(uuid, warp).isPresent();
    // }
//
    // @Override
    // public List<String> getPlayerWarps(@NotNull UUID uuid) {
    //     return map.getOrDefault(uuid, null)
    //             .stream().map(OpPlayerWarp::getWarpName)
    //             .collect(Collectors.toList());
    // }
//
    // public int getPlayerWarpsCount(@NotNull UUID uuid) {
    //     return Math.toIntExact(map.keySet().stream().filter(uuid::equals).count());
    // }
}