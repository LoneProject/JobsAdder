package org.lone64.jobsadder.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.inventory.InvJobUpgrade;
import org.lone64.jobsadder.util.adventure.AdventureUtil;

public class JobUpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            AdventureUtil.consoleMessage(JobsAdder.getLangConfig().getString("cannot-use-this-command"));
            return true;
        }

        Player player = (Player) sender;
        if (!JobsAdder.getInstance().getRepository().isJobPlayer(player)) {
            AdventureUtil.playerSound(player, Sound.ENTITY_VILLAGER_NO);
            AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("not-selected-job"));
            return true;
        }

        new InvJobUpgrade(player).load();
        return false;
    }

}
