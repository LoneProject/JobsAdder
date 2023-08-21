package org.lone64.jobsadder.api.plugin.expression;

import org.bukkit.OfflinePlayer;
import org.lone64.jobsadder.JobsAdder;

public class JobStage {

    public static void set(OfflinePlayer player, int s) {
        JobsAdder.getInstance().getRepository().setJobStagePlayer(player, s);
    }

    public static void add(OfflinePlayer player, int s) {
        int stage = JobsAdder.getInstance().getRepository().getJobStagePlayer(player) + 1;
        JobsAdder.getInstance().getRepository().setJobStagePlayer(player, stage);
    }

    public static void subtract(OfflinePlayer player, int s) {
        int stage = JobsAdder.getInstance().getRepository().getJobStagePlayer(player) - 1;
        JobsAdder.getInstance().getRepository().setJobStagePlayer(player, stage);
    }

    public static int get(OfflinePlayer player) {
        return JobsAdder.getInstance().getRepository().getJobStagePlayer(player);
    }

}
