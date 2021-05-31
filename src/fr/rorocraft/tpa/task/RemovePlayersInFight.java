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
	   if(!Infos.playersInFight.contains(p)) return;
		
	   Infos.playersInFight.remove(p);
	   p.sendMessage("Tu peux maintenant utiliser les commandes pour être tp");
		
	}

}
