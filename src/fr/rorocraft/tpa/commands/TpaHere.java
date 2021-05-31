package fr.rorocraft.tpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.tpa.Main;
import fr.rorocraft.tpa.task.TpaHereExpiration;
import fr.rorocraft.tpa.utils.Infos;

public class TpaHere implements CommandExecutor {

	private Main main;

	public TpaHere(Main main) {
		this.main = main;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(Infos.playersInFight.contains(p)) {
			p.sendMessage("Vous êtes en combat");
			return true;
		} 
		
		if(Infos.TPA_COMMAND.contains(p) || Infos.TPAHERE_COMMAND.contains(p)) {
			p.sendMessage("Vous ne pouvez faire qu'une demande à la fois");
			return true;
		}
		
		if(args.length == 0) {
			p.sendMessage("Faites /tpa <Joueur>");
			return true;
		}
		
		String targetName = args[0];
		
		if(Bukkit.getPlayer(targetName) == null) {
			p.sendMessage("Le joueur ne s'est jamais connecté");
			return true;
		}
		Player target = Bukkit.getPlayer(targetName);
		
		if(Infos.playersInFight.contains(target)) {
			p.sendMessage("Le joueur est en combat");
			return true;
		}
		
		
		
		p.sendMessage("Vous venez d'envoyé un demande à " + target.getName() + " pour qu'il se téléporte à vous");
		target.sendMessage(p.getName() + " vous a envoyé un demande pour que vous vous téléportiez à lui");
		
		Infos.TPAHERE_COMMAND.add(p);
		Infos.PLAYERS.put(p, target);
		System.out.println(Infos.PLAYERS.toString());
		
		if(main.getConfig().get("cooldown") != null) {
			
		Long cooldown = main.getConfig().getLong("cooldown");	
		Bukkit.getScheduler().runTaskLater(main, new TpaHereExpiration(p, target), 20 * cooldown );
		
		} else {
			
			Bukkit.getScheduler().runTaskLater(main, new TpaHereExpiration(p, target), 20 * 60 );
			
		
		
	}
		return false;	}

}
