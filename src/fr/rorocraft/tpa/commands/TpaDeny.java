package fr.rorocraft.tpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.tpa.utils.Infos;

public class TpaDeny implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player p = (Player) sender;
		
		if(args.length == 0 ) {
			p.sendMessage("Faites /tpadeny <nom du joueur>");
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		
		if(target == null) {
			p.sendMessage("Le joueur ne s'est jamais connecté sur ce serveur");
			return true;
		}
		
		if(Infos.PLAYERS.get(target) != p) {
			p.sendMessage("Le joueur ne vous a pas envoyé d'invitations ou son invitation a expiré");
			return true;
		}
		
		p.sendMessage("Vous avez refusé l'invitation de " + target.getName());
		target.sendMessage(p.getName() + " a refusé votre invitation");
		Infos.PLAYERS.remove(target);
		
		
		if(Infos.TPA_COMMAND.contains(target)) {
			Infos.TPA_COMMAND.remove(target);
		}
		
		if(Infos.TPAHERE_COMMAND.contains(target)) {
			Infos.TPAHERE_COMMAND.remove(target);
		}
		
		
		
		return false;
	}

	

}
