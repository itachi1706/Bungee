package com.itachi1706.Bungee.BungeeJoin;

import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Kenneth on 17/7/2015.
 * for BungeeJoin in com.itachi1706.Bungee.BungeeJoin.
 */
public class BungPlayer {

    private ProxiedPlayer player;
    private ProxiedPlayer lastMsged;

    public BungPlayer(){}

    public BungPlayer(ProxiedPlayer p){
        player = p;
        lastMsged = null;
    }

    public ProxiedPlayer getPlayer(){
        return player;
    }

    public void setPlayer(ProxiedPlayer p){
        player = p;
    }

    public ProxiedPlayer getLastMsged(){
        return lastMsged;
    }

    public void setLastMsged(ProxiedPlayer p){
        lastMsged = p;
    }
}
