package org.lone64.jobsadder.util.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.lone64.jobsadder.util.Util;
import org.lone64.jobsadder.util.file.Config;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static ItemStack replaceItemLegacy(Config config, String name) {
        ItemUtil item = new ItemUtil(Material.valueOf(config.getString("stage-options." + name + ".item-options.show-type")));
        item.setDisplayName(config.getString("stage-options." + name + ".item-options.show-display"));
        item.setLore(replaceLoreLegacy(config.getListString("stage-options." + name + ".item-options.show-lore"), config, name));
        item.setEnchant(Enchantment.DURABILITY, 1);
        item.setItemFlag(ItemFlag.HIDE_ENCHANTS);
        item.setItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        return item.getItemStack();
    }

    private static List<String> replaceLoreLegacy(List<String> lore, Config config, String name) {
        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(Util.replaceLegacyColor(s.replace("{@amount}", Util.replaceLegacyFormat(config.getDouble("stage-options." + name + ".required-amount")))));
        }
        return lores;
    }

    public static ItemStack replaceItemLegacy(Config config) {
        ItemUtil item = new ItemUtil(Material.valueOf(config.getString("stage-maximum.item-options.show-type")));
        item.setDisplayName(config.getString("stage-maximum.item-options.show-display"));
        item.setLore(replaceLoreLegacy(config.getListString("stage-maximum.item-options.show-lore")));
        item.setEnchant(Enchantment.DURABILITY, 1);
        item.setItemFlag(ItemFlag.HIDE_ENCHANTS);
        item.setItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        return item.getItemStack();
    }

    private static List<String> replaceLoreLegacy(List<String> lore) {
        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(Util.replaceLegacyColor(s));
        }
        return lores;
    }

}
