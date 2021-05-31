package fr.rorocraft.tpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.tpa.Main;
import fr.rorocraft.tpa.task.TpaHereTeleportation;
import fr.rorocraft.tpa.task.TpaTeleportation;
import fr.rorocraft.tpa.utils.Infos;

public class TpaAccept implements CommandExecutor {

	private Main main;

	public TpaAccept(Main main) {
		this.main = main;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command xmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(args.length == 0 ) {
			p.sendMessage("Faites /accept <Nom du joueur>");
			return true;
		}
		
		
		Player target = Bukkit.getPlayer(args[0]);
		
		if(target == null) {
			p.sendMessage("Le joueur ne s'est jamais connecté sur le serveur");
			return true;
		}
		
		 if( Infos.PLAYERS.get(target) != p)  {
			p.sendMessage("Le joueur ne vous a pas envoyé d'invitations ou son invitation a expiré");
			
			return true;
		} 
		
		if(Infos.TPA_COMMAND.contains(target)) {
			
			target.sendMessage("Téléportation dans 5 secondes");
			p.sendMessage("Téléportation dans 5 secondes");
		 	Bukkit.getScheduler().runTaskLater(main, new TpaTeleportation(target, p), 20 * 5);
		 	
		} else if (Infos.TPAHERE_COMMAND.contains(target)) {
			target.sendMessage("Téléportation dans 5 secondes");
			p.sendMessage("Téléportation dans 5 secondes");
		 	Bukkit.getScheduler().runTaskLater(main, new TpaHereTeleportation(target, p), 20 * 5);
		}
		
		
		
		
		return false;
	}

	
}
