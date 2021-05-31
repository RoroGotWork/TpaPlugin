package fr.rorocraft.tpa.task;

import org.bukkit.Location;
import org.bukkit.entity.Player;


import fr.rorocraft.tpa.utils.Infos;

public class TpaHereTeleportation implements Runnable {

	private Player target;
	private Player p;
	public TpaHereTeleportation(Player p, Player target) {
		this.p = p;
		this.target = target;
	}
	@Override
	public void run() {
		if(!Infos.TPAHERE_COMMAND.contains(p)) return;
		Location pLoc = p.getLocation();
		target.teleport(new Location(p.getWorld(), pLoc.getX(), pLoc.getY(), pLoc.getZ()));
		
		target.sendMessage("Téléportation vers " + p.getName() +" réussie");
		p.sendMessage(target.getName() + " vient de se téléporter vers vous");
		Infos.PLAYERS.remove(p);
		Infos.TPA_COMMAND.remove(p);	
		
	}

}
