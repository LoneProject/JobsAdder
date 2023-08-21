package org.lone64.jobsadder.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.job.main.Job;
import org.lone64.jobsadder.job.stage.JobStage;
import org.lone64.jobsadder.util.inventory.InvUtil;

public class InvJobUpgrade extends InvUtil {

    public InvJobUpgrade(Player player) {
        super(player, "&8직업 해금", InventoryType.HOPPER);

        int stage = JobsAdder.getInstance().getRepository().getJobStagePlayer(player);
        Job job = JobsAdder.getInstance().getRepository().getJobPlayer(player);
        if (job.getJobStages().size() == stage) {
            this.setItem(2, job.getJobMaximum().getItem());
        } else {
            JobStage jobStage = JobsAdder.getInstance().getRepository().getJob(job.getName()).getJobStages().get(stage);
            this.setItem(2, jobStage.getItem());
        }
    }

}
