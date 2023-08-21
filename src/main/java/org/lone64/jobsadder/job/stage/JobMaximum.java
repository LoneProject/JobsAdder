package org.lone64.jobsadder.job.stage;

import org.bukkit.inventory.ItemStack;

public class JobMaximum {

    private final ItemStack item;

    public JobMaximum(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

}
