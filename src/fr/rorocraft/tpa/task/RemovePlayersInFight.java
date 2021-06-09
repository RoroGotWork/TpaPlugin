package fr.rorocraft.tpa.task;


import org.bukkit.entity.Player;


import fr.rorocraft.tpa.utils.Infos;

public class RemovePlayersInFight implements Runnable{

	private Player p;
	public RemovePlayersInFight(Player p) {
		this.p = p;
	}
	@Override
	public void run() {
	   if(!Infos.PLAYERS_IN_FIGHT.contains(p)) return;
		
	   Infos.PLAYERS_IN_FIGHT.remove(p);
	   p.sendMessage("Tu peux maintenant utiliser les commandes pour être tp");
		
	}

}
