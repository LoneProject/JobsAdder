package org.lone64.jobsadder.command.tabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTab implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (sender instanceof Player player) {
            if (player.hasPermission("ja.admin")) {
                if (args.length == 1) {
                    tabs.addAll(Arrays.asList("목록", "재설정"));
                }
            }
        }
        return tabs;
    }

}
