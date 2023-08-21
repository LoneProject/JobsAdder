package org.lone64.jobsadder.api.plugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SelectedJobEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final String job;

    public SelectedJobEvent(Player player, String job) {
        this.player = player;
        this.job = job;
    }

    public Player getPlayer() {
        return player;
    }

    public String getJob() {
        return job;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}