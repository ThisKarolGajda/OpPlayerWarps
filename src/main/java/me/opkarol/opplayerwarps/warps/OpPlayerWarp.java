package me.opkarol.opplayerwarps.warps;

import me.opkarol.opc.api.location.OpSerializableLocation;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class OpPlayerWarp implements Serializable {
    private final UUID ownerUUID;
    private final String ownerName;
    private final String location;
    private final OpPlayerWarpRating rating;
    private final String warpName;
    private int id;

    public OpPlayerWarp(@NotNull OfflinePlayer player, @NotNull OpSerializableLocation location, String warpName) {
        this.ownerUUID = player.getUniqueId();
        this.ownerName = player.getName();
        this.location = location.toString();
        this.rating = new OpPlayerWarpRating();
        this.warpName = warpName;
        this.id = -1;
    }

    public OpPlayerWarp(UUID ownerUUID, String ownerName, @NotNull OpSerializableLocation location, OpPlayerWarpRating rating, String warpName, int id) {
        this.ownerUUID = ownerUUID;
        this.ownerName = ownerName;
        this.location = location.toString();
        this.rating = rating;
        this.warpName = warpName;
        this.id = id;
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

    public OpPlayerWarpRating getRating() {
        return rating;
    }

    public String getStringRating() {
        return getRating().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OpPlayerWarp that = (OpPlayerWarp) o;

        if (!ownerUUID.equals(that.ownerUUID)) {
            return false;
        }
        if (!ownerName.equals(that.ownerName)) {
            return false;
        }
        return location.equals(that.location);
    }

    @Override
    public int hashCode() {
        int result = ownerUUID.hashCode();
        result = 31 * result + ownerName.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    public String getWarpName() {
        return warpName;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }
}
