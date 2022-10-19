package me.opkarol.opplayerwarps;

import me.opkarol.opc.api.files.ConfigurationMap;

public class Messages extends ConfigurationMap {
    private static Messages messages;

    public Messages() {
        super(OpPlayerWarps.getInstance(), "messages.yml");
        getConfiguration().updateConfig();
        messages = this;
    }

    public static Messages getInstance() {
        return messages == null ? new Messages() : messages;
    }
}
