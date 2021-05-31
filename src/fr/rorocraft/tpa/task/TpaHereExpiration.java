package fr.rorocraft.tpa.task;

import org.bukkit.entity.Player;


import fr.rorocraft.tpa.utils.Infos;

public class TpaHereExpiration implements Runnable {
	private Player p;
	private Player target;

	public TpaHereExpiration(Player p, Player target) {
		this.p = p;
		this.target = target;
	}

	@Override
	public void run() {
		if(Infos.TPAHERE_COMMAND.contains(p)) {
			Infos.TPAHERE_COMMAND.remove(p);
		}
		
		if(Infos.PLAYERS.containsKey(p)) {
			Infos.PLAYERS.remove(p);
			p.sendMessage("Votre demande de t�l�portation � " + target.getName() + " a expir�");
			target.sendMessage("La demande de t�l�portation de " + p.getName() + " a expir�");
		}

	}

}
