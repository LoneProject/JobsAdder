package org.lone64.jobsadder.api.skript.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.lone64.jobsadder.JobsAdder;

public class SkJobName extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

    public SkJobName() { }

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.player = (Expression<OfflinePlayer>) args[0];
        return true;
    }

    public String toString(Event event, boolean b) {
        return this.player.toString(event, b);
    }

    protected String[] get(Event e) {
        OfflinePlayer p = this.player.getSingle(e);
        return new String[]{JobsAdder.getInstance().getRepository().getJobPlayer(p).getName()};
    }

    static {
        Skript.registerExpression(SkJobName.class, String.class, ExpressionType.SIMPLE, "[JobsAdder] job name of %offlineplayer%", "[JobsAdder] %offlineplayer%'s job name");
    }

}