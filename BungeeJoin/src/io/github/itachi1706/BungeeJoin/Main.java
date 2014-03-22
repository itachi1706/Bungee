package io.github.itachi1706.BungeeJoin;

import java.util.Collection;
import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Main extends ConfigurablePlugin implements Listener {
	
	protected String loginString;
    protected String logoutString;
    protected String loginStringNorm;
    protected String logoutStringNorm;
    
    @Override
    public void onEnable()
    {
        this.loginString = this.getConfig().getString( "strings.login", "&5[BUNGEE-JOIN] &b[STAFF] %s &ejoined the network." );
        this.logoutString = this.getConfig().getString( "strings.logout", "&5[BUNGEE-JOIN] &b[STAFF] %s &eleft the network." );
        this.loginStringNorm = this.getConfig().getString( "strings.login", "&5[BUNGEE-JOIN] &e%s joined the network." );
        this.logoutStringNorm = this.getConfig().getString( "strings.logout", "&5[BUNGEE-JOIN] &e%s left the network." );
        
        this.getProxy().getPluginManager().registerListener( this, this );
    }
    
    @EventHandler
    public void onPostLoginEvent( PostLoginEvent e )
    {
    	String loginmsg = ChatColor.translateAlternateColorCodes('&', String.format(this.loginString, e.getPlayer().getDisplayName()));
    	String loginmsgNorm = ChatColor.translateAlternateColorCodes('&', String.format(this.loginStringNorm, e.getPlayer().getDisplayName()));
    	TextComponent com = new TextComponent(loginmsg);
    	TextComponent comNorm = new TextComponent(loginmsgNorm);
        Collection<ProxiedPlayer> players = this.getProxy().getPlayers();
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

}
