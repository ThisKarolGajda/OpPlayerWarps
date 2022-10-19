package me.opkarol.opplayerwarps.warps;

import me.opkarol.opc.api.map.OpMap;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class OpPlayerWarpRating implements Serializable {
    private final OpMap<UUID, OpRating> ratings = new OpMap<>();

    public void addRating(UUID uuid, int rating) {
        addRating(uuid, new OpRating(rating, null));
    }

    public void addRating(UUID uuid, int rating, String message) {
        addRating(uuid, new OpRating(rating, message));
    }

    public void addRating(UUID uuid, OpRating rating) {
        ratings.set(uuid, rating);
    }

    public void removeRating(UUID uuid) {
        ratings.remove(uuid);
    }

    public String getUUIDString(UUID uuid) {
        OpRating rating = ratings.getOrDefault(uuid, null);
        if (rating == null) {
            return null;
        }
        return String.format("%s:-:%s", uuid.toString(), rating);
    }

    public OpPlayerWarpRating fromString(@NotNull String s) {
        String[] ratings = s.split("-=-");
        for (String rating : ratings) {
            String[] strings = rating.split(":-:");
            if (strings.length == 2) {
                UUID uuid = UUID.fromString(strings[0]);
                OpRating rating1 = new OpRating(strings[1]);
                addRating(uuid, rating1);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (ratings.keySet().size() > 0) {
            for (UUID uuid : ratings.keySet()) {
                builder.append(getUUIDString(uuid)).append("-=-");
            }
            return builder.substring(0, builder.length() - 3);
        }
        return "";
    }
}
