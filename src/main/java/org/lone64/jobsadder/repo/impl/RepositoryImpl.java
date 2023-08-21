package org.lone64.jobsadder.repo.impl;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.lone64.jobsadder.job.main.Job;
import org.lone64.jobsadder.job.prefix.JobPrefix;
import org.lone64.jobsadder.job.stage.JobMaximum;
import org.lone64.jobsadder.job.stage.JobStage;
import org.lone64.jobsadder.job.team.JobTeam;
import org.lone64.jobsadder.repo.Repository;
import org.lone64.jobsadder.util.file.Config;
import org.lone64.jobsadder.util.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryImpl implements Repository {

    private final Map<String, Job> jobMap = new HashMap<>();

    @Override
    public Repository init() {
        this.loadAll();
        return this;
    }

    @Override
    public void loadAll() {
        this.jobMap.clear();
        for (String s : new Config("contents").getList()) {
            if (new Config("contents/" + s).isFolder()) {
                for (String s1 : new Config("contents/" + s).getList()) {
                    if (new Config("contents/" + s + "/" + s1).isFolder()) continue;
                    if (!s1.endsWith(".yml")) continue;

                    Config config = new Config("contents/" + s + "/" + s1);
                    if (this.isJob(config.getString("name"))) continue;

                    Job job = new Job(config.getString("name"), config.getString("icon"), config.getString("display-name"),
                            new JobPrefix(config.getBoolean("prefix-options.enabled"),
                                    config.getString("prefix-options.prefix"),
                                    config.getString("prefix-options.suffix")),
                            new JobMaximum(Items.replaceItemLegacy(config)),
                            config.getListString("run-commands"));
                    if (job.getJobPrefix().isEnabled()) {
                        JobTeam.addTeam(job.getName(), job.getJobPrefix().getPrefix(), job.getJobPrefix().getSuffix());
                    } else JobTeam.delTeam(job.getName());
                    for (String name : config.getSet("stage-options")) {
                        JobStage jobStage = new JobStage(name, config.getDouble("stage-options." + name + ".required-amount"),
                                Items.replaceItemLegacy(config, name),
                                Sound.valueOf(config.getString("stage-options." + name + ".default-options.play-sound")));
                        job.addJobStage(jobStage);
                    }
                    this.jobMap.put(config.getString("name"), job);
                }
                continue;
            }

            if (new Config("contents/" + s).isFile() && !s.endsWith(".yml")) continue;

            Config config = new Config("contents/" + s);
            if (this.isJob(config.getString("name"))) continue;

            Job job = new Job(config.getString("name"), config.getString("icon"), config.getString("display-name"),
                    new JobPrefix(config.getBoolean("prefix-options.enabled"),
                            config.getString("prefix-options.prefix"),
                            config.getString("prefix-options.suffix")),
                    new JobMaximum(Items.replaceItemLegacy(config)),
                    config.getListString("run-commands"));
            for (String name : config.getSet("stage-options")) {
                JobStage jobStage = new JobStage(name, config.getDouble("stage-options." + name + ".required-amount"),
                        Items.replaceItemLegacy(config, name),
                        Sound.valueOf(config.getString("stage-options." + name + ".default-options.play-sound")));
                job.addJobStage(jobStage);
            }
            this.jobMap.put(config.getString("name"), job);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!this.isJobPlayer(player)) continue;
            if (JobTeam.isTeamPlayer(player)) continue;
            String name = this.getJobPlayer(player).getName();
            JobTeam.addTeamPlayer(name, player);
        }
    }

    @Override
    public void addJobPlayer(String name, OfflinePlayer player) {
        new Config("jobs.yml").setObject(player.getUniqueId() + ".job", name);
        new Config("jobs.yml").setObject(player.getUniqueId() + ".stage", 1);
    }

    @Override
    public void delJobPlayer(OfflinePlayer player) {
        new Config("jobs.yml").setObject(player.getUniqueId().toString(), null);
    }

    @Override
    public void setJobStagePlayer(OfflinePlayer player, int stage) {
        new Config("jobs.yml").setObject(player.getUniqueId() + ".stage", stage);
    }

    @Override
    public Job getJobPlayer(OfflinePlayer player) {
        String name = new Config("jobs.yml").getString(player.getUniqueId() + ".job");
        return this.jobMap.get(name);
    }

    @Override
    public int getJobStagePlayer(OfflinePlayer player) {
        return new Config("jobs.yml").getInt(player.getUniqueId() + ".stage");
    }

    @Override
    public boolean isJobPlayer(OfflinePlayer player) {
        for (String uuid : new Config("jobs.yml").getSet("")) {
            if (uuid.equals(player.getUniqueId().toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Job getJob(String name) {
        return this.jobMap.get(name);
    }

    @Override
    public boolean isJob(String name) {
        return this.jobMap.get(name) != null;
    }

    @Override
    public List<String> getJobs() {
        return new ArrayList<>(this.jobMap.keySet());
    }

}
