package org.lone64.jobsadder.api.skript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.lone64.jobsadder.api.plugin.event.ChangedJobStageEvent;
import org.lone64.jobsadder.api.plugin.event.SelectedJobEvent;

import javax.annotation.Nullable;

public class SkSelectedJobEvent extends SkriptEvent {

    static {
        Skript.registerEvent("Selected Player Job", SkSelectedJobEvent.class, SelectedJobEvent.class,
                        "selected player job")
                .description("")
                .examples("on selected player job:")
                .since("1.0.0");

        EventValues.registerEventValue(SelectedJobEvent.class, Player.class, new Getter<>() {
            @Override
            public @Nullable Player get(SelectedJobEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SelectedJobEvent.class, String.class, new Getter<>() {
            @Override
            public @Nullable String get(SelectedJobEvent e) {
                return e.getJob();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public boolean check(Event e) {
        return e instanceof SelectedJobEvent;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "selected player job";
    }

}
