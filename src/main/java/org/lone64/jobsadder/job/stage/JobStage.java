package org.lone64.jobsadder.job.stage;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class JobStage {

    private final String name;
    private final double amount;
    private final ItemStack item;
    private final Sound sound;

    public JobStage(String name, double amount, ItemStack item, Sound sound) {
        this.name = name;
        this.amount = amount;
        this.item = item;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public ItemStack getItem() {
        return item;
    }

    public Sound getSound() {
        return sound;
    }

}
