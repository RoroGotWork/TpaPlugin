package fr.rorocraft.tpa.commands;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import fr.rorocraft.tpa.Main;
import fr.rorocraft.tpa.task.TpaExpiration;
import fr.rorocraft.tpa.utils.Infos;

public class Tpa implements CommandExecutor {

	
	private Main main;

	public Tpa(Main main) {
		this.main = main;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(Infos.PLAYERS_IN_FIGHT.contains(p)) {
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
		
		if(Infos.PLAYERS_IN_FIGHT.contains(target)) {
			p.sendMessage("Le joueur est en combat");
			return true;
		}
		
		
		
		p.sendMessage("Vous venez d'envoyé un demande à " + target.getName() + " pour se téléporter à lui");
		target.sendMessage(p.getName() + " vous a envoyé un demande pour se téléporter à vous");
		
		Infos.TPA_COMMAND.add(p);
		Infos.PLAYERS.put(p, target);
		
		if(main.getConfig().get("cooldown") != null) {
			
		Long cooldown = main.getConfig().getLong("cooldown");	
		Bukkit.getScheduler().runTaskLater(main, new TpaExpiration(p, target), 20 * cooldown );
		
		
		} else {
			
			Bukkit.getScheduler().runTaskLater(main, new TpaExpiration(p, target), 20 * 60 );
			
		}
		
		return false;
	}

}
