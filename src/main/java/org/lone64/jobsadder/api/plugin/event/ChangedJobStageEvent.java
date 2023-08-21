package org.lone64.jobsadder.api.plugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ChangedJobStageEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final int stage;

    public ChangedJobStageEvent(Player player, int stage) {
        this.player = player;
        this.stage = stage;
    }

    public Player getPlayer() {
        return player;
    }

    public int getStage() {
        return stage;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
