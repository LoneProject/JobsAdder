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

import javax.annotation.Nullable;

public class SkChangedJobStageEvent extends SkriptEvent {

    static {
        Skript.registerEvent("Changed Job Stage", SkChangedJobStageEvent.class, ChangedJobStageEvent.class,
                        "changed job stage")
                .description("")
                .examples("on changed job stage:")
                .since("1.0.0");

        EventValues.registerEventValue(ChangedJobStageEvent.class, Player.class, new Getter<>() {
            @Override
            public @Nullable Player get(ChangedJobStageEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ChangedJobStageEvent.class, Integer.class, new Getter<>() {
            @Override
            public @Nullable Integer get(ChangedJobStageEvent e) {
                return e.getStage();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public boolean check(Event e) {
        return e instanceof ChangedJobStageEvent;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "changed job stage";
    }

}
