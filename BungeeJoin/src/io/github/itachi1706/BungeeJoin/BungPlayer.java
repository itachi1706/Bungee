package io.github.itachi1706.BungeeJoin;

import net.md_5.bungee.api.connection.ProxiedPlayer;

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
