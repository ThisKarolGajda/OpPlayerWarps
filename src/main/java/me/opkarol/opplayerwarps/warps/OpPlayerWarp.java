package me.opkarol.opplayerwarps.warps;

import me.opkarol.opc.api.database.mysql.reflection.symbols.Constructor;
import me.opkarol.opc.api.database.mysql.reflection.symbols.Identification;
import me.opkarol.opc.api.database.mysql.reflection.symbols.Table;
import me.opkarol.opc.api.database.mysql.reflection.symbols.Value;
import me.opkarol.opc.api.database.mysql.reflection.types.OpMObjectValues;
import me.opkarol.opc.api.location.OpSerializableLocation;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "warps")
public class OpPlayerWarp implements Serializable {
    @Value(value = OpMObjectValues.UUID_OBJECT, parameter = 0)
    private final UUID ownerUUID;
    @Value(parameter = 1)
    private final String ownerName;
    @Value(parameter = 2)
    private final String location;
    @Value(parameter = 3)
    private final String stringRating;
    @Value(value = OpMObjectValues.COMPARABLE_OBJECT, parameter = 4)
    private final String warpName;
    @Value(value = OpMObjectValues.IDENTIFICATION_OBJECT, parameter = 5)
    private int id;
    private final OpPlayerWarpRating rating;

    public OpPlayerWarp(@NotNull OfflinePlayer player, @NotNull OpSerializableLocation location, String warpName) {
        this.ownerUUID = player.getUniqueId();
        this.ownerName = player.getName();
        this.location = location.toString();
        this.rating = new OpPlayerWarpRating();
        this.stringRating = this.rating.toString();
        this.warpName = warpName;
        this.id = -1;
    }

    @Constructor
    public OpPlayerWarp(UUID ownerUUID, String ownerName, @NotNull String location, String rating, String warpName, Integer id) {
        this.ownerUUID = ownerUUID;
        this.ownerName = ownerName;
        this.location = location;
        this.rating = new OpPlayerWarpRating().fromString(rating);
        this.stringRating = rating;
        this.warpName = warpName;
        this.id = id;
    }

    public OpPlayerWarp() {
        this.ownerUUID = null;
        this.ownerName = null;
        this.location = null;
        this.stringRating = null;
        this.warpName = null;
        this.rating = null;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public OpSerializableLocation getLocation() {
        return new OpSerializableLocation(location);
    }

    public String getStringLocation() {
        return location;
    }

    public String getStringRating() {
        return stringRating;
    }

    public OpPlayerWarpRating getRating() {
        return rating;
    }

    public String getWarpName() {
        return warpName;
    }

    public int getId() {
        return id;
    }

    @Identification
    public void setId(Integer id) {
        this.id = id;
    }
}
