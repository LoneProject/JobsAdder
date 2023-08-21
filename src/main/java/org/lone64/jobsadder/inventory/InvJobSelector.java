package org.lone64.jobsadder.inventory;

import org.bukkit.entity.Player;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.job.stage.JobStage;
import org.lone64.jobsadder.util.inventory.InvUtil;

public class InvJobSelector extends InvUtil {

    public InvJobSelector(Player player) {
        super(player, "&8직업 선택", 54);

        for (String name : JobsAdder.getInstance().getRepository().getJobs()) {
            JobStage jobStage = JobsAdder.getInstance().getRepository().getJob(name).getJobStages().get(0);
            this.addItem(jobStage.getItem());
        }
    }

}
