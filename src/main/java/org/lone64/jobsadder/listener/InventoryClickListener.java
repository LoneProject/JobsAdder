package org.lone64.jobsadder.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.api.plugin.event.ChangedJobStageEvent;
import org.lone64.jobsadder.api.plugin.event.SelectedJobEvent;
import org.lone64.jobsadder.inventory.InvJobUpgrade;
import org.lone64.jobsadder.job.main.Job;
import org.lone64.jobsadder.job.stage.JobStage;
import org.lone64.jobsadder.job.team.JobTeam;
import org.lone64.jobsadder.util.Util;
import org.lone64.jobsadder.util.adventure.AdventureUtil;
import org.lone64.jobsadder.util.vault.API;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(Util.replaceLegacyColor("&8직업 목록"))) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onJobSelect(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(Util.replaceLegacyColor("&8직업 선택"))) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        for (String name : JobsAdder.getInstance().getRepository().getJobs()) {
            Job job = JobsAdder.getInstance().getRepository().getJob(name);
            JobStage jobStage = JobsAdder.getInstance().getRepository().getJob(name).getJobStages().get(0);
            if (item.isSimilar(jobStage.getItem())) {
                if (JobsAdder.getInstance().getRepository().isJobPlayer(player)) return;

                player.closeInventory();
                JobTeam.addTeamPlayer(name, player);
                JobsAdder.getInstance().getRepository().addJobPlayer(name, player);
                AdventureUtil.playerSound(player, jobStage.getSound());
                AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("player-select-job")
                        .replace("{@job}", job.getDisplayName()));
                if (job.getCommands().size() != 0) {
                    for (String s : job.getCommands()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
                    }
                }
                Bukkit.getPluginManager().callEvent(new SelectedJobEvent(player, job.getDisplayName()));
            }
        }
    }

    @EventHandler
    public void onJobUpgrade(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equals(Util.replaceLegacyColor("&8직업 해금"))) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        event.setCancelled(true);
        Job job = JobsAdder.getInstance().getRepository().getJobPlayer(player);
        if (item.isSimilar(job.getJobMaximum().getItem())) {
            AdventureUtil.playerSound(player, Sound.ENTITY_VILLAGER_NO);
            AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("upgrade-job-maximum"));
            return;
        }

        int stage = JobsAdder.getInstance().getRepository().getJobStagePlayer(player);
        JobStage jobStage = JobsAdder.getInstance().getRepository().getJob(job.getName()).getJobStages().get(stage == 8 ? stage - 1 : stage);
        if (item.isSimilar(jobStage.getItem())) {
            if (!JobsAdder.getInstance().getRepository().isJobPlayer(player)) return;
            if (jobStage.getAmount() > API.getVault().get(player)) {
                AdventureUtil.playerSound(player, Sound.ENTITY_VILLAGER_NO);
                AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("amount-not-enough"));
                return;
            }

            API.getVault().subtract(player, jobStage.getAmount());
            AdventureUtil.playerSound(player, jobStage.getSound());
            AdventureUtil.playerMessage(player, JobsAdder.getLangConfig().getString("upgrade-job-stage")
                    .replace("{@job}", job.getDisplayName()).replace("{@stage}", (stage + 1) + "단계"));
            JobsAdder.getInstance().getRepository().setJobStagePlayer(player, stage + 1);
            stage = JobsAdder.getInstance().getRepository().getJobStagePlayer(player);
            Bukkit.getPluginManager().callEvent(new ChangedJobStageEvent(player, stage));

            job = JobsAdder.getInstance().getRepository().getJob(job.getName());
            if (job.getJobStages().size() == stage) {
                event.getView().setItem(2, job.getJobMaximum().getItem());
            } else {
                jobStage = JobsAdder.getInstance().getRepository().getJob(job.getName()).getJobStages().get(stage);
                event.getView().setItem(2, jobStage.getItem());
            }
        }
    }

}
