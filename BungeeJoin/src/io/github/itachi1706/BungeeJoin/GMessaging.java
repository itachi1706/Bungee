package io.github.itachi1706.BungeeJoin;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GMessaging extends Command {
	
	public GMessaging(Main This) {
        super("gmsg", "bungeejoin.global.message");
    }
	
	public void execute(CommandSender sender, String[] args) {
        if (args.length < 2){
        	String errormsg = "&cUsage: /gmsg <player> <message>";
        	TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', errormsg));
        	sender.sendMessage(com);
        } else {
        	if (!(sender.hasPermission("bungeejoin.global.message"))){
        		String errormsg = "&4You do not have permission to use this command";
            	TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', errormsg));
            	sender.sendMessage(com);
        	} else {
        		ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
        		if (target == null){
        			String errormsg = "&c" + args[0] + " is not online!";
                	TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', errormsg));
                	sender.sendMessage(com);
        		} else {
	        		StringBuilder builder = new StringBuilder();
	        		for (int i = 1; i < args.length; i++){
	        			builder.append(args[i] + " ");
	        		}
	        		
	        		if (sender instanceof ProxiedPlayer){
	        			//Sender is player
	        			if (((ProxiedPlayer) sender).getServer() == target.getServer()){
	        				String msgToSend = "&5[&6" + sender.getName() + " &r->&6 " + target.getName() + "&5]&r " + builder.toString();
	        				TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', msgToSend));
	        				sender.sendMessage(com);
	        				target.sendMessage(com);
	        				for (int i = 0; i < Main.playerList.size(); i++){
	        					BungPlayer bP = Main.playerList.get(i);
	        					if (bP.getPlayer().equals(target)){
	        						bP.setLastMsged((ProxiedPlayer)sender);
	        					}
	        					if (bP.getPlayer().equals((ProxiedPlayer)sender)){
	        						bP.setLastMsged(target);
	        					}
	        				}
	        			} else {
	        				String msgToSend = "&5[&6" + sender.getName() + " &b(" + ((ProxiedPlayer) sender).getServer().getInfo().getName() + ")&r -> &6" + target.getName() + " &b(" + target.getServer().getInfo().getName() + ")&5]&r " + builder.toString();
	        				TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', msgToSend));
	        				sender.sendMessage(com);
	        				target.sendMessage(com);
	        				for (int i = 0; i < Main.playerList.size(); i++){
	        					BungPlayer bP = Main.playerList.get(i);
	        					if (bP.getPlayer().equals(target)){
	        						bP.setLastMsged((ProxiedPlayer)sender);
	        					}
	        					if (bP.getPlayer().equals((ProxiedPlayer)sender)){
	        						bP.setLastMsged(target);
	        					}
	        				}
	        			}
	        			
	        		} else {
	        			//Sender is console
	        			String msgToSend = "&5[&6" + sender.getName() + " &r->&6 " + target.getName() + "&5]&r " + builder.toString();
        				TextComponent com = new TextComponent(ChatColor.translateAlternateColorCodes('&', msgToSend));
        				sender.sendMessage(com);
        				target.sendMessage(com);
	        		}
	        		
        		}
        	}
        }
    }

}
