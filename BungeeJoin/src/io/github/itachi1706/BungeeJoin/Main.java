package io.github.itachi1706.BungeeJoin;

import java.util.ArrayList;
import java.util.Collection;

import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Main extends ConfigurablePlugin implements Listener {
	
	protected String loginString;
    protected String logoutString;
    protected String loginStringNorm;
    protected String logoutStringNorm;
    
    public static ArrayList<BungPlayer> playerList = new ArrayList<BungPlayer>();
    
    @Override
    public void onEnable()
    {
        this.loginString = this.getConfig().getString( "strings.login", "&5[BUNGEE-JOIN] &b[STAFF] %s &ejoined the network." );
        this.logoutString = this.getConfig().getString( "strings.logout", "&5[BUNGEE-LEAVE] &b[STAFF] %s &eleft the network." );
        this.loginStringNorm = this.getConfig().getString( "strings.login", "&5[BUNGEE-JOIN] &e%s joined the network." );
        this.logoutStringNorm = this.getConfig().getString( "strings.logout", "&5[BUNGEE-LEAVE] &e%s left the network." );
        
        this.getProxy().getPluginManager().registerListener( this, this );
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new GMessaging(this));
    }
    
    @EventHandler
    public void onPostLoginEvent( PostLoginEvent e )
    {
    	String loginmsg = ChatColor.translateAlternateColorCodes('&', String.format(this.loginString, e.getPlayer().getDisplayName()));
    	String loginmsgNorm = ChatColor.translateAlternateColorCodes('&', String.format(this.loginStringNorm, e.getPlayer().getDisplayName()));
    	TextComponent com = new TextComponent(loginmsg);
    	TextComponent comNorm = new TextComponent(loginmsgNorm);
        Collection<ProxiedPlayer> players = this.getProxy().getPlayers();
        playerList.add(new BungPlayer(e.getPlayer()));
        for (ProxiedPlayer p : players){
        	if (p.hasPermission("bungeejoin.staff")){
        		if (e.getPlayer().hasPermission("bungeejoin.staff")){
        			p.sendMessage(com);
        		} else {
        			p.sendMessage(comNorm);
        		}
        	}
        }
        
        
    }
    
    @EventHandler
    public void onPlayerDisconnectEvent( PlayerDisconnectEvent e )
    {
    	String quitMsg = ChatColor.translateAlternateColorCodes('&', String.format(this.logoutString, e.getPlayer().getDisplayName()));
    	String quitMsgNorm = ChatColor.translateAlternateColorCodes('&', String.format(this.logoutStringNorm, e.getPlayer().getDisplayName()));
    	TextComponent com = new TextComponent(quitMsg);
    	TextComponent comNorm = new TextComponent(quitMsgNorm);
        Collection<ProxiedPlayer> players = this.getProxy().getPlayers();
        for (int i = 0; i < playerList.size(); i++){
        	BungPlayer ple = playerList.get(i);
        	if (ple.getPlayer() == e.getPlayer()){
        		playerList.remove(i);
        	}
        }
        for (ProxiedPlayer p : players){
        	if (p.hasPermission("bungeejoin.staff")){
        		if (e.getPlayer().hasPermission("bungeejoin.staff")){
        			p.sendMessage(com);
        		} else {
        			p.sendMessage(comNorm);
        		}
        	}
        }
    }
    
    @EventHandler
    public void onPlayerServerChange(ServerSwitchEvent e){
    	ProxiedPlayer p = e.getPlayer();
    	Server s = p.getServer();
    	String msg = "&6[BUNGEE-SWITCH] &a" + p.getDisplayName() + " &ejoined &2" + s.getInfo().getName();
    	TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
    	Collection<ProxiedPlayer> players = this.getProxy().getPlayers();
        for (ProxiedPlayer pla : players){
        	if (pla.hasPermission("bungeejoin.staff")){
        		pla.sendMessage(com);
        	}
        }
    }

}
