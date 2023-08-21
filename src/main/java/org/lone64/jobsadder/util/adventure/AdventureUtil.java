package org.lone64.jobsadder.util.adventure;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.util.Util;
import org.lone64.jobsadder.util.color.builders.GradientTextBuilder;

public class AdventureUtil {

    public static String getPrefix() {
        return new GradientTextBuilder().text(JobsAdder.getPrefix())
                .addColor("#00ffff")
                .addColor("#6a5acd")
                .blur(0.12)
                .build()
                .getJsonText();
    }

    public static void consoleMessage(String s) {
        if (s == null) return;
        String name = getPrefix();
        Bukkit.getConsoleSender().sendMessage(Util.replaceLegacyColor(s.replace("{@p}", name)));
    }

    public static void playerMessage(Player player, String s) {
        if (s == null) return;
        String name = getPrefix();
        player.sendMessage(Util.replaceLegacyColor(s.replace("{@p}", name)));
    }

    public static void playerSound(Player player, Sound sound) {
        if (sound == null) return;
        player.playSound(player.getLocation(), sound, 1, 2);
    }

}
