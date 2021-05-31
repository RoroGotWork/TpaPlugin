package fr.rorocraft.tpa;

import org.bukkit.plugin.java.JavaPlugin;

import fr.rorocraft.tpa.commands.Tpa;
import fr.rorocraft.tpa.commands.TpaAccept;
import fr.rorocraft.tpa.commands.TpaDeny;
import fr.rorocraft.tpa.commands.TpaHere;
import fr.rorocraft.tpa.event.EventManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("[TpaPlugin] Le plugin est pret");
		
		this.getCommand("tpa").setExecutor(new Tpa(this));
		
		this.getCommand("tpahere").setExecutor(new TpaHere(this));
		
		this.getCommand("tpaaccept").setExecutor(new TpaAccept(this));
		
		this.getCommand("tpadeny").setExecutor(new TpaDeny());
		
		this.getServer().getPluginManager().registerEvents(new EventManager(this), this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

}
