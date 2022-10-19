package me.opkarol.opplayerwarps.warps;

import me.opkarol.opc.api.utils.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class OpRating implements Serializable {
    private final int rating;
    private final String message;

    public OpRating(int rating, String message) {
        this.rating = rating;
        this.message = message;
    }

    public OpRating(@NotNull String string) {
        String[] strings = string.split(";-;");
        if (strings.length == 2) {
            this.rating = StringUtil.getInt(strings[0]);
            this.message = strings[1];
        } else if (strings.length == 1) {
            this.rating = StringUtil.getInt(strings[0]);
            this.message = null;
        } else {
            this.rating = -1;
            this.message = null;
        }
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("%d;-;%s", rating, message);
    }
}
