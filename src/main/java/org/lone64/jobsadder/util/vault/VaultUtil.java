package org.lone64.jobsadder.util.vault;

import org.bukkit.OfflinePlayer;
import org.lone64.vault.economy.AbstractEconomy;
import org.lone64.vault.economy.Economy;
import org.lone64.vault.economy.EconomyResponse;

public class VaultUtil {

    private final Economy economy;

    public VaultUtil() {
        this.economy = new AbstractEconomy();
    }

    public boolean set(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.setAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public boolean add(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.addAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        EconomyResponse response = this.economy.subAmount(player, amount);
        return response.getType().equals(EconomyResponse.ResponseType.SUCCESS);
    }

    public double get(OfflinePlayer player) {
        return this.economy.getAmount(player);
    }

}
