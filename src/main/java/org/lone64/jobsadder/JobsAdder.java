package org.lone64.jobsadder;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.lone64.jobsadder.command.JobCommand;
import org.lone64.jobsadder.command.JobUpCommand;
import org.lone64.jobsadder.command.MainCommand;
import org.lone64.jobsadder.command.tabs.MainTab;
import org.lone64.jobsadder.job.team.JobTeam;
import org.lone64.jobsadder.listener.InventoryClickListener;
import org.lone64.jobsadder.repo.Repository;
import org.lone64.jobsadder.repo.impl.RepositoryImpl;
import org.lone64.jobsadder.util.file.Config;
import org.lone64.jobsadder.util.language.LanguageUtil;
import org.lone64.jobsadder.util.vault.VaultUtil;

import java.io.IOException;

public final class JobsAdder extends JavaPlugin {

    private static JobsAdder instance;
    private static String prefix;
    private static Config config, jobs, lang, contents;

    private Repository repository;
    private VaultUtil util;

    private SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "[JobsAdder]";

        this.saveConfig();
        this.util = new VaultUtil();
        this.repository = new RepositoryImpl().init();
        this.getCommand("직업").setExecutor(new JobCommand());
        this.getCommand("직업해금").setExecutor(new JobUpCommand());
        this.getCommand("직업관리").setExecutor(new MainCommand());
        this.getCommand("직업관리").setTabCompleter(new MainTab());
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);

        try {
            SkriptAddon addonInstance = Skript.registerAddon(this);
            addonInstance.loadClasses("org.lone64.jobsadder.api", "skript");
        } catch (IOException ignored) {}
    }

    @Override
    public void onDisable() {
        JobTeam.cancel();
    }

    public void saveConfig() {
        config = new Config("config.yml");

        contents = new Config("contents");
        if (!contents.isExists())
            contents.setFolder();

        contents = new Config("contents/_example/_example_job.yml");
        if (!config.isExists()) {
            this.saveResource("contents/_example/_example_job.yml", false);
        }

        if (!config.isExists())
            this.saveResource("config.yml", false);

        jobs = new Config("jobs.yml");
        if (!jobs.isExists())
            this.saveResource("jobs.yml", false);

        lang = new Config("lang/lang_en.yml");
        if (!lang.isExists())
            this.saveResource("lang/lang_en.yml", false);
        lang = new Config("lang/lang_ko.yml");
        if (!lang.isExists())
            this.saveResource("lang/lang_ko.yml", false);

        lang = new Config("lang/" + LanguageUtil.getCurrentLanguage() + ".yml");
        if (!lang.isExists()) {
            lang = new Config("lang/lang_en.yml");
        }
    }

    public static JobsAdder getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Config getMainConfig() {
        return config;
    }

    public static void setLangConfig(Config lang) {
        JobsAdder.lang = lang;
    }

    public static Config getLangConfig() {
        return lang;
    }

    public Repository getRepository() {
        return repository;
    }

    public VaultUtil getUtil() {
        return util;
    }

}
