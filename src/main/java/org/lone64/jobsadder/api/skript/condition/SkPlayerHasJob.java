package org.lone64.jobsadder.api.skript.condition;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.lone64.jobsadder.JobsAdder;

public class SkPlayerHasJob extends Condition {

    static {
        Skript.registerCondition(SkPlayerHasJob.class,
                "%player% (has|have) job",
                "%player% (has|have)(n't| not) job");
    }

    private Expression<OfflinePlayer> player;


    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.player = (Expression<OfflinePlayer>) exprs[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        OfflinePlayer p = this.player.getSingle(e);
        return JobsAdder.getInstance().getRepository().isJobPlayer(p);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return this.player.getSingle(e) + " has " + (isNegated() ? "not " : "") + "job";
    }

}
