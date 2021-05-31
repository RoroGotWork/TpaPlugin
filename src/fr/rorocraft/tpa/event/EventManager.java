package fr.rorocraft.tpa.event;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.rorocraft.tpa.Main;
import fr.rorocraft.tpa.task.RemovePlayersInFight;
import fr.rorocraft.tpa.utils.Infos;

public class EventManager implements Listener{
	
	private Main main;

	public EventManager(Main main) {
		this.main = main;
	}

	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;
		if(!(e.getEntity() instanceof Player)) return;
		Player damager = (Player) e.getDamager();
		Player  p = (Player) e.getEntity();
		
		if(Infos.playersInFight.contains(p)) {
			Infos.playersInFight.remove(p);
		    Infos.playersInFight.add(p);
		   
		} else {
			Infos.playersInFight.add(p);
		    p.sendMessage(ChatColor.RED + "Tu es en combat, tu ne pourra pas te téléporter à un joueur ou te déconnecter pendant 20 secondes");
		}
		
		
		if(Infos.playersInFight.contains(damager)) {
			Infos.playersInFight.remove(damager);
		    Infos.playersInFight.add(damager);  
		   
		} else {
			Infos.playersInFight.add(damager);  
		    damager.sendMessage(ChatColor.RED + "Tu es en combat, tu ne pourra pas te téléporter à un joueur pendant 20 secondes");
		}
		
		Bukkit.getScheduler().runTaskLater(main, new RemovePlayersInFight(p), 20 * 20);
		Bukkit.getScheduler().runTaskLater(main, new RemovePlayersInFight(damager), 20 * 20);
		
		}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p =e.getPlayer();
		
	 if(Infos.playersInFight.contains(p)) {
		 if(!p.isOp()) {
		 p.getInventory().clear();
		 Infos.players_to_clear.add(p.getUniqueId());
		 }
		 Infos.playersInFight.remove(p);
	 }
	 if(Infos.PLAYERS.containsKey(p)) {
		 Infos.PLAYERS.remove(p);
	 }
	 
	if(Infos.TPA_COMMAND.contains(p)) {
		Infos.TPA_COMMAND.remove(p);
	}
	
	if(Infos.TPAHERE_COMMAND.contains(p)) {
		Infos.TPAHERE_COMMAND.remove(p);
	}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p =e.getPlayer();
		if(Infos.players_to_clear.contains(p.getUniqueId())) {
			
	p.sendMessage(ChatColor.RED +" Ton inventaire à été clear car tu t'es déconnecté pendant un combat");
	Infos.players_to_clear.remove(p.getUniqueId());
		} else {
			
		}
	}
	
}
