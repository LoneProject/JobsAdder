package org.lone64.jobsadder.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.inventory.InvJobs;
import org.lone64.jobsadder.util.adventure.AdventureUtil;
import org.lone64.jobsadder.util.file.Config;
import org.lone64.jobsadder.util.language.LanguageUtil;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            AdventureUtil.consoleMessage(JobsAdder.getLangConfig().getString("cannot-use-this-command"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ja.admin")) return true;
        else if (args.length < 1) {
            AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("none-argument-command"));
            return true;
        }

        switch (args[0]) {
            default:
                AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("unknown-command"));
                break;
            case "목록":
                new InvJobs(player).load();
                break;
            case "재설정":
                JobsAdder.getMainConfig().setReload();
                JobsAdder.setLangConfig(new Config("lang/" + LanguageUtil.getCurrentLanguage() + ".yml"));
                if (!JobsAdder.getLangConfig().isExists()) {
                    JobsAdder.setLangConfig(new Config("lang/lang_en.yml"));
                }
                JobsAdder.getLangConfig().setReload();
                JobsAdder.getInstance().getRepository().loadAll();
                AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("reload-completed"));
                break;
        }
        return false;
    }

}
