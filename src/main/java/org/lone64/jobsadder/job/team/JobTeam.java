package org.lone64.jobsadder.job.team;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.lone64.jobsadder.util.Util;

public class JobTeam {

    private static final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public static void cancel() {
        for (Team team : scoreboard.getTeams()) {
            team.unregister();
        }
    }

    public static void addTeam(String name, String prefix, String suffix) {
        if (scoreboard.getTeam(name) != null) return;
        Team team = scoreboard.registerNewTeam(name);
        team.setPrefix(Util.replaceLegacyColor(prefix));
        team.setSuffix(Util.replaceLegacyColor(suffix));
    }

    public static void delTeam(String name) {
        Team team = scoreboard.getTeam(name);
        if (team != null) {
            team.unregister();
        }
    }

    public static void addTeamPlayer(String name, OfflinePlayer player) {
        Team team = scoreboard.getTeam(name);
        if (team != null) {
            team.addPlayer(player);
        }
    }

    public static void delTeamPlayer(OfflinePlayer player) {
        Team team = scoreboard.getPlayerTeam(player);
        if (team != null) {
            team.removePlayer(player);
        }
    }

    public static Team getTeamPlayer(OfflinePlayer player) {
        return scoreboard.getPlayerTeam(player);
    }

    public static boolean isTeamPlayer(OfflinePlayer player) {
        return scoreboard.getPlayerTeam(player) != null;
    }

}
