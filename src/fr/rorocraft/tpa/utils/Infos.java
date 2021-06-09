package fr.rorocraft.tpa.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.bukkit.entity.Player;

public class Infos {

	public static Map<Player, Player>  PLAYERS = new HashMap<>();

	public static List<Player> PLAYERS_IN_FIGHT = new ArrayList<>();
	public static List<Player> TPA_COMMAND = new ArrayList<>();
	public static List<Player> TPAHERE_COMMAND = new ArrayList<>();
	public static List<UUID> PLAYERS_TO_CLEAR = new ArrayList<>();
}
