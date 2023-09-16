package uk.rythefirst.hcgames;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import uk.rythefirst.hcgames.cache.Chests;
import uk.rythefirst.hcgames.cache.PlayerCache;
import uk.rythefirst.hcgames.cache.SpawnCache;
import uk.rythefirst.hcgames.commands.Manage;
import uk.rythefirst.hcgames.commands.Setup;
import uk.rythefirst.hcgames.commands.Spawnpoint;
import uk.rythefirst.hcgames.commands.Team;
import uk.rythefirst.hcgames.commands.Teleport;
import uk.rythefirst.hcgames.listeners.BlockBreak;
import uk.rythefirst.hcgames.listeners.CloseInv;
import uk.rythefirst.hcgames.listeners.EntityTarget;
import uk.rythefirst.hcgames.listeners.HungerEvent;
import uk.rythefirst.hcgames.listeners.InvClick;
import uk.rythefirst.hcgames.listeners.MobSpawn;
import uk.rythefirst.hcgames.listeners.MobSpawnChange;
import uk.rythefirst.hcgames.listeners.PlayerChat;
import uk.rythefirst.hcgames.listeners.PlayerDamage;
import uk.rythefirst.hcgames.listeners.PlayerDamagePlayer;
import uk.rythefirst.hcgames.listeners.PlayerDeath;
import uk.rythefirst.hcgames.listeners.PlayerEliminated;
import uk.rythefirst.hcgames.listeners.PlayerJoin;
import uk.rythefirst.hcgames.listeners.PlayerLogin;
import uk.rythefirst.hcgames.listeners.PlayerMove;
import uk.rythefirst.hcgames.listeners.PlayerPortal;
import uk.rythefirst.hcgames.listeners.PlayerQuit;
import uk.rythefirst.hcgames.listeners.PlayerRegistered;
import uk.rythefirst.hcgames.listeners.TeamWiped;
import uk.rythefirst.hcgames.managers.ChestManager;
import uk.rythefirst.hcgames.managers.PlayerManager;
import uk.rythefirst.hcgames.managers.SpawnManager;
import uk.rythefirst.hcgames.managers.TeamManager;
import uk.rythefirst.hcgames.timers.GameTimer;
import uk.rythefirst.hcgames.timers.QuitPlayers;
import uk.rythefirst.hcgames.timers.StartCountdown;
import uk.rythefirst.hcgames.tools.PlayerTools;

public class Main extends JavaPlugin {

	public static Scoreboard scoreboard;
	public static Settings settings;
	public static Game game;
	public static PlayerManager pm;
	public static TeamManager tm;
	public static Plugin instance;
	public static StartCountdown sCountdown;
	public static GameTimer bTimer;
	public static ChestManager cm;
	public static Chests ChestCache;
	public static PlayerCache playerCache;
	public TabList tblst;
	public static SpawnCache spawnCache;
	public static SpawnManager spawnManager;
	public static QuitPlayers quitTimer;

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		// Creating instances of classes -
		instance = this;
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		settings = new Settings();
		settings.setPvp(false);
		game = new Game();
		pm = new PlayerManager();
		tblst = new TabList();
		tm = new TeamManager();
		sCountdown = new StartCountdown();
		bTimer = new GameTimer();
		ChestCache = new Chests();
		cm = new ChestManager();
		cm.loadChests();
		playerCache = new PlayerCache();
		spawnCache = new SpawnCache();
		spawnManager = new SpawnManager();
		quitTimer = new QuitPlayers();
		spawnManager.loadPoints();
		settings.setStreamLink("twitch.tv/nihachu");
		tm.loadTeams();
		pm.loadPendingTeams();

		tblst.custom();

		Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
		Bukkit.getPluginManager().registerEvents(new HungerEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamagePlayer(), this);
		Bukkit.getPluginManager().registerEvents(new MobSpawn(), this);
		Bukkit.getPluginManager().registerEvents(new InvClick(), this);
		Bukkit.getPluginManager().registerEvents(new CloseInv(), this);
		Bukkit.getPluginManager().registerEvents(new MobSpawnChange(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerRegistered(), this);
		Bukkit.getPluginManager().registerEvents(new EntityTarget(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerEliminated(), this);
		Bukkit.getPluginManager().registerEvents(new TeamWiped(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPortal(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);

		this.getCommand("manage").setExecutor(new Manage());
		this.getCommand("teams").setExecutor(new Team());
		this.getCommand("tp").setExecutor(new Teleport());
		this.getCommand("setup").setExecutor(new Setup());
		this.getCommand("spawn").setExecutor(new Spawnpoint());

		for (World world : Bukkit.getServer().getWorlds()) {
			world.setGameRuleValue("doMobSpawning", "false");
		}

		if (!(Bukkit.getOnlinePlayers().size() == 0)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				pm.registerPlayer(player);
			}
		}

		Bukkit.getWorld("world").getWorldBorder().setCenter(0, 0);
		Bukkit.getWorld("world").getWorldBorder().setSize(50);
		Bukkit.getLogger().log(Level.INFO, "World Border Set!");

		PlayerTools.hidePlayers();
	}

	@Override
	public void onDisable() {
		cm.saveChests();
		tm.saveTeams();
		pm.savePendingTeams();
		// tm.saveTeams();
		spawnManager.savePoints();
		settings = null;
		game = null;
		pm = null;
	}

}
