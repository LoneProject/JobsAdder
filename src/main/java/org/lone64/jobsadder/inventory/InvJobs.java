package org.lone64.jobsadder.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.lone64.jobsadder.JobsAdder;
import org.lone64.jobsadder.job.main.Job;
import org.lone64.jobsadder.util.inventory.InvUtil;
import org.lone64.jobsadder.util.item.ItemUtil;

public class InvJobs extends InvUtil {

    public InvJobs(Player player) {
        super(player, "&8직업 목록", 54);

        for (String name : JobsAdder.getInstance().getRepository().getJobs()) {
            Job job = JobsAdder.getInstance().getRepository().getJob(name);
            this.addItem(new ItemUtil(Material.valueOf(job.getIcon()))
                    .setDisplayName("&a&l" + job.getDisplayName() + " &a&l직업").setEnchant(Enchantment.DURABILITY, 1)
                    .setItemFlag(ItemFlag.HIDE_ENCHANTS).setItemFlag(ItemFlag.HIDE_ATTRIBUTES).getItemStack());
        }
    }

}
