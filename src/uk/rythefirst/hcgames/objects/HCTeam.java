package uk.rythefirst.hcgames.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.TeamWipeEvent;
import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.managers.PlayerManager;
import uk.rythefirst.hcgames.result.TeamMemberChangeResult;
import uk.rythefirst.hcgames.tools.Visuals;

public class HCTeam {

	private List<String> memberlist;
	private UUID teamID;
	private String Leader;
	private String name;
	private TreeMap<String, LifeStatus> memberStatus;
	private Boolean isWiped = false;
	private Integer deadPlayerCount = 0;
	private Team sBTeam;
	private ChatColor teamColor;

	public HCTeam(String Name) {
		memberlist = new ArrayList<String>();
		teamID = UUID.randomUUID();
		setName(Name);
		memberStatus = new TreeMap<String, LifeStatus>();
		Bukkit.broadcastMessage(teamID.toString());
		teamColor = Visuals.NextTeamColor();
		if (Main.scoreboard.getTeam(Name) == null) {
			sBTeam = Main.scoreboard.registerNewTeam(Name);
		} else {
			sBTeam = Main.scoreboard.getTeam(Name);
		}
	}

	public HCTeam(String Name, UUID ID) {
		memberlist = new ArrayList<String>();
		teamID = ID;
		setName(Name);
		memberStatus = new TreeMap<String, LifeStatus>();
		Bukkit.broadcastMessage(teamID.toString());
		teamColor = Visuals.NextTeamColor();
		if (Main.scoreboard.getTeam(Name) == null) {
			sBTeam = Main.scoreboard.registerNewTeam(Name);
		} else {
			sBTeam = Main.scoreboard.getTeam(Name);
		}
	}

	public UUID getUUID() {
		return teamID;
	}

	public String getLeader() {
		return Leader;
	}

	public void setLeader(String LeaderID) {
		Leader = LeaderID;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public List<String> getMembers() {
		return memberlist;
	}

	@SuppressWarnings("deprecation")
	public TeamMemberChangeResult addMember(String PID) {
		if (memberlist.size() < 3) {
			memberlist.add(PID);
			PlayerManager pManager = Main.pm;
			pManager.getPlayer(PID).setTeam(teamID);
			memberStatus.put(PID, LifeStatus.ALIVE);
			sBTeam.addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(PID)));
			return TeamMemberChangeResult.SUCCESS;
		} else if (memberlist.size() == 3) {
			return TeamMemberChangeResult.FULL;
		}
		return TeamMemberChangeResult.FAILED;
	}

	@SuppressWarnings("deprecation")
	public TeamMemberChangeResult removeMember(String PID) {
		if (memberlist.contains(PID) && !(Leader == PID)) {
			memberlist.remove(PID);
			PlayerManager pm = Main.pm;
			pm.getPlayer(PID).setTeam(null);
			sBTeam.removePlayer(Bukkit.getOfflinePlayer(UUID.fromString(PID)));
			return TeamMemberChangeResult.SUCCESS;
		} else if (Leader == PID) {
			return TeamMemberChangeResult.LEADER;
		} else {
			return TeamMemberChangeResult.FAILED;
		}
	}

	public Boolean isMember(String PID) {
		return memberlist.contains(PID);
	}

	public Boolean getIsWiped() {
		return isWiped;
	}

	public void setIsWiped(Boolean isWiped) {
		this.isWiped = isWiped;
	}

	public Integer getDeadPlayerCount() {
		return deadPlayerCount;
	}

	public ChatColor getTeamColor() {
		return teamColor;
	}

	public void PlayerDied() {
		deadPlayerCount++;
		if (deadPlayerCount == 3) {
			setIsWiped(true);
			TeamWipeEvent event = new TeamWipeEvent(this);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
	}

	public Team getSbTeam() {
		return sBTeam;
	}

}
