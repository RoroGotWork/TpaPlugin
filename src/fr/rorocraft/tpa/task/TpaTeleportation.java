package fr.rorocraft.tpa.task;

import org.bukkit.Location;
import org.bukkit.entity.Player;


import fr.rorocraft.tpa.utils.Infos;

public class TpaTeleportation implements Runnable {

	private Player target;
	private Player p;
	public TpaTeleportation(Player p, Player target) {
		this.p = p;
		this.target = target;
	}
	@Override
	public void run() {
		if(!Infos.TPA_COMMAND.contains(p)) return;
		Location targetLoc = target.getLocation();
		p.teleport(new Location(target.getWorld(), targetLoc.getX(), targetLoc.getY(), targetLoc.getZ()));
		
		p.sendMessage("Téléportation vers " + target.getName() +" réussie");
		target.sendMessage(p.getName() + " vient de se téléporter vers vous");
		Infos.PLAYERS.remove(p);
		Infos.TPA_COMMAND.remove(p);

	}

}
