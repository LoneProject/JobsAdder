package org.lone64.jobsadder.api.skript.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.lone64.jobsadder.api.plugin.expression.JobStage;

public class SkJobStage extends SimpleExpression<Integer> {

    private Expression<OfflinePlayer> player;

    public SkJobStage() { }

    public Class<? extends Integer> getReturnType() {
        return Integer.class;
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

    protected Integer[] get(Event e) {
        OfflinePlayer p = this.player.getSingle(e);
        return new Integer[]{JobStage.get(p)};
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        OfflinePlayer p = this.player.getSingle(e);
        Integer num = (Integer) delta[0];
        if (mode == Changer.ChangeMode.ADD) {
            JobStage.add(p, num);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            JobStage.subtract(p, num);
        } else if (mode == Changer.ChangeMode.SET) {
            JobStage.set(p, num);
        }
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode != Changer.ChangeMode.ADD && mode != Changer.ChangeMode.REMOVE && mode != Changer.ChangeMode.SET ? null : (Class[]) CollectionUtils.array(new Class[]{Integer.class});
    }

    static {
        Skript.registerExpression(SkJobStage.class, Integer.class, ExpressionType.SIMPLE, "[JobsAdder] stage of %offlineplayer%", "[JobsAdder] %offlineplayer%'s stage");
    }

}