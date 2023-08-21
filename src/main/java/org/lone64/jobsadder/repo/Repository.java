package org.lone64.jobsadder.repo;

import org.bukkit.OfflinePlayer;
import org.lone64.jobsadder.job.main.Job;

import java.util.List;

public interface Repository {

    Repository init();

    void loadAll();

    void addJobPlayer(String name, OfflinePlayer player);

    void delJobPlayer(OfflinePlayer player);

    void setJobStagePlayer(OfflinePlayer player, int stage);

    Job getJobPlayer(OfflinePlayer player);

    int getJobStagePlayer(OfflinePlayer player);

    boolean isJobPlayer(OfflinePlayer player);

    Job getJob(String name);

    boolean isJob(String name);

    List<String> getJobs();

}
