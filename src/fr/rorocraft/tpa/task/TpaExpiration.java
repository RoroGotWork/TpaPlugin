package fr.rorocraft.tpa.task;

import org.bukkit.entity.Player;

import fr.rorocraft.tpa.utils.Infos;

public class TpaExpiration implements Runnable{
	 private Player p;
	 private Player target;

	public TpaExpiration(Player p, Player target) {
		this.p = p;
		this.target = target;
	}

	@Override
	public void run() {
		if(Infos.PLAYERS.containsKey(p)) {
		Infos.PLAYERS.remove(p);
		}
		
		if(Infos.TPA_COMMAND.contains(p)) {
		Infos.TPA_COMMAND.remove(p);
		p.sendMessage("Votre demande de t�l�portation pour " + target.getName() +" a expir�");
		target.sendMessage("La demande de t�l�portation de " + p.getName() + " a expir�");
		}
		
		
	}

}
