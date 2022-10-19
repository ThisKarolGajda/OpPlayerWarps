package me.opkarol.opplayerwarps.database;

import me.opkarol.opc.api.database.mysql.OpMConnection;
import me.opkarol.opc.api.database.mysql.base.OpMDatabaseAuto;
import me.opkarol.opc.api.database.mysql.base.OpMSingleDatabase;
import me.opkarol.opc.api.database.mysql.objects.OpMObjects;
import me.opkarol.opc.api.database.mysql.resultset.OpMResultSet;
import me.opkarol.opplayerwarps.OpPlayerWarps;
import me.opkarol.opplayerwarps.warps.OpPlayerWarp;
import me.opkarol.opplayerwarps.warps.OpPlayerWarpRating;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MySql3 extends OpMDatabaseAuto<OpPlayerWarp> {

    public MySql3() {
        super(new OpMSingleDatabase<>("table",
                new OpMConnection(OpPlayerWarps.getInstance().getConfiguration(), "mysql"),
                new OpMObjects<OpPlayerWarp>().addNotNullText("uuid", OpPlayerWarp::getOwnerUUID).addNotNullText("location", OpPlayerWarp::getStringLocation).addNotNullText("rating", OpPlayerWarp::getStringRating).addNotNullText("warpName", OpPlayerWarp::getWarpName).addNotNullText("name", OpPlayerWarp::getOwnerName).addPrimaryNotNullInt("id", OpPlayerWarp::getId)));
    }


    @Override
    public Function<OpMResultSet, OpPlayerWarp> getObjectAsResultSet() {
        return set -> new OpPlayerWarp(set.getUUID("uuid"), set.getText("name"), set.getLocation("location"), new OpPlayerWarpRating().fromString(set.getText("rating")), set.getText("warpName"), set.getInt("id"));
    }

    @Override
    public Function<OpPlayerWarp, Integer> getIdentification() {
        return OpPlayerWarp::getId;
    }

    @Override
    public BiConsumer<OpPlayerWarp, Integer> setIdentification() {
        return OpPlayerWarp::setId;
    }

    @Override
    public Function<OpPlayerWarp, UUID> getUUID() {
        return OpPlayerWarp::getOwnerUUID;
    }

    @Override
    public Predicate<OpPlayerWarp> getPredicate(Object object) {
        return warp -> warp.getWarpName().equals(object);
    }
}
