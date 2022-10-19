package me.opkarol.opplayerwarps.database;

import me.opkarol.opplayerwarps.OpPlayerWarps;
import org.bukkit.configuration.file.FileConfiguration;

public class Database {
    private final MySql3 database;
    private static Database instance;
    private FlatDatabase flatDatabase;

    public Database() {
        instance = this;
        FileConfiguration config = OpPlayerWarps.getInstance().getConfig();
        database = new MySql3();
        // if (config.getBoolean("mysql.enabled")) {
        //     database = new MySqlDatabase();
        // } else {
        //     flatDatabase = new FlatDatabase();
        //     database = flatDatabase;
        // }
    }

    public static Database getInstance() {
        return instance == null ? new Database() : instance;
    }

    public void onDisable() {
        if (flatDatabase != null) {
            flatDatabase.onDisable();
        }
    }

    public MySql3 getDatabase() {
        return database;
    }
}
